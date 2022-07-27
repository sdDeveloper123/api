package com.reference.newsapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reference.newsapi.apis.newsResponse.Article
import com.reference.newsapi.databinding.ItemNewsBinding
import kotlinx.android.synthetic.main.item_news.view.*

class listAdapter(private val onClickListener:OnClickListener): ListAdapter<Article,listAdapter.ViewHolder>(DiffCallback) {
    class ViewHolder(private var binding: ItemNewsBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(data:Article){
                binding.properties = data
                binding.executePendingBindings()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val properties = getItem(position)
        holder.itemView.setOnClickListener(){
            onClickListener.onClick(properties)
        }
        


        holder.bind(properties)
    }

companion object DiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Article, newItem:Article): Boolean {
        return oldItem.title == newItem.title
    }
}
    class OnClickListener(val clickListener: (data: Article) -> Unit) {
        fun onClick(data:Article) = clickListener(data)
    }
}