package com.example.testcurs.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcurs.databinding.CursItemBinding
import com.example.testcurs.model.entities.Record

class MainAdapter(val curs: List<Record>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        CursItemBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )
    )

    override fun getItemCount() = curs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            txtDate.setText(curs.get(position).date)
            txtCurs.setText(curs.get(position).value)
        }
    }

    class ViewHolder(val binding: CursItemBinding) : RecyclerView.ViewHolder(binding.root)

}