package com.example.tmdb.data.remote

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.tmdb.BuildConfig
import com.example.tmdb.data.remote.NetworkConstant.BASE_URL
import com.example.tmdb.data.remote.NetworkConstant.ChuckerInterceptor
import com.example.tmdb.data.remote.NetworkConstant.LogInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConstant {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    val ChuckerInterceptor = named("chuckerInterceptor")
    val LogInterceptor = named("logInterceptor")

    const val IMG_BASE_URL = "https://image.tmdb.org/t/p/"
    const val DEFAULT_BACKDROP_URL =
        "http://placehold.jp/36/cccccc/aaaaaa/480x270.png?text=Awesome%20Poster%20Here"
    const val DEFAULT_POSTER_URL =
        "http://placehold.jp/48/cccccc/aaaaaa/320x480.png?text=Awesome%20Poster%20Here"
}

val networkModule = module {

    // Interceptors
    single<Interceptor>(ChuckerInterceptor) { ChuckerInterceptor(androidContext()) }
    single<Interceptor>(LogInterceptor) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // OkHttpClients
    single {
        OkHttpClient.Builder()
            .addInterceptor {
                val oldReq = it.request()
                val newUrl = oldReq.url.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                    .build()
                val newReq = oldReq.newBuilder().url(newUrl).build()
                it.proceed(newReq)
            }
            .addInterceptor(get<Interceptor>(ChuckerInterceptor))
            .apply {
                if (BuildConfig.DEBUG) addInterceptor(get<Interceptor>(LogInterceptor))
            }
            .build()
    }

    // Service Apis
    single { createRestApiAdapter<MovieServiceApi>(get(), BASE_URL) }
}

inline fun <reified T> createRestApiAdapter(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}