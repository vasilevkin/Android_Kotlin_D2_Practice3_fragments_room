package com.vasilevkin.fragmentsroom.features.splash.view.ui

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import com.vasilevkin.fragmentsroom.R
import com.vasilevkin.fragmentsroom.base.BaseActivity
import com.vasilevkin.fragmentsroom.features.animalList.view.ui.MainActivity
import com.vasilevkin.fragmentsroom.features.splash.ISplashContract
import io.reactivex.Completable
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit


/**
 * Splash Screen with the app icon and name at the center, this is also the launch screen and
 * opens up in fullscreen mode. Once launched it waits for 2 seconds after which it opens the
 * MainActivity
 */
class SplashActivity : BaseActivity<ISplashContract.Presenter>(), ISplashContract.View {

    override val presenter: ISplashContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        makeFullScreen()

        setContentView(R.layout.activity_splash)

        Completable.complete()
            .delay(2, TimeUnit.SECONDS)
            .doOnComplete { startMainActivity() }
            .subscribe()
    }

    override fun finishView() {
        finish()
    }

    override fun showError(msg: String) {
        // show error
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onViewDestroyed()
    }

    private fun makeFullScreen() {
        // Remove Title
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        // Make Fullscreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // Hide the toolbar
        supportActionBar?.hide()
    }

    private fun startMainActivity() {
        // Start activity
        startActivity(Intent(this, MainActivity::class.java))

        // Animate the loading of new activity
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        // Close this activity
        presenter.onViewCreated()
    }

    override val progressBar: ProgressBar
        get() = ProgressBar(this)
}
