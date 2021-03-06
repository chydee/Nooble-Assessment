package com.desmondngwuta.domain.usecase

import com.desmondngwuta.domain.model.ShortItemDomain
import com.desmondngwuta.domain.repository.LocalRepository
import io.reactivex.Single
import javax.inject.Inject


class GetDataUseCase @Inject constructor(
    private val localRepository: LocalRepository,
) {
    operator fun invoke(): Single<List<ShortItemDomain>> {
        return localRepository.getData()
    }
}