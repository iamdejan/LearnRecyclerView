package com.example.learnrecyclerview

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener {

    private val itemList: MutableList<Item> = generateDummyItems(15)
    private val adapter: ItemAdapter = ItemAdapter(itemList, this)

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.setHasFixedSize(true)
    }

    override fun onDetachedFromWindow() {
        recyclerView = null
        super.onDetachedFromWindow()
    }

    override fun onDestroy() {
        recyclerView = null
        super.onDestroy()
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
    }

    fun addItem(view: View) {
        val id = itemList.size
        val item = generateDummyItem(id)
        itemList.add(item)
        adapter.notifyItemInserted(id)
        recyclerView?.smoothScrollToPosition(id)
        Toast.makeText(this, "Item $id added!", Toast.LENGTH_SHORT).show()
    }

    fun removeItem(view: View) {
        val id = itemList.size - 1
        itemList.removeAt(id)
        adapter.notifyItemRemoved(id)
        Toast.makeText(this, "Item $id removed!", Toast.LENGTH_SHORT).show()
    }

    private fun generateDummyItems(count: Int): MutableList<Item> {
        val list = ArrayList<Item>()
        for (i in 0 until count) {
            val item = generateDummyItem(i)
            list.add(item)
        }
        return list
    }

    private fun generateDummyItem(i: Int): Item {
        val drawable = when (i % 3) {
            0 -> R.drawable.ic_android
            1 -> R.drawable.ic_audio
            else -> R.drawable.ic_sun
        }

        return Item(drawable, "Item $i", "Description $i")
    }
}