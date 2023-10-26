package com.example.lovecalculate.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lovecalculate.R
import com.example.lovecalculate.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private lateinit var binding:FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        val resultScore = arguments?.getString("resultScore")
        val resultText = arguments?.getString("resultText")
        val firstName = arguments?.getString("firstName")
        val secondName = arguments?.getString("secondName")
        tvScore.text = resultScore
        tvScoreText.text = resultText
        tvFirstName.text = firstName
        tvSecondName.text = secondName
        binding.btnAgain.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
        }
        binding.btnHistory.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_historyFragment)
        }
    }
}