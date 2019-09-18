package pt.unbabel.demo

import android.app.Application
import android.content.SharedPreferences
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate

/**
 * Created by Ricardo Neves on 16/09/2019.
 */
class UnbabelApplication : Application() {

    companion object {
        lateinit var instance: UnbabelApplication
        private set

        fun getString(@StringRes rscId: Int): String = instance.getString(rscId)
    }

    var sharedPreferences: SharedPreferences? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

}