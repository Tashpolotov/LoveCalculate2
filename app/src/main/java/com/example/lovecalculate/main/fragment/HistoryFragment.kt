package com.example.lovecalculate.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.lovecalculate.R
import com.example.lovecalculate.databinding.FragmentHistoryBinding
import com.example.lovecalculate.main.App
import com.example.lovecalculate.main.fragment.adapter.HistoryAdapter
import com.example.lovecalculate.model.LoveModel


class HistoryFragment : Fragment() {

    private lateinit var binding:FragmentHistoryBinding
    private val adapter = HistoryAdapter(this::onClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = App.dataBase.getLoveDao().getAll()
        val reversedList = list.reversed()
        binding.rv.adapter = adapter
        adapter.submitList(reversedList)

    }

    private fun onClick(model: LoveModel) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Удаление элемента")
        alertDialogBuilder.setMessage("Вы уверены, что хотите удалить этот элемент?")

        alertDialogBuilder.setPositiveButton("Да") { dialog, which ->
            // Пользователь подтвердил удаление, выполняем удаление элемента
            App.dataBase.getLoveDao().delete(model)

            val list = App.dataBase.getLoveDao().getAll()
            adapter.submitList(list)
        }

        alertDialogBuilder.setNegativeButton("Отмена") { dialog, which ->
            // Пользователь отменил удаление, ничего не делаем
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}