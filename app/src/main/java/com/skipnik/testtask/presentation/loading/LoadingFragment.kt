package com.skipnik.testtask.presentation.loading

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.skipnik.testtask.R
import com.skipnik.testtask.databinding.FragmentLoadingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoadingFragment : Fragment(R.layout.fragment_loading) {

    private val viewModel: LoadingViewModel by viewModels()

    private var _binding: FragmentLoadingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentLoadingBinding.bind(view)

        viewModel.apply {


            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.link.collect { response ->
                        delay(2000)
                        if (response != "") {
                            binding.progress.visibility = View.INVISIBLE
                            Log.d("SOMETHING", response)
                            val action =
                                LoadingFragmentDirections.actionLoadingFragmentToWebViewFragment(
                                    response
                                )
                            findNavController().navigate(action)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


