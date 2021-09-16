package com.example.mysmarthome.ui.splashscreen

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.MutableLiveData
import com.example.mysmarthome.databinding.SplashScreenActivityBinding
import com.example.mysmarthome.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private val viewModel by viewModels<SplashScreenVM>()
    private lateinit var binding: SplashScreenActivityBinding
    private val appThemeIsApplied = MutableLiveData(false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAppTheme()
        setLottieAnimationListener()
        retrieveDataFromRemote()
    }

    private fun setAppTheme() {
        viewModel.currentPrefTheme.observe(this) { nightMode ->
            when (nightMode) {
                null -> viewModel.setAppTheme()
                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            appThemeIsApplied.postValue(true)
        }
    }

    private fun retrieveDataFromRemote() {
        viewModel.userFirstConnection.observe(this) { firstConnection ->
            if (firstConnection) {
                viewModel.changeConnectionValue(false)
                viewModel.retrieveDataFromRemote().observe(this) { result ->
                    if (result.isSuccess) {
                        viewModel.insertDataIntoLocalDb(result.getOrThrow())
                    }
                }
            }
        }
    }

    private fun setLottieAnimationListener() {
        binding.lottieAnim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                appThemeIsApplied.observe(this@SplashScreenActivity) {
                    if (it) {
                        startMainActivity()
                    }
                }
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {}

        })
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
}