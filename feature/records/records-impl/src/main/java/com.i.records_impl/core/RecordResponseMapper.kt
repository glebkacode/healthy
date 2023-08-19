package com.i.records_impl.core

fun RecordResponse.toDomain(): Record {
    return Record(
        id = this.id,
        date = this.date,
        pressure = this.pressure,
        feelings = this.feelings
    )
}
