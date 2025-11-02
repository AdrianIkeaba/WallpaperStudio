package com.ghostdev.wallpaperstudio.di

import com.ghostdev.wallpaperstudio.data.prefs.ActiveWallpaperPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { ActiveWallpaperPrefs(androidContext()) }
}