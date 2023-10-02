package com.i.records_impl.addrecord.bl

import com.i.records_impl.core.Record

interface AddRecordUseCase {
    suspend operator fun invoke(record: Record)
}
