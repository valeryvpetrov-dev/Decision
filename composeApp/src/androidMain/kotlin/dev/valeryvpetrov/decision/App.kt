package dev.valeryvpetrov.decision

import android.app.Application
import com.arkivanov.mvikotlin.timetravel.server.TimeTravelServer
import di.startKoinWithSharedInitialized
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class App : Application() {

    private val timeTravelServer = TimeTravelServer()

    override fun onCreate() {
        super.onCreate()
        timeTravelServer.start()
        startKoinWithSharedInitialized {
            androidContext(this@App)
            androidLogger()
        }
    }
}