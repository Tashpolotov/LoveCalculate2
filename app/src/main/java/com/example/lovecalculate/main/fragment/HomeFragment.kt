package com.example.lovecalculate.main.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.lovecalculate.R
import com.example.lovecalculate.databinding.FragmentHomeBinding
import com.example.lovecalculate.model.LoveModel
import com.example.lovecalculate.view.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        btnResult.isEnabled = false
        btnCalculate.setOnClickListener {
            RetrofitService().api.getPercentage(
                etFname.text.toString(),
                etSname.text.toString()
            ).enqueue(object : Callback<LoveModel>{
                override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                    Log.e("ololo", "onResponce: ${response.body()}")
                    if(response.isSuccessful) {
                        val loveResult = response.body()
                        val resultScore = loveResult?.percentage
                        val firstName = loveResult?.firstName
                        val secondName = loveResult?.secondName
                        val resultText = loveResult?.result
                        val bundle = Bundle()
                        bundle.putString("resultScore", resultScore)
                        bundle.putString("resultText", resultText)
                        bundle.putString("firstName", firstName)
                        bundle.putString("secondName", secondName)

                        if (!firstName.isNullOrBlank() && !secondName.isNullOrBlank()) {
                            btnResult.isEnabled = true
                            binding.btnResult.setOnClickListener {
                                findNavController().navigate(R.id.action_homeFragment_to_resultFragment, bundle)
                            }
                        } else {
                            Toast.makeText(requireContext(), "No Result", Toast.LENGTH_SHORT).show()
                        }

                    }

                }

                override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                    Log.e("ololo", "onFailure: ${t.message}")
                }

            })
        }
    }
}