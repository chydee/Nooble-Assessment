package com.chidi.local.source

import android.content.Context
import com.chidi.local.model.ShortLocal
import com.google.gson.Gson
import org.json.JSONObject
import java.nio.charset.Charset
import javax.inject.Inject


class NoobleBusinessLogic @Inject constructor(private val context: Context) {

    operator fun invoke(): List<ShortLocal> {
        val inputStream = context.assets.open("data.json")
        val shorts = mutableListOf<ShortLocal>()
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charset.forName("UTF-8"))
        val item = JSONObject(json)
        val items = item.getJSONArray("shorts")
        val gson = Gson()
        for (i in 0 until items.length()) {
            val obj = items.getJSONObject(i)
            val innerObj = obj.getJSONObject("creator")
            val creatorID = innerObj.getString("userID")
            val creatorEmail = innerObj.getString("email")
            val info = gson.fromJson(obj.toString(), ShortLocal::class.java)
            shorts.add(info)
        }
        return shorts
    }
}