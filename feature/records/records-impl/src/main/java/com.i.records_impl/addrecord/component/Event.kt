package com.i.records_impl.addrecord.component

sealed interface Event {
    @JvmInline
    value class DateTextChanged(val text: String) : Event

    @JvmInline
    value class PressureTextChanged(val text: String) : Event

    @JvmInline
    value class FeelingsTextChanged(val text: String) : Event

    object AddRecordClicked : Event
}
