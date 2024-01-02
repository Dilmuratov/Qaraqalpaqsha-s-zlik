package com.example.qaraqalpaqshaszlik.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.qaraqalpaqshaszlik.R
import com.example.qaraqalpaqshaszlik.data.models.UserData
import com.example.qaraqalpaqshaszlik.databinding.FragmentIntroductionBinding
import com.example.qaraqalpaqshaszlik.presentation.MainViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroductionFragment : Fragment(R.layout.fragment_introduction) {
    private lateinit var binding: FragmentIntroductionBinding
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var userDataList: List<UserData>
    private lateinit var pref: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentIntroductionBinding.bind(view)
        pref = requireActivity().getSharedPreferences("my_pref", Context.MODE_PRIVATE)
        initListeners()

        initObservers()

    }

    private fun initObservers() {
        mainViewModel.userDataLiveData.observe(requireActivity()) {
            userDataList = it
        }

        MainScope().launch {
            mainViewModel.getUserData()
        }
    }

    private fun initListeners() {
        binding.btnEnter.setOnClickListener {
            val login = binding.etLogin.text
            val password = binding.etPassword.text
            if (login != null && password != null && this::userDataList.isInitialized) {
                if (login.toString().isNotEmpty() && password.toString().isNotEmpty()) {
                    var isHave = false
                    for (userData in userDataList) {
                        if (login.toString() == userData.login && password.toString() == userData.password) {
                            pref.edit().putString("login", userData.login).apply()
                            pref.edit().putString("password", userData.password).apply()
                            pref.edit().putString("username", userData.username).apply()
                            pref.edit().putString("userPath", userData.userPath).apply()
                            findNavController().navigate(R.id.action_introductionFragment_to_mainFragment)
                            isHave = true
                        }
                    }
                    if (isHave.not()) {
                        Toast.makeText(
                            requireActivity(),
                            "Parol yáki login qáte kiritilgen!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else if (password.isEmpty()) {
                    binding.etPassword.error = "Paroldi kiritiń!"
                } else {
                    binding.etLogin.error = "Logindi kiritiń!"
                }
            } else if (this::userDataList.isInitialized.not()) {
                Toast.makeText(requireActivity(), "Biraz kútiń!", Toast.LENGTH_SHORT).show()
            }
        }

//        binding.ivNext.setOnClickListener {
//            findNavController().navigate(R.id.action_introductionFragment_to_mainFragment)
//        }
//
//        binding.ivNext.startAnimation(
//            AnimationUtils.loadAnimation(
//                requireActivity(),
//                R.anim.anim_next_btn
//            )
//        )
    }
}