package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ListAdapter
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.supFun.formatShortened
import ru.netology.nmedia.viewmodel.PostViewModel

interface OnInteractionListener {
    fun onLike(post: Post)
    fun onEdit(post: Post)
    fun onRemove(post: Post)
    fun onShare(post: Post)
}

class PostAdapter(
    private val onInteractionListener: OnInteractionListener

) : androidx.recyclerview.widget.ListAdapter<Post, PostAdapter.Holder>(PostDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(
            CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onInteractionListener
        )


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    class Holder(
        private val binding: CardPostBinding,
        private val onInteractionListener: OnInteractionListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                shareBtn.text = formatShortened(post.shares)
                like.isChecked = post.likeByMe
                like.text = formatShortened(post.likes)
                like.setOnClickListener {
                    onInteractionListener.onLike(post)
                }
                shareBtn.setOnClickListener {
                    onInteractionListener.onShare(post)
                }
                moreMenu.setOnClickListener {
                    PopupMenu(it.context, it).apply {
                        inflate(R.menu.options_menu)
                        setOnMenuItemClickListener { menuItem ->
                            when (menuItem.itemId) {
                                R.id.edit -> {
                                    onInteractionListener.onEdit(post)
                                    true
                                }
                                R.id.remove -> {
                                    onInteractionListener.onRemove(post)
                                    true
                                }
                                else -> false
                            }
                        }
                    }.show()
                }
            }
        }
    }
}

object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem

}