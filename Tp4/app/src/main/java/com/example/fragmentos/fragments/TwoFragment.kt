package com.example.fragmentos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentos.MainActivityViewModel
import com.example.fragmentos.R
import com.example.fragmentos.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    private lateinit var binding: FragmentTwoBinding
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
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTextToShow()
        setTextBackgroundColor()
    }

    private fun setTextToShow() {
        mainActivityViewModel.enterText.observe(viewLifecycleOwner){
            binding.textToShow.text = mainActivityViewModel.enterText.value + " 2"
        }
    }

    private fun setTextBackgroundColor() {
        oneFragmentViewModel.color.observe(viewLifecycleOwner) { color ->
            binding.container.setBackgroundResource(convertColorCode(color))
        }
    }

    private fun convertColorCode(colorCode: Int): Int {
        return when(colorCode) {
            1 -> R.color.color1
            2 -> R.color.color2
            3 -> R.color.color3
            else -> R.color.black
        }
    }
}