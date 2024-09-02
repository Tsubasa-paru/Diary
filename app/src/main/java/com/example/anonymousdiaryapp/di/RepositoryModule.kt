package com.example.anonymousdiaryapp.di

import com.example.anonymousdiaryapp.data.repository.DiaryRepositoryImpl
import com.example.anonymousdiaryapp.data.repository.SettingsRepositoryImpl
import com.example.anonymousdiaryapp.domain.repository.DiaryRepository
import com.example.anonymousdiaryapp.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDiaryRepository(
        diaryRepositoryImpl: DiaryRepositoryImpl
    ): DiaryRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(
        settingsRepositoryImpl: SettingsRepositoryImpl
    ): SettingsRepository
}