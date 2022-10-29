package com.magdy.moviesapp.core.services

import com.magdy.moviesapp.core.BuildConfig
import com.magdy.moviesapp.core.services.responses.MoviesResponse
import com.magdy.moviesapp.core.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): MoviesResponse

    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): MoviesResponse

    companion object {
        operator fun invoke(): ApiService {
            val requestInterceptor = Interceptor {
                val url = it.request()
                    .url
                    .newBuilder()
                    .build()
                val request = it.request()
                    .newBuilder()
                    .run {
                        addHeader(
                            "Authorization",
                            "Bearer ${BuildConfig.MOVIES_API_KEY}"
                        )
                    }
                    .url(url)
                    .build()
                return@Interceptor it.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

}
