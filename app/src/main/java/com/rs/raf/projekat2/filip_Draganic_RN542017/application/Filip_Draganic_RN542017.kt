package com.rs.raf.projekat2.filip_Draganic_RN542017.application

import android.app.Application
import com.facebook.stetho.Stetho
import com.rs.raf.projekat2.filip_Draganic_RN542017.modules.classModule
import com.rs.raf.projekat2.filip_Draganic_RN542017.modules.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class Filip_Draganic_RN542017 : Application() {
    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
        initStetho()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
        Timber.e("Pokrenut Timber")
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            classModule

        )
        startKoin {
            androidLogger(Level.DEBUG)
            // Use application context
            androidContext(this@Filip_Draganic_RN542017)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
        Timber.e("Pokrenut Koin")

    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
        Timber.e("Pokrenut Stetho")

    }

}