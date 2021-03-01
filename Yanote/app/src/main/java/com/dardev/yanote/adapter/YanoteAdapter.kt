package com.dardev.yanote.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dardev.yanote.databinding.YanoteLayoutAdapterBinding
import com.dardev.yanote.fragments.HomeFragDirections
import com.dardev.yanote.model.Yanote
import java.util.*

class YanoteAdapter:RecyclerView.Adapter<YanoteAdapter.YanoteViewHolder>() {

    private var binding:YanoteLayoutAdapterBinding?=null

    class YanoteViewHolder(itemBinding: YanoteLayoutAdapterBinding):
            RecyclerView.ViewHolder(itemBinding.root)


    private val differCallback=
            object :DiffUtil.ItemCallback<Yanote>(){
                override fun areItemsTheSame(oldItem: Yanote, newItem: Yanote): Boolean {
                    return oldItem.TAG==newItem.TAG
                }

                override fun areContentsTheSame(oldItem: Yanote, newItem: Yanote): Boolean {
                    return  oldItem==newItem
                }
            }

    val differ=AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YanoteViewHolder {
        binding= YanoteLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )
        return YanoteViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: YanoteViewHolder, position: Int) {
        val currentYanote=differ.currentList[position]

        holder.itemView.apply {
            binding?.tvYaNoteTitle?.text=currentYanote.yaTitre
            binding?.tvYaNoteBody?.text=currentYanote.yaContenu

            val random = Random()

            val color =
                    Color.argb(
                            255,
                            random.nextInt(256),
                            random.nextInt(256),
                            random.nextInt(256)
                    )

            binding?.viewColor?.setBackgroundColor(color)

        }.setOnClickListener {
            val direction=HomeFragDirections
                    .actionHomeFragToUpdateYanoteFrag(currentYanote)
            it.findNavController().navigate(
                    direction
            )
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}