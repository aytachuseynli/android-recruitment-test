package com.aytachuseynli.algoritmatask.di

import android.content.Context
import androidx.room.Room
import com.aytachuseynli.algoritmatask.data.local.dao.AppDatabase
import com.aytachuseynli.algoritmatask.data.local.dao.SocketDao
import com.aytachuseynli.algoritmatask.data.repository.SocketRepository
import com.aytachuseynli.algoritmatask.ui.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }


    @Provides
    @Singleton
    fun provideSocketDao(database: AppDatabase): SocketDao {
        return database.socketDao()
    }

    @Provides
    @Singleton
    fun provideSocketRepository(socketDao: SocketDao): SocketRepository {
        return SocketRepository(socketDao)
    }

    @Provides
    @Singleton
    fun provideMainViewModel(socketRepository: SocketRepository): MainViewModel {
        return MainViewModel(socketRepository)
    }


}