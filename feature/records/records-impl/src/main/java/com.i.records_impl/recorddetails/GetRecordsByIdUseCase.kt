package com.i.records_impl.recorddetails

import com.i.records_impl.core.Record

interface GetRecordsByIdUseCase {
    suspend operator fun invoke(id: Long): Record
}
