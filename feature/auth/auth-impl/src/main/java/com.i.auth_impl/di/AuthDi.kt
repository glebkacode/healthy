package com.i.auth_impl.di

import com.i.auth_impl.core.AuthRepository
import com.i.auth_impl.core.AuthRepositoryImpl
import com.i.auth_impl.core.AuthService
import com.i.auth_impl.signin.AuthUseCase
import com.i.auth_impl.signin.AuthUseCaseImpl
import com.i.auth_impl.signin.SignInViewModel
import com.i.auth_impl.signup.CreateAccountUseCase
import com.i.auth_impl.signup.CreateAccountUseCaseImpl
import com.i.auth_impl.signup.SignUpViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 60L
private const val READ_TIMEOUT = 60L

val authModule = module {
    provideUi()
    provideDomain()
}

private fun Module.provideUi() {
    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
}

private fun Module.provideDomain() {
    single<AuthService> {
        val okhttpClient = provideOkHttpClient()
        provideAuthApiService(okhttpClient)
    }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    factory<AuthUseCase> { AuthUseCaseImpl(get()) }
    factory<CreateAccountUseCase> { CreateAccountUseCaseImpl(get()) }
}

private fun provideOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
    return OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()
}

private fun provideAuthApiService(okhttp: OkHttpClient): AuthService {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder().client(okhttp)
        .baseUrl("http://192.168.0.13:8080/")
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build().create(AuthService::class.java)
}
