package com.example.mysmarthome

import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.mysmarthome.ui.MainActivity

import androidx.test.core.app.ActivityScenario
import com.example.mysmarthome.ui.devicelist.DeviceListFragment


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var scenario : ActivityScenario<MainActivity>

    @get:Rule
    var rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
        scenario = rule.scenario
    }

    @After
    fun cleanup() {
        Intents.release()
    }

    @Test
    fun testOnActivityStart_displayDevicesListFragment() {
        scenario.onActivity {  mainActivity ->
            val navHostFragment = mainActivity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_nav_drawer)
            if (navHostFragment != null) {
               assert(navHostFragment.childFragmentManager.fragments[0] is DeviceListFragment)
            } else
                throw Exception("Test failed")
        }
    }
}