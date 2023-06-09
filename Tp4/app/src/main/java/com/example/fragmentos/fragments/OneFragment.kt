package com.example.fragmentos.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentos.MainActivityViewModel
import com.example.fragmentos.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    private lateinit var binding: FragmentOneBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it)[MainActivityViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTextToShow()
    }

    private fun setTextToShow() {
        viewModel.enterText.observe(viewLifecycleOwner){
            binding.textToShow.text = viewModel.enterText.value + " 1"
        }
    }
}