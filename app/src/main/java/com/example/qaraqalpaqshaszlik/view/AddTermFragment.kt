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
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTermFragment : Fragment(R.layout.fragment_add_term) {
    private lateinit var binding: FragmentAddTermBinding
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var list: List<TermData>
    private lateinit var pref: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddTermBinding.bind(view)
        pref = requireActivity().getSharedPreferences("my_pref", Context.MODE_PRIVATE)

        initObservers()

        initListeners()
    }

    private fun initObservers() {
        mainViewModel.allDataLiveData.observe(requireActivity()) {
            list = it
        }

        MainScope().launch {
            mainViewModel.getAllData()
        }
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            val term = binding.etTerm.text
            if (term != null) {
                if (term.isNotEmpty() || ::list.isInitialized || term.toString() != "" || term.toString() != "null") {
                    var accept = true
                    for (termData in list) {
                        if (termData.term.lowercase() == term.toString().lowercase()) {
                            accept = false

                        }
                    }
                    if (accept) {
                        MainScope().launch {
                            mainViewModel.addTermData(
                                TermData(
                                    term = term.toString(),
                                    termId = System.currentTimeMillis().toString(),
                                    ownerPath = pref.getString("userPath", "null")
                                        .toString(),
                                    like = "0",
                                    dislike = "0"
                                )
                            )
//                            Toast.makeText(requireActivity(), "Qosıldı", Toast.LENGTH_SHORT)
//                                .show()
                            showSnackbar()
                            (requireActivity() as MainActivity).onBackPressed()
                        }
                    } else {
                        Snackbar.make(
                            requireView(),
                            "Aldin bunday sóz qosılǵan!",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                } else if (::list.isInitialized.not()) {
                    Snackbar.make(
                        requireView(),
                        "Iltimas biraz kútiń!\nJúklenbekte...",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    binding.etTerm.error = "Sóz kiritiń!"
                }
            } else {
                binding.etTerm.error = "Sóz kiritiń!"
            }
        }
    }

    private fun showSnackbar() {
        Snackbar.make(requireView(), "Qosildi", Snackbar.LENGTH_SHORT).show()
//        snackbar
    }
}