package dev.valeryvpetrov.decision.feature.hello_world.sample

import android.app.Application
import com.arkivanov.mvikotlin.timetravel.server.TimeTravelServer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class App : Application() {

    private val timeTravelServer = TimeTravelServer()

    override fun onCreate() {
        super.onCreate()
        timeTravelServer.start()
        startKoin {
            androidContext(this@App)
            androidLogger()
        }
    }
}