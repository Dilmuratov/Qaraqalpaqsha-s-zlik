package com.example.qaraqalpaqshaszlik.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshaszlik.R
import com.example.qaraqalpaqshaszlik.data.models.TermData2
import com.example.qaraqalpaqshaszlik.databinding.FragmentCheckBinding
import com.example.qaraqalpaqshaszlik.presentation.MainViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckFragment : Fragment(R.layout.fragment_check) {
    private lateinit var binding: FragmentCheckBinding
    private lateinit var pref: SharedPreferences
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var list: List<TermData2>
    private var num = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCheckBinding.bind(view)
        pref = requireActivity().getSharedPreferences("my_pref", Context.MODE_PRIVATE)

        initListeners()

        initObservers()
    }

    private fun initObservers() {
        mainViewModel.allDataLiveData.observe(requireActivity()) {
            list = it.shuffled()

            setData()
        }

        MainScope().launch {
            mainViewModel.getAllData()
        }
    }

    private fun initListeners() {
        if (this::list.isInitialized) {
            setData()
        }

        binding.btnRandom.setOnClickListener {
            if (this::list.isInitialized) {
                num++
                if (num < list.size) {
                    setData()
                } else {
                    Toast.makeText(requireActivity(), "Tawsildi", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.llMonets.setOnClickListener {
            initObservers()
        }

    }

    private fun setData() {
        if (num < list.size) {
            binding.tvTerm.text = list[num].term
            binding.btnRight.text = "Duris(+${list[num].like?.size})"
            binding.btnWrong.text = "Qáte(-${list[num].dislike?.size})"

            Log.d("TTTT", "setData: ${list[num].like?.joinToString { it }}")
            Log.d("TTTT", "setData: ${list[num].dislike?.joinToString { it }}")
        } else {
            num = 0
        }
    }
}