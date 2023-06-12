 package com.example.fragmentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentos.databinding.ActivityMainBinding
import com.example.fragmentos.fragments.OneFragment
import com.example.fragmentos.fragments.TwoFragment

 class MainActivity : AppCompatActivity() {
     private lateinit var binding: ActivityMainBinding
     private lateinit var fragment: Fragment
     private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        sendTextToFragments()

        onButtonClick(binding.buttonOne)
        onButtonClick(binding.buttonTwo)
    }

     private fun onButtonClick(button: TextView) {
         button.setOnClickListener {
             fragment = selectFragment(button)
             changeFragment()
         }
     }

     private fun selectFragment(button: TextView): Fragment {
         return if (button == binding.buttonOne)  {
             OneFragment()
         } else {
             TwoFragment()
         }
     }

    private fun changeFragment() {
        fragment.let {
            supportFragmentManager.beginTransaction().replace(R.id.containerFragment, it).commit()
        }
    }

     private fun sendTextToFragments() {
         binding.sendButton.setOnClickListener {
            mainActivityViewModel.setText(binding.enterText.text.toString())
         }
     }
}