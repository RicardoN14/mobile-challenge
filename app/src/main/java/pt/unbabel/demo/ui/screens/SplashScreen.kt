package pt.unbabel.demo.ui.screens

import android.os.Bundle
import android.view.animation.BounceInterpolator
import kotlinx.android.synthetic.main.screen_splash.*
import org.jetbrains.anko.startActivity
import pt.unbabel.demo.R
import pt.unbabel.demo.extensions.setEndAnimation

/**
 * Created by Ricardo Neves on 15/09/2019$.
 */
class SplashScreen : Screen(){

    companion object {
        const val SPLASH_DURATION = 1500L
    }

    override fun getLayoutResourceId() = R.layout.screen_splash

    override fun doInitializations(savedInstanceState: Bundle?) {
        splashImage.animate().rotationXBy(360f)
            .setEndAnimation { onEndAnimation() }
            .setInterpolator(BounceInterpolator())
            .setDuration(SPLASH_DURATION).start()
    }

    private fun onEndAnimation() {
        startActivity<PostsScreen>()
        finish()
    }

}