package com.i.healthy

import android.app.Application
import com.i.auth_impl.di.authModule
import com.i.records_impl.di.recordsModule
import org.koin.core.context.startKoin

class HealthyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    authModule,
                    recordsModule
                )
            )
        }
    }
}
