package com.abkhrr.result

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.abkhrr.common.base.BaseFragment
import com.abkhrr.common.base.ExpressionEvaluator
import com.abkhrr.common.utils.ext.view.invisible
import com.abkhrr.common.utils.ext.view.visible
import com.abkhrr.result.databinding.FragmentResultBinding
import com.bumptech.glide.Glide
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

@AndroidEntryPoint
@FragmentScoped
class ResultFragment : BaseFragment<FragmentResultBinding, ResultViewModel>() {

    @FragmentScoped
    override val binding: FragmentResultBinding by lazy { FragmentResultBinding.inflate(layoutInflater) }
    override val viewModel: ResultViewModel by viewModels()
    override val backToPreviousFragmentOnBackPressed: Boolean = true

    private val receivedURI by lazy { arguments?.getString("uri")}
    private lateinit var textRecognizer: TextRecognizer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        setupView()
    }

    private fun setupView() {
        Log.d("RESULT-FRAGMENT-URI", receivedURI.toString())

        Glide.with(requireContext())
            .load(Uri.parse(receivedURI.toString()))
            .into(binding.imageViewResult)

        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it){
                showLoading()
            }else{
                hideLoading()
            }
        }

        viewModel.recognizedTextResult.observe(viewLifecycleOwner){ recognizedText ->
            binding.tvResultText.text = recognizedText
        }

        binding.btnRecognizeText.setOnClickListener {
            recognizeTextFromImage()
        }

        binding.btnStartOver.setOnClickListener {
            viewModel.navigateToMain()
        }
    }

    private fun recognizeTextFromImage() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val inputImage = loadBitmapFromUri()?.let { InputImage.fromBitmap(it, 0) }
                viewModel.isLoading.value = true

                val recognizerTask = textRecognizer.process(inputImage!!)

                recognizerTask.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val text = task.result
                        val recognizedText = text.text
                        binding.btnRecognizeText.invisible()
                        binding.btnStartOver.visible()

                        val evaluator = ExpressionEvaluator()

                        val (question, result) = evaluator.evaluateExpression(recognizedText)

                        val resultMessage = getString(R.string.result_message, question, result)
                        viewModel.isLoading.value = false
                        viewModel.setRecognizedText(resultMessage)
                    } else {
                        viewModel.isLoading.value = false
                        val exception = task.exception
                        val resultMessage = getString(R.string.result_error, exception?.message)
                        viewModel.setRecognizedText(resultMessage)
                    }
                }
            } catch (e: Exception) {
                viewModel.isLoading.value = false
                val resultMessage = getString(R.string.result_error, e.message)
                viewModel.setRecognizedText(resultMessage)
            }
        }
    }


    private fun loadBitmapFromUri(): Bitmap? {
        return try {
            val inputStream = requireContext().contentResolver.openInputStream(Uri.parse(receivedURI.toString()))
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}