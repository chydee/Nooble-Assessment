package com.chidi.nooble.model


data class ShortPlayback(
    var speed: Float,
    var text: String,
    var speedType: PlaybackSpeed
)

enum class PlaybackSpeed {
    SPEED_NORMAL,
    SPEED_MEDIUM,
    SPEED_HIGH
}

val speeds = listOf(
    ShortPlayback(
        1f,
        "1x",
        PlaybackSpeed.SPEED_NORMAL
    ),
    ShortPlayback(
        1.5f,
        "1.5x",
        PlaybackSpeed.SPEED_MEDIUM
    ), ShortPlayback(
        2f,
        "2x",
        PlaybackSpeed.SPEED_HIGH
    )
)