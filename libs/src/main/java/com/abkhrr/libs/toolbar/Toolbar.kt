package com.abkhrr.libs.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.abkhrr.libs.R
import com.abkhrr.libs.databinding.ViewToolbarBinding
import com.abkhrr.libs.ext.setOnApplyWindowTopInsetMargin

class Toolbar(context: Context, attrs: AttributeSet) : Toolbar(context, attrs) {

    private val binding: ViewToolbarBinding by lazy {
        ViewToolbarBinding.inflate(LayoutInflater.from(context), this, true)
    }

    private var progressCount: Int = 0
    private var currentProgressStep: Int = STARTING_PROGRESS_STEP

    @StringRes
    var title: Int? = null
        set(value) {
            field = value
            binding.titleView.text = ""
            value?.let { binding.titleView.setText(it) }
        }

    var showBack: Boolean = true
        set(value) {
            field = value
            binding.backArrowView.isVisible = value
        }

    @DrawableRes
    var rightActionImageRes: Int? = null
        set(value) {
            field = value
            binding.rightActionView.isVisible = value != null
            if (value == null) return

            binding.rightActionView.setImageResource(value)
        }

    var showSecondIcon: Boolean = true
        set(value) {
            field = value
            binding.rightActionView.isVisible = value
        }


    var onBackClickedListener: () -> Unit = {}
    var onRightActionClickedListener: () -> Unit = {}

    init {
        setContentInsetsRelative(0, 0)
        val attr = context.obtainStyledAttributes(attrs, R.styleable.Toolbar)

        if (attr.hasValue(R.styleable.Toolbar_android_text))
            title = attr.getResourceId(R.styleable.Toolbar_android_text, 0)

        if (attr.hasValue(R.styleable.Toolbar_rightActionImage))
            rightActionImageRes = attr.getResourceId(R.styleable.Toolbar_rightActionImage, 0)

        if (attr.hasValue(R.styleable.Toolbar_showBack))
            showBack = attr.getBoolean(R.styleable.Toolbar_showBack, true)

        if (attr.hasValue(R.styleable.Toolbar_showSecondary))
            showSecondIcon = attr.getBoolean(R.styleable.Toolbar_showSecondary, true)

        attr.recycle()

        with(binding) {
            backArrowView.setOnClickListener { onBackClickedListener.invoke() }
            rightActionView.setOnClickListener { onRightActionClickedListener.invoke() }
            toolbarContainer.setOnApplyWindowTopInsetMargin()
        }
    }

    fun setTitle(title: String) {
        binding.titleView.text = title
    }

    fun setProgressStepCount(count: Int, currentStep: Int = STARTING_PROGRESS_STEP) = post {
        val step = if (currentStep < STARTING_PROGRESS_STEP || currentStep > count)
            STARTING_PROGRESS_STEP
        else
            currentStep

        progressCount = count + 1 // in order to have progress max - 1 on the last step
        if (step == STARTING_PROGRESS_STEP) {
            resetProgress()
        } else {
            currentProgressStep = step
            showProgress()
        }
        binding.progressLineView.isVisible = true
    }

    fun resetProgress() = post {
        currentProgressStep = STARTING_PROGRESS_STEP
        showProgress()
    }

    fun nextProgressStep() = post {
        currentProgressStep++
        showProgress()
    }

    fun previousProgressStep() = post {
        currentProgressStep--
        showProgress()
    }

    private fun showProgress() {
        val progress = MAX_PROGRESS / progressCount * currentProgressStep
        val newWidth = progress / MAX_PROGRESS * binding.container.width
        binding.progressLineView.updateLayoutParams { width = newWidth.toInt() }
    }

    companion object {
        private const val STARTING_PROGRESS_STEP = 1
        private const val MAX_PROGRESS = 100f
    }

}