package com.teamb.chzonk.ui.reader

import dagger.android.support.DaggerFragment

open class ReaderComicFragment : DaggerFragment() {
    // not sure if can really use a DaggerFragment here or need a custom class with leanback

    // Probably getting pages here, actually loading the images with glide
    // I might make this a base class so that you could have separate dual and single panel classes, and bind
    // their individual necessary views accordingly.
}
