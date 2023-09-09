package com.i.records_impl.di

import com.i.records_impl.addrecord.bl.AddRecordUseCase
import com.i.records_impl.addrecord.bl.AddRecordUseCaseImpl
import com.i.records_impl.addrecord.component.AddRecordComponent
import com.i.records_impl.core.DefaultRecordsDispatcher
import com.i.records_impl.core.RecordService
import com.i.records_impl.core.RecordsDispatchers
import com.i.records_impl.core.RecordsRepository
import com.i.records_impl.core.RecordsRepositoryImpl
import com.i.records_impl.recorddetails.bl.GetRecordByIdUseCaseImpl
import com.i.records_impl.recorddetails.bl.GetRecordByIdUseCase
import com.i.records_impl.recorddetails.component.RecordsDetailComponent
import com.i.records_impl.recordlist.bl.GetRecordsListUseCase
import com.i.records_impl.recordlist.bl.GetRecordsListUseCaseImpl
import com.i.records_impl.recordlist.component.RecordsListComponent
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 60L
private const val READ_TIMEOUT = 60L

val recordsModule = module {
    provideDomain()
    provideUi()
}

private fun Module.provideDomain() {
    single<RecordService> {
        val okhttpClient = provideOkHttpClient()
        provideRecordApiService(okhttpClient)
    }
    single<RecordsRepository> { RecordsRepositoryImpl(get()) }
    factory<AddRecordUseCase> { AddRecordUseCaseImpl(get()) }
    factory<GetRecordsListUseCase> { GetRecordsListUseCaseImpl(get()) }
    factory<GetRecordByIdUseCase> { GetRecordByIdUseCaseImpl(get()) }
}

private fun Module.provideUi() {
    single<RecordsDispatchers> { DefaultRecordsDispatcher() }

    factory { RecordsListComponent(get(), get(), get(), get()) }
    factory { RecordsDetailComponent(get(), get(), get(), get()) }
    factory { AddRecordComponent(get(), get(), get(), get()) }
}

private fun provideOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(Level.BASIC)
    return OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()
}

private fun provideRecordApiService(okhttp: OkHttpClient): RecordService {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder().client(okhttp)
        .baseUrl("http://192.168.0.13:8080/")
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build().create(RecordService::class.java)
}
