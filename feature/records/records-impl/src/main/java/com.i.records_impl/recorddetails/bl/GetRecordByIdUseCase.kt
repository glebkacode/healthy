package com.i.records_impl.recorddetails.bl

import com.i.records_impl.core.Record

interface GetRecordByIdUseCase {
    suspend operator fun invoke(id: Long): Record
}
