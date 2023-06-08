 package com.example.fragmentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fragmentos.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity() {
     private lateinit var binding: ActivityMainBinding
     private lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        onButtonOneClick()
        onButtonTwoClick()
    }

    private fun onButtonOneClick() {
        binding.buttonOne.setOnClickListener {
            fragment = OneFragment()
            changeFragment()
        }
    }

    private fun onButtonTwoClick() {
        binding.buttonTwo.setOnClickListener {
            fragment = TwoFragment()
            changeFragment()
        }
    }

    private fun changeFragment() {
        fragment.let {
            supportFragmentManager.beginTransaction().replace(R.id.containerFragment, it).commit()
        }
    }
}