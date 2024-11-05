package com.example.calculator

import android.util.Log
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import kotlin.math.pow
import kotlin.math.sqrt

class ButtonHandler(
    private val sumDisplay: MaterialTextView,
    private val operatorDisplay: MaterialTextView,
    private val calcDisplay: MaterialTextView
) {

    fun digitButtonClick(button: MaterialButton) {
        val calcDisplayText = "${calcDisplay.text}${button.text}"
        calcDisplay.text = calcDisplayText
    }

    fun operatorButtonClick(button: MaterialButton) {
        if (button != binding.squareRootButton) {
            sumDisplay.text = calcDisplay.text
            calcDisplay.text = ""
        }
        operatorDisplay.text = button.text.toString()
    }

    fun clearButtonClick() {
        sumDisplay.text = ""
        calcDisplay.text = ""
        operatorDisplay.text = ""
    }

    fun calculateButtonClick() {
        val num1 = sumDisplay.text.toString().toFloatOrNull() ?: 0F
        val num2 = calcDisplay.text.toString().toFloat()
        val operator = operatorDisplay.text.toString()
        Log.d("Num2", "$num2")
        val result: Float = when (operator) {
            binding.plusButton.text.toString() -> num1 + num2
            binding.minusButton.text.toString() -> num1 - num2
            binding.divideButton.text.toString() -> num1 / num2
            binding.multiplyButton.text.toString() -> num1 * num2
            binding.powerOfButton.text.toString() -> num1.pow(num2)
            binding.squareRootButton.text.toString() -> sqrt(num2)
            else -> 0F
        }
        calcDisplay.text = if (result % 1 == 0F) result.toInt().toString() else result.toString()
        operatorDisplay.text = ""
        sumDisplay.text = ""
    }


}