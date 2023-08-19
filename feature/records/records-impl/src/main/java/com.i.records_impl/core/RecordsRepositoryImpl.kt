package com.i.records_impl.core

import com.i.records_impl.addrecord.map

internal class RecordsRepositoryImpl(
    private val recordService: RecordService
) : RecordsRepository {

    override suspend fun createRecords(record: Record) {
        val request = record.map()
        recordService.createRecord(request)
    }

    override suspend fun getRecords(): List<Record> {
        return recordService.getRecords().map { record -> record.toDomain() }
    }

    override suspend fun getRecordById(id: Long): Record {
        return recordService.getRecordById(id).toDomain()
    }
}
