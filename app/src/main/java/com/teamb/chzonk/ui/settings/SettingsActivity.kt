package com.teamb.chzonk.ui.settings
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.teamb.chzonk.R

class SettingsActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("debug1", "gothere3")
        setContentView(R.layout.settings_fragment)
        Log.d("debug1", "gothere4")
    }
}
