package com.example.calculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.textview.MaterialTextView

class HistoryListAdapter(context: Context, historyItems: List<String>) :
    ArrayAdapter<String>(context, R.layout.calculation_history_item, historyItems) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.calculation_history_item, parent, false)
        val historyItem = getItem(position) ?: "ERROR"
        val tVHistoryItem = view.findViewById<MaterialTextView>(R.id.tv_history_item)
        tVHistoryItem.text = historyItem
        return view
    }
}
