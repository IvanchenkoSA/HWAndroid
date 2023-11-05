package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.supFun.formatShortened

class PostAdapter(private val listener: (Post) -> Unit) : RecyclerView.Adapter<PostAdapter.Holder>() {

    var list : List<Post> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    class Holder(private val binding: CardPostBinding, private  val listener: (Post) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likesCounter.text = formatShortened(post.likes)
                shareCounter.text = formatShortened(post.shares)
                like.setImageResource(if (post.likeByMe) R.drawable.liked_24 else R.drawable.baseline_favorite_border_24)
                like.setOnClickListener {
                    listener(post)
                }
                if (post.likeByMe) {
                    like.setImageResource(R.drawable.liked_24)
                }
            }
        }
    }
}
