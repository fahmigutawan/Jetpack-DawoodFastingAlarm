package com.frgutawan.dawoodfastingalarm.di

import android.content.Context
import android.media.MediaPlayer
import com.frgutawan.dawoodfastingalarm.data.alarm.AlarmDataSource
import com.frgutawan.dawoodfastingalarm.data.datastore.AppDataStore
import com.frgutawan.dawoodfastingalarm.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDatastoreDataSource(@ApplicationContext context: Context) = AppDataStore(context)

    @Provides
    @Singleton
    fun provideAlarmDataSource() = AlarmDataSource()

    @Provides
    @Singleton
    fun provideMediaPlayer() = MediaPlayer()

    @Provides
    @Singleton
    fun provideAppRepository(dataStore: AppDataStore, alarmDataSource: AlarmDataSource) =
        AppRepository(dataStore, alarmDataSource)
}
