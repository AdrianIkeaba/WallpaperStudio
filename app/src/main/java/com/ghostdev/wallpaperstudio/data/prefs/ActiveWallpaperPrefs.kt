package com.ghostdev.wallpaperstudio.data.prefs

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ActiveWallpaperPrefs(
    context: Context
) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("active_wallpaper_prefs", Context.MODE_PRIVATE)

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _activeWallpaperId = MutableStateFlow(prefs.getString(KEY_ACTIVE_WALLPAPER_ID, "") ?: "")
    val activeWallpaperId: StateFlow<String> = _activeWallpaperId.asStateFlow()

    fun setActiveWallpaperId(id: String) {
        prefs.edit().putString(KEY_ACTIVE_WALLPAPER_ID, id).apply()
        _activeWallpaperId.value = id
    }

    fun clearActiveWallpaperId() {
        prefs.edit().remove(KEY_ACTIVE_WALLPAPER_ID).apply()
        _activeWallpaperId.value = ""
    }

    companion object {
        private const val KEY_ACTIVE_WALLPAPER_ID = "active_wallpaper_id"
    }

}