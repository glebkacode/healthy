package com.i.navigation

private const val SIGN_IN_SCREEN_ROUTE = "sign_in_route"
private const val SIGN_UP_SCREEN_ROUTE = "sign_up_route"
private const val RECORDS_LIST_SCREEN_ROUTE = "records_list_route"
private const val RECORD_SCREEN_ROUTE = "record_route"
private const val ADD_RECORD_SCREEN_ROUTE = "add_record_route"

sealed class ScreenDest(val id: String) {
    object SignInScreenDest : ScreenDest(SIGN_IN_SCREEN_ROUTE)
    object SignUpScreenDest : ScreenDest(SIGN_UP_SCREEN_ROUTE)
    object RecordListScreenDest : ScreenDest(RECORDS_LIST_SCREEN_ROUTE)
    object RecordDetailsScreenDest : ScreenDest(RECORD_SCREEN_ROUTE)
    object AddRecordScreenDest : ScreenDest(ADD_RECORD_SCREEN_ROUTE)
}
