package com.example.mirestaurante.ui.bebidas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.databinding.FragmentBebidasBinding

class BebidasFragment : Fragment() {
    private var _binding: FragmentBebidasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bebidasViewModel =
            ViewModelProvider(this).get(BebidasViewModel::class.java)

        _binding = FragmentBebidasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textBebidas
        bebidasViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}