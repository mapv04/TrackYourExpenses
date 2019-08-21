package com.miguel.android.trackyourexpenses.dagger

import com.miguel.android.trackyourexpenses.common.API_EXPENSES_BASE_URL
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthExpensesService
import com.miguel.android.trackyourexpenses.data.api.retrofit.AuthInterceptor
import com.miguel.android.trackyourexpenses.data.api.retrofit.ExpensesService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()
    }

    @Provides
    @Singleton
    @Named("retrofit")
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_EXPENSES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    @Named("retrofit_auth")
    fun provideAuthRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_EXPENSES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideRetrofitService(@Named("retrofit") retrofit: Retrofit) =  retrofit.create(ExpensesService::class.java)

    @Provides
    @Singleton
    fun provideAuthRetrofitService(@Named("retrofit_auth") retrofit: Retrofit) =  retrofit.create(AuthExpensesService::class.java)

}