package com.i.records_impl.addrecord

import com.i.records_impl.core.Record
import com.i.records_impl.core.RecordsRepository

class AddRecordUseCaseImpl(
    private val recordsRepository: RecordsRepository
) : AddRecordUseCase {

    override suspend fun invoke(record: Record) {
        recordsRepository.createRecords(record)
    }
}
