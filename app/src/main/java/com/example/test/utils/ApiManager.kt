package com.example.test.utils

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    companion object{
        private var retrofit: Retrofit? = null
        @Synchronized
        private fun getInstance(): Retrofit {
            if (retrofit == null) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                val keyInterceptor = Interceptor{chain : Interceptor.Chain->
                    var original = chain.request()
                    val url: HttpUrl = original.url.newBuilder().addQueryParameter("api_key",Constants.api_key).build()
                    original = original.newBuilder().url(url).build()
                    chain.proceed(original)
                }

                val languageInterceptor = Interceptor{chain : Interceptor.Chain->
                    var original = chain.request()
                    val url: HttpUrl = original.url.newBuilder().addQueryParameter("language","en-US").build()
                    original = original.newBuilder().url(url).build()
                    chain.proceed(original)
                }


                val client = OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(keyInterceptor)
                    .addInterceptor(languageInterceptor)
                    .build()



                retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                return retrofit!!
            }
            return retrofit!!
        }
        fun getWebServices(): WebServices {
            return getInstance().create(WebServices::class.java)

        }

    }
}