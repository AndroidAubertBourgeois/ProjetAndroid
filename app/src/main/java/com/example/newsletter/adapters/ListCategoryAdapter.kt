package com.example.newsletter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.newsletter.R

class ListCategoryAdapter (val handler: SourceHandler): RecyclerView.Adapter<ListCategoryAdapter.ViewHolder>(){

    val listCategory: MutableList<String> = mutableListOf(
            "business",
            "entertainement",
            "general",
            "health",
            "science",
            "sports",
            "technology"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.button_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = listCategory[position]
        holder.mButton.text = category
        holder.mButton.setOnClickListener {
            handler.showSource(category)
        }
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    class ViewHolder(view: View) :
            RecyclerView.ViewHolder(view) {
        val mButton: Button


        init {
            // Enable click on item
            mButton = view.findViewById(R.id.btn_item)

        }
    }
}