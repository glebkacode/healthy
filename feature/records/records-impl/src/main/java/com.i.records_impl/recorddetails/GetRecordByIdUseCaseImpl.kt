package com.i.records_impl.recorddetails

import com.i.records_impl.core.Record
import com.i.records_impl.core.RecordsRepository

class GetRecordByIdUseCaseImpl(
    private val recordsRepository: RecordsRepository
) : GetRecordsByIdUseCase {

    override suspend fun invoke(id: Long): Record {
        return recordsRepository.getRecordById(id)
    }
}
