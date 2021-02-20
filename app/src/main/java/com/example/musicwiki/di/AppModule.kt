package com.example.musicwiki.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.musicwiki.R
import com.example.musicwiki.network.TagAPIRequests
import com.example.musicwiki.utils.BASE_URL
import com.example.musicwiki.utils.KEY
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideOkHTTPClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor {
            val originalRequest: Request = it.request()
            val httpUrl: HttpUrl = originalRequest.url
            val newHttpUrl: HttpUrl = httpUrl.newBuilder().addQueryParameter("api_key", KEY)
                .addQueryParameter("format", "json").build()
            val requestBuilder = originalRequest.newBuilder().url(newHttpUrl)
            val request = requestBuilder.build()
            it.proceed(request)
        }.addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun providesRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(R.drawable.white_background)
            .error(R.drawable.white_background)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun providesGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }

}