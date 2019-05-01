package com.teamb.chzonk.ui.settings

import android.os.Bundle
import android.os.Environment
import androidx.leanback.preference.LeanbackPreferenceFragment
import androidx.leanback.preference.LeanbackSettingsFragment
import androidx.preference.Preference
import androidx.preference.PreferenceDialogFragment
import androidx.preference.PreferenceFragment
import androidx.preference.PreferenceScreen
import com.obsez.android.lib.filechooser.ChooserDialog
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.R
import com.teamb.chzonk.Settings
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.util.SharedPrefsHelper
import javax.inject.Inject

class SettingsFragment : LeanbackSettingsFragment() {

    override fun onPreferenceStartInitialScreen() {
        val prefsFrag = PrefsFragment()
        startPreferenceFragment(prefsFrag)
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

        init {
            DaggerApp.appComponent.inject(this)
        }

        @Inject
        lateinit var sharedPrefsHelper: SharedPrefsHelper
        @Inject
        lateinit var viewModel: ViewModel

        private var downloadDirectory: Preference? = null

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            addPreferencesFromResource(R.xml.settings)
            // NOTE: reStoreSettings needs to be moved out of here once we have shared prefs
            // that aren't settings. that would require some changes.
            sharedPrefsHelper.restoreSettings()
            downloadDirectory = findPreference("settings_download_directory")
        }

        override fun onResume() {
            super.onResume()
            downloadDirectory?.summary = com.teamb.chzonk.Settings.DOWNLOAD_DIRECTORY
        }

        override fun onPreferenceTreeClick(preference: Preference?): Boolean {
            if (preference?.key == "settings_download_directory") {
                ChooserDialog(activity)
                    .withFilter(true, false)
                    .withStartFile(Environment.getExternalStorageDirectory().absolutePath)
                    .withResources(R.string.title_choose_folder, R.string.title_choose, R.string.dialog_cancel)
                    // to handle the result(s)
                    .withChosenListener { path, _ ->
                            if (Settings.DOWNLOAD_DIRECTORY != path) {
                                Settings.DOWNLOAD_DIRECTORY = path
                                viewModel.refreshFiles(path, false)
                                sharedPrefsHelper.saveDownloadDirectory()
                                downloadDirectory?.summary = path
                            }
                    }
                    .build()
                    .show()
            }
            return super.onPreferenceTreeClick(preference)
        }
    }
}
