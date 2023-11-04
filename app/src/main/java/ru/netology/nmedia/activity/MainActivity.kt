package ru.netology.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.supFun.formatShortened
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likesCounter.text = formatShortened(post.likes)
                shareCounter.text = formatShortened(post.shares)
                like.setImageResource(if (post.likeByMe) R.drawable.liked_24 else R.drawable.baseline_favorite_border_24)

                if (post.likeByMe) {
                    binding.like.setImageResource(R.drawable.liked_24)
                }
            }
        }
        binding.like.setOnClickListener {
            viewModel.like()
        }

        binding.shareBtn.setOnClickListener {
            viewModel.share()
        }
    }
}