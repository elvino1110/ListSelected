package com.example.listselected

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listselected.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(itemList)
        binding.recyclerView.adapter = adapter



        val button: Button = findViewById(R.id.buttonSendData)
        button.setOnClickListener {
/*
            val selectedItems = adapter.selectedItems.toList()
*/
            val selectedItems = adapter.selectedItems.toList()

            binding.listSelect.layoutManager = LinearLayoutManager(this)
            val adapter2 = MyAdapter(selectedItems)
            binding.listSelect.adapter= adapter2
            val queryString = selectedItems.joinToString(separator = "&") {
                "selected_items[]=${Uri.encode(it)}"
            }
            /*val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("https://example.com/api?${queryString}"))
            startActivity(intent)*/
            val message = "Selected items: ${selectedItems.joinToString(", ")}"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }


    }
}