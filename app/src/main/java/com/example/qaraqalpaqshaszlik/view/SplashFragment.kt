package com.example.qaraqalpaqshaszlik.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.qaraqalpaqshaszlik.R
import com.example.qaraqalpaqshaszlik.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private lateinit var binding: FragmentSplashBinding
    private lateinit var pref: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        pref = requireActivity().getSharedPreferences("my_pref", Context.MODE_PRIVATE)

        initListeners()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {
        binding.root.setOnTouchListener { _, _ ->
            if (pref.getString("login", null) != null && pref.getString("password", null) != null) {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_introductionFragment)
            }
            false
        }

        binding.ivNext.setOnClickListener {
            if (pref.getString("login", null) != null && pref.getString("password", null) != null) {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_introductionFragment)
            }
        }

        binding.ivNext.startAnimation(
            AnimationUtils.loadAnimation(
                requireActivity(),
                R.anim.anim_next_btn
            )
        )
    }
}