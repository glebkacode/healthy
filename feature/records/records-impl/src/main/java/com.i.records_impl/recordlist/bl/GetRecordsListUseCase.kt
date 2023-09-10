package com.i.records_impl.recordlist.bl

import com.i.records_impl.core.Record

interface GetRecordsListUseCase {
    suspend operator fun invoke(): List<Record>
}
