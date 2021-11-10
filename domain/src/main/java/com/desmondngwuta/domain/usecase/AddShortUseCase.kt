package com.desmondngwuta.domain.usecase

import com.desmondngwuta.domain.model.ShortItemDomain
import com.desmondngwuta.domain.repository.LocalRepository
import io.reactivex.Completable
import javax.inject.Inject

class AddShortUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    operator fun invoke(itemDomain: ShortItemDomain): Completable {
        return localRepository.addShorts(itemDomain)
    }
}