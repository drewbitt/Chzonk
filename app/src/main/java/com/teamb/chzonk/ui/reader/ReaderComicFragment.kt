package com.teamb.chzonk.ui.reader

import androidx.fragment.app.Fragment
import com.teamb.chzonk.R
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.teamb.chzonk.DaggerApp
import com.teamb.chzonk.data.ViewModel
import com.teamb.chzonk.data.model.GlideModel
import javax.inject.Inject

open class ReaderComicFragment : Fragment() {

    lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comic_reader, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var imageView = view!!.findViewById<View>(R.id.image)

        viewModel.currentBook.observe( this, androidx.lifecycle.Observer {
                result ->
            result?.let {
                Glide.with(activity!!)
                    .load(GlideModel(it, 1, true))
                    .apply(
                        RequestOptions()
                            .fitCenter()
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    )
                    .into(imageView as ImageView)
            }
        })
    }



}

// not sure if can really use a DaggerFragment here or need a custom class with leanback

// Probably getting pages here, actually loading the images with glide
// I might make this a base class so that you could have separate dual and single panel classes, and bind
// their individual necessary views accordingly.
