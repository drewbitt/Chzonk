package com.teamb.testbegin.settings
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.teamb.testbegin.R

class SettingsActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_fragment)
    }
}