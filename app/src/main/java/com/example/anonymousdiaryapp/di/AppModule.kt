package com.example.anonymousdiaryapp.di

import android.content.Context
import androidx.room.Room
import com.example.anonymousdiaryapp.data.local.AppDatabase
import com.example.anonymousdiaryapp.data.local.DiaryEntryDao
import com.example.anonymousdiaryapp.data.repository.DiaryRepositoryImpl
import com.example.anonymousdiaryapp.domain.repository.DiaryRepository
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
            context,
            AppDatabase::class.java,
            "diary_database"
        ).build()
    }

    @Provides
    fun provideDiaryEntryDao(database: AppDatabase): DiaryEntryDao {
        return database.diaryEntryDao()
    }
}