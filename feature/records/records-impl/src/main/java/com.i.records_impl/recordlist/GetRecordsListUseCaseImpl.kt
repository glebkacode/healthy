package com.i.records_impl.recordlist

import com.i.records_impl.core.Record
import com.i.records_impl.core.RecordsRepository

class GetRecordsListUseCaseImpl(
    private val repository: RecordsRepository
) : GetRecordsListUseCase {

    override suspend fun invoke(): List<Record> {
        return repository.getRecords()
    }
}
