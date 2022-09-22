package com.example.mycoverage

import android.app.Application
import android.content.Context

/**
 * This application class is used to provide context
 * a useful place to initialize things like database
 *
 * don't forget to include this in the AndroidManifest!
 * Otherwise there will be null exception.
 */
class BaseApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: BaseApplication? = null

        fun getAppContext(): Context {
            return instance!!.applicationContext
        }
    }
}