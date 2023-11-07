package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.supFun.formatShortened
import ru.netology.nmedia.viewmodel.PostViewModel

class PostAdapter(
    private val likeListener: (Post) -> Unit,
    private val shareListener: (Post) -> Unit
) : androidx.recyclerview.widget.ListAdapter<Post, PostAdapter.Holder>(PostDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(
            CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            likeListener, shareListener
        )


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    class Holder(
        private val binding: CardPostBinding,
        private val likeListener: (Post) -> Unit,
        private val shareListener: (Post) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likesCounter.text = formatShortened(post.likes)
                shareCounter.text = formatShortened(post.shares)
                like.setImageResource(if (post.likeByMe) R.drawable.liked_24 else R.drawable.baseline_favorite_border_24)
                like.setOnClickListener {
                    likeListener(post)
                }
                shareBtn.setOnClickListener {
                    shareListener(post)
                }
            }
        }
    }
}

object PostDiffCallback: DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem

}