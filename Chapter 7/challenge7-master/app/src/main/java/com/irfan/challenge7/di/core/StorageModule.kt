package com.irfan.challenge7.di.core

import android.content.Context
import com.irfan.challenge7.storage.PreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object StorageModule {

    @Singleton
    @Provides
    fun provideStorage(context: Context): PreferencesManager {
        return PreferencesManager(context)
    }
}