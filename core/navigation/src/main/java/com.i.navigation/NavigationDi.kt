package com.i.navigation

import org.koin.dsl.module

val navigationModule = module {
    single<Navigator> { ScreenNavigator() }
}
