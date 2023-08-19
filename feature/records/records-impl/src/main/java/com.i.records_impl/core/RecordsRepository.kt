package com.i.records_impl.core

interface RecordsRepository {
    suspend fun getRecords(): List<Record>
    suspend fun createRecords(record: Record)
    suspend fun getRecordById(id: Long): Record
}
