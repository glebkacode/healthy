package com.i.records_impl.addrecord

import com.i.records_impl.core.Record
import com.i.records_impl.core.RecordRequest

fun Record.map(): RecordRequest {
    return RecordRequest(
        date = this.date,
        pressure = this.pressure,
        feelings = this.feelings
    )
}
