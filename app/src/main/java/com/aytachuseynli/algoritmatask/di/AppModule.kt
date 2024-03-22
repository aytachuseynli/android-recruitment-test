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
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponentManager::class)
object AppModule {


}