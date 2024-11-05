package com.example.calculator

import com.google.android.material.textview.MaterialTextView
import kotlin.math.pow

class ButtonHandler(
    private val sumDisplay: MaterialTextView,
    private val operatorDisplay: MaterialTextView,
    private val calcDisplay: MaterialTextView
) {

    fun digitButtonClick(digit: String) {
        val calcDisplayText = "${calcDisplay.text}" + digit
        calcDisplay.text = calcDisplayText
    }

    fun operatorButtonClick(operator: String) {
        if (calcDisplay.text.toString() != "") sumDisplay.text = calcDisplay.text
        operatorDisplay.text = operator
        calcDisplay.text = ""
    }

    fun clearButtonClick() {
        sumDisplay.text = ""
        calcDisplay.text = ""
        operatorDisplay.text = ""
    }

    fun calculateButtonClick() {
        val num1 = sumDisplay.text.toString().toFloat()
        val num2 = calcDisplay.text.toString().toFloat()
        val result: Float = when (operatorDisplay.text.toString()) {
            binding.plusButton.text.toString() -> num1 + num2
            binding.minusButton.text.toString() -> num1 - num2
            binding.divideButton.text.toString() -> num1 / num2
            binding.multiplyButton.text.toString() -> num1 * num2
            binding.powerOfButton.text.toString() -> num1.pow(num2)
            //TODO add square root
            else -> 0F
        }
        calcDisplay.text = if (result % 1 == 0F) result.toInt().toString() else result.toString()
        operatorDisplay.text = ""
        sumDisplay.text = ""
    }


}