package com.abkhrr.common.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.abkhrr.common.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingUtils {
    @JvmStatic
    @BindingAdapter("imgUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("imageUrlCrop")
    fun setImageUrlCrop(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("imageWithPlaceHolder")
    fun setImageUrlWithPlaceHolder(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(imageView)
    }
}