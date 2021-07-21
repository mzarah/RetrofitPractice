package com.hfad.retrofitget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.retrofitget.R
import com.hfad.retrofitget.model.Post

class PostsListAdapter: RecyclerView.Adapter<PostsListAdapter.MyViewHolder>(){

    private var postList = emptyList<Post>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.posts_list, parent, false))
    }


    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.findViewById<TextView>(R.id.postId_txt).setTextColor(0xffffffff.toInt())
        if (postList[position].id == 50) holder.itemView.findViewById<TextView>(R.id.postId_txt).setTextColor(0xff0000ff.toInt())
        holder.itemView.findViewById<TextView>(R.id.userId_txt).text = postList[position].userId.toString()
        holder.itemView.findViewById<TextView>(R.id.postId_txt).text = postList[position].id.toString()
        holder.itemView.findViewById<TextView>(R.id.title_txt).text = postList[position].title
        holder.itemView.findViewById<TextView>(R.id.desc_txt).text = postList[position].body
    }

    fun setData(newList: List<Post>){
        postList = newList
        notifyDataSetChanged()
    }
}