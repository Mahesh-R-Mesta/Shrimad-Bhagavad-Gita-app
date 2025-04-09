package com.example.shree_bhagavad_gita.network

import com.example.shree_bhagavad_gita.config.AppConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object {
        private fun interpret(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("x-rapidapi-key", AppConfig.API_KEY)
                        .addHeader("x-rapidapi-host", "bhagavad-gita3.p.rapidapi.com").build()
                    chain.proceed(request)
                }.build()
        }

        fun instance(): Retrofit {
            return Retrofit.Builder().baseUrl(AppConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(interpret())
                .build()
        }
    }
}