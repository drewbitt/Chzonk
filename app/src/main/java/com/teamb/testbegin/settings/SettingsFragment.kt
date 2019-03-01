package com.teamb.testbegin.settings
import com.teamb.testbegin.R

import android.os.Bundle
import androidx.preference.PreferenceFragment
import androidx.leanback.preference.LeanbackPreferenceFragment
import androidx.leanback.preference.LeanbackSettingsFragment
import androidx.preference.DialogPreference
import androidx.preference.Preference
import androidx.preference.PreferenceScreen


class SettingsFragment : LeanbackSettingsFragment(), DialogPreference.TargetFragment {
    override fun onPreferenceStartInitialScreen() {
    }

    override fun onPreferenceStartScreen(caller: PreferenceFragment?, pref: PreferenceScreen?): Boolean {
        return true
    }

    override fun onPreferenceStartFragment(caller: PreferenceFragment?, pref: Preference?): Boolean {
        return true
    }

    override fun findPreference(key: CharSequence?): Preference {
        return Preference(context)
    }

}

/*
    // java
    private PreferenceFragment mPreferenceFragment;

    override fun onPreferenceStartInitialScreen() {
        mPreferenceFragment = buildPreferenceFragment(R.xml.settings, null)
        startPreferenceFragment(mPreferenceFragment!!)
    }

    override fun onPreferenceStartFragment(referenceFragment: PreferenceFragment, preference: Preference): Boolean {
        return false
    }

    override fun onPreferenceStartScreen(preferenceFragment: PreferenceFragment, preferenceScreen: PreferenceScreen): Boolean {
        val frag = buildPreferenceFragment(
            R.xml.settings,
            preferenceScreen.key
        )
        startPreferenceFragment(frag)
        return true
    }

    override fun findPreference(charSequence: CharSequence): Preference {
        return mPreferenceFragment!!.findPreference(charSequence)
    }

    private fun buildPreferenceFragment(preferenceResId: Int, root: String?): PreferenceFragment {
        val fragment = PrefFragment()
        val args = Bundle()
        args.putInt(PREFERENCE_RESOURCE_ID, preferenceResId)
        args.putString(PREFERENCE_ROOT, root)
        fragment.arguments = args
        return fragment
    }

    class PrefFragment : LeanbackPreferenceFragment() {

        override fun onCreatePreferences(bundle: Bundle?, s: String?) {
            val root = arguments.getString(PREFERENCE_ROOT, null)
            val prefResId = arguments.getInt(PREFERENCE_RESOURCE_ID)
            if (root == null) {
                addPreferencesFromResource(prefResId)
            } else {
                setPreferencesFromResource(prefResId, root)
            }
        }
    }

    companion object {
        private const val PREFERENCE_RESOURCE_ID = "preferenceResource"
        private const val PREFERENCE_ROOT = "root"
    }
 */