package com.anupras.schoolapp.di

import android.app.Application
import androidx.room.Room
import com.anupras.schoolapp.data.local.SchoolDatabase
import com.anupras.schoolapp.data.remote.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * Created by anamika on 03,November,2022
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSchoolApi(): APIService {
        return Retrofit.Builder()
            .baseUrl(APIService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }).build())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSchoolDatabase(app: Application): SchoolDatabase {
        return Room.databaseBuilder(
            app,
            SchoolDatabase::class.java,
            "schoolApp.db"
        ).build()
    }
}