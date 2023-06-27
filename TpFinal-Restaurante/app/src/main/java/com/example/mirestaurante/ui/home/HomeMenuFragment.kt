package com.example.mirestaurante.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mirestaurante.R
import com.example.mirestaurante.databinding.FragmentMenuBinding

class HomeMenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = FragmentMenuBinding.inflate(inflater, container, false)

        onBebidasButtonClick()
        onPlatosButtonClick()

        return binding.root
    }

    private fun onBebidasButtonClick() {
        binding.bebidas.setOnClickListener {
//            changeFragment(BebidasFragment())
            this.findNavController().navigate(R.id.action_nav_menu_home_to_nav_bebidas)
        }
    }

    private fun onPlatosButtonClick() {
        binding.platos.setOnClickListener {
            this.findNavController().navigate(R.id.action_nav_menu_home_to_nav_platos)
        }
    }

//    private fun changeFragment(fragment: Fragment) {
////        fragment.let {
////            parentFragmentManager.beginTransaction()
////                .replace(R.id.nav_host_fragment_content_navigation_drawer, it)
////                .addToBackStack("menu")
////                .commit()
////        }
//
//        this.findNavController().navigate(R.id.action_nav_menu_home_to_nav_bebidas)
//    }
}