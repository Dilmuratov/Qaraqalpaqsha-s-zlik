package com.example.qaraqalpaqshaszlik.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshaszlik.R
import com.example.qaraqalpaqshaszlik.data.models.TermData
import com.example.qaraqalpaqshaszlik.databinding.FragmentAddTermBinding
import com.example.qaraqalpaqshaszlik.presentation.MainViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTermFragment : Fragment(R.layout.fragment_add_term) {
    private lateinit var binding: FragmentAddTermBinding
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var pref: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddTermBinding.bind(view)
        pref = requireActivity().getSharedPreferences("my_pref", Context.MODE_PRIVATE)

        initListeners()
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            val term = binding.etTerm.text
            if (term != null) {
                if (term.toString().isNotEmpty()) {
                    MainScope().launch {
                        mainViewModel.addTermData(
                            TermData(
                                term = term.toString(),
                                termId = System.currentTimeMillis().toString(),
                                ownerId = pref.getString("userId", "null").toString(),
                                ownerPath = pref.getString("userPath", "null").toString(),
                            )
                        )
                        Toast.makeText(requireActivity(), "Qosıldı", Toast.LENGTH_SHORT).show()
                        (requireActivity() as MainActivity).onBackPressed()
                    }
                }
            }
        }
    }
}