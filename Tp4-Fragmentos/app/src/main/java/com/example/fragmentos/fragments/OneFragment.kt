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
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var oneFragmentViewModel: OneFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {
            mainActivityViewModel = ViewModelProvider(it)[MainActivityViewModel::class.java]
            oneFragmentViewModel = ViewModelProvider(it)[OneFragmentViewModel::class.java]
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
        sendColor()
    }

    private fun setTextToShow() {
        mainActivityViewModel.enterText.observe(viewLifecycleOwner){
            binding.textToShow.text = mainActivityViewModel.enterText.value + " 1"
        }
    }

    private fun sendColor() {
        binding.color1.setOnClickListener {
            oneFragmentViewModel.setColor(1)
        }

        binding.color2.setOnClickListener {
            oneFragmentViewModel.setColor(2)
        }

        binding.color3.setOnClickListener {
            oneFragmentViewModel.setColor(3)
        }
    }
}