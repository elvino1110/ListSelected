package com.example.listselected

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val itemList: List<String>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    //var selectedItems: MutableList<String> = mutableListOf()
    var selectedItems = MutableLiveData<String>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.checkBox)
        val textView: TextView = view.findViewById(R.id.textView)

        fun bind(item: String) {
            checkBox.isChecked = selectedItems.value!!.contains(item)
            textView.text = item
            checkBox.isEnabled = false

            itemView.setOnClickListener {
                if (checkBox.isChecked) {
                    selectedItems.value.remove(item)
                    checkBox.isChecked = false
                } else {
                    selectedItems.add(item)
                    checkBox.isChecked = true
                }
            }
            /*checkBox.setOnClickListener {
                if (checkBox.isChecked) {
                    selectedItems.remove(item)
                    checkBox.isChecked = false
                } else {
                    selectedItems.add(item)
                    checkBox.isChecked = true
                }
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size
}
