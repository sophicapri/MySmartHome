package com.example.mysmarthome.ui.splashscreen

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    private val dataRetrieved = MutableLiveData<Boolean>()


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
            Log.d("SplashScreen", "nightmode On ? =null ? ${nightMode == null}")
            Log.d("SplashScreen", "nightmode On ? = $nightMode")
            when (nightMode) {
                null -> viewModel.setAppTheme()
                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun retrieveDataFromRemote() {
        viewModel.userFirstConnection.observe(this) { firstConnection ->
            Log.d("SplashScreen", "retrieveDataFromRemote: first $firstConnection")
            if (firstConnection) {
                viewModel.changeConnectionValue(false)
                viewModel.retrieveDataFromRemote().observe(this) { result ->
                    if (result.isSuccess) {
                        viewModel.insertDataIntoLocalDb(result.getOrThrow())
                        dataRetrieved.postValue(true)
                    }
                }
            } else dataRetrieved.postValue(true)
        }
    }

    private fun setLottieAnimationListener() {
        binding.lottieAnim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                dataRetrieved.observe(this@SplashScreenActivity) {
                    if (it) { startMainActivity() }
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