package com.example.calculator

import android.widget.ListView
import com.example.calculator.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import kotlin.math.pow
import kotlin.math.sqrt

class CalculationsHandler(private val binding: ActivityMainBinding) {
    private val tvUpper: MaterialTextView = binding.tvUpper
    private val tvOperator: MaterialTextView = binding.tvOperator
    private val tvLower: MaterialTextView = binding.tvLower
    private val lvHistory: ListView = binding.lvHistory
    private var historyList = mutableListOf("Test", "Test2")
    private var historyListAdapter = HistoryListAdapter(binding.root.context, historyList)

    init {
        lvHistory.adapter = historyListAdapter
    }

    fun digitButton(button: MaterialButton) {
        val calcDisplayText = "${tvLower.text}${button.text}"
        tvLower.text = calcDisplayText
    }

    fun operatorButton(button: MaterialButton) {
        if (tvLower.text.toString() != "" && tvOperator.text.toString() == "") {
            if (button != binding.btnSquareRoot) {
                tvUpper.text = tvLower.text
                tvLower.text = ""
            }
            tvOperator.text = button.text.toString()
        }
    }

    fun clearButton() {
        tvUpper.text = ""
        tvLower.text = ""
        tvOperator.text = ""
    }

    fun calculateButton() {
        if (tvLower.text.toString() != "" && tvOperator.text.toString() != "") {
            val num1 = tvUpper.text.toString().toFloatOrNull() ?: 0F
            val num2 = tvLower.text.toString().toFloat()
            val operator = tvOperator.text.toString()
            val result: Float = when (operator) {
                binding.btnPlus.text.toString() -> num1 + num2
                binding.btnMinus.text.toString() -> num1 - num2
                binding.btnDivide.text.toString() -> num1 / num2
                binding.btnMultiply.text.toString() -> num1 * num2
                binding.btnPowOf.text.toString() -> num1.pow(num2)
                binding.btnSquareRoot.text.toString() -> sqrt(num2)
                else -> 0F
            }
            addToHistoryList(trimFloatToString(result))
            tvLower.text = trimFloatToString(result)
            tvOperator.text = ""
            tvUpper.text = ""
        }
    }

    private fun addToHistoryList(result: String) {
        val trimmedOperator = if (tvOperator.text.contains("x")) "^" else tvOperator.text.toString()
        historyList.add("${tvUpper.text} $trimmedOperator ${tvLower.text} = $result")

    }

    private fun trimFloatToString(float: Float): String {
        return if (float % 1 == 0F) float.toInt().toString() else float.toString()
    }
}