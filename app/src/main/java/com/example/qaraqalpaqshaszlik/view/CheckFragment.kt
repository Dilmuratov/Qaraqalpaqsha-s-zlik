package com.example.qaraqalpaqshaszlik.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshaszlik.R
import com.example.qaraqalpaqshaszlik.data.models.TermData
import com.example.qaraqalpaqshaszlik.databinding.FragmentCheckBinding
import com.example.qaraqalpaqshaszlik.presentation.MainViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckFragment : Fragment(R.layout.fragment_check) {
    private lateinit var binding: FragmentCheckBinding
    private lateinit var pref: SharedPreferences
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var list: List<TermData>
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
            list = it

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
            binding.btnRandom.isEnabled = false
            if (this@CheckFragment::list.isInitialized) {
                num++
                if (num < list.size) {
                    setData()
                } else {
                    Toast.makeText(requireActivity(), "Tawsildi", Toast.LENGTH_SHORT).show()
                    num = 0
                    setData()
                }
            }
            binding.btnRandom.isEnabled = true
        }

        binding.llMonets.setOnClickListener {
            initObservers()
        }

        binding.btnRight.setOnClickListener {
            MainScope().launch {
                binding.btnRight.isEnabled = false
                delay(1000)
                if (this@CheckFragment::list.isInitialized) {
                    val termData = list[num]
                    val userPath = pref.getString("userPath", "").toString()
                    if (termData.ownerPath != userPath) {
                        MainScope().launch {
                            mainViewModel.rate(
                                true,
                                termData.termId,
                                TermData(
                                    termData.termId,
                                    termData.term,
                                    termData.ownerPath,
                                    (termData.like.toInt() + 1).toString(),
                                    termData.dislike
                                )
                            )
                        }
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "Siz ózińizdiń sózligińizge dawıs beralmaysız!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    initObservers()
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Iltimas biraz kútiń!\nJúklenbekte...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                num++
                setData()
                binding.btnRight.isEnabled = true
            }

        }

        binding.btnWrong.setOnClickListener {
            MainScope().launch {
                binding.btnWrong.isEnabled = false
                delay(1000)
                if (this@CheckFragment::list.isInitialized) {
                    val termData = list[num]
                    val userPath = pref.getString("userPath", "").toString()
                    if (termData.ownerPath != userPath) {
                        MainScope().launch {
                            mainViewModel.rate(
                                true,
                                termData.termId,
                                TermData(
                                    termData.termId,
                                    termData.term,
                                    termData.ownerPath,
                                    termData.like,
                                    (termData.dislike.toInt() + 1).toString()
                                )
                            )
                        }
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "Siz ózińizdiń sózligińizge dawıs beralmaysız!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    initObservers()
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Iltimas biraz kútiń!\nJúklenbekte...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                num++
                setData()
                binding.btnWrong.isEnabled = true
            }

        }
    }

    private fun setData() {
        if (num < list.size) {
            val termData = list[num]
            binding.tvTerm.text = termData.term
//            binding.btnRight.text = "Duris(+${termData.like})"
//            binding.btnWrong.text = "Qáte(-${termData.dislike})"

            Log.d("TTTT", "setData: ${list[num].like}")
            Log.d("TTTT", "setData: ${list[num].dislike}")
        } else {
            num = 0
        }
    }
}