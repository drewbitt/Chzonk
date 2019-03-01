package com.teamb.testbegin.settings
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.leanback.preference.LeanbackPreferenceFragment
import androidx.leanback.preference.LeanbackSettingsFragment
import androidx.preference.Preference
import androidx.preference.PreferenceDialogFragment
import androidx.preference.PreferenceFragment
import androidx.preference.PreferenceScreen
import com.teamb.testbegin.R
import java.util.Locale

class SettingsFragment : LeanbackSettingsFragment() {

    override fun onPreferenceStartInitialScreen() {
        startPreferenceFragment(PrefsFragment())
        val res: Resources = context.resources
        val conf: Configuration = resources.configuration
        conf.setLayoutDirection(Locale.ENGLISH)
    }

    override fun onPreferenceStartScreen(caller: PreferenceFragment?, pref: PreferenceScreen?): Boolean {
        val f = PrefsFragment()
        val args = Bundle(1)
        args.putString(PreferenceFragment.ARG_PREFERENCE_ROOT, pref!!.key)
        f.arguments = args
        startPreferenceFragment(f)
        return true
    }

    override fun onPreferenceStartFragment(caller: PreferenceFragment?, pref: Preference?): Boolean {
        val f = PreferenceFragment.instantiate(activity, pref!!.fragment, pref.extras)
        f.setTargetFragment(caller, 0)
        if (f is PreferenceFragment || f is PreferenceDialogFragment) {
            startPreferenceFragment(f)
        } else {
            startImmersiveFragment(f)
        }
        return true
    }

    class PrefsFragment : LeanbackPreferenceFragment() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.settings, rootKey)
        }
    }
}
