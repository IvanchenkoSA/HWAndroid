package ru.netology.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.supFun.formatShortened
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter{
            viewModel.likeById(it.id)
        }
        binding.rcView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcView.adapter = adapter

        viewModel.data.observe(this) { posts ->
            adapter.list = posts
//            adapter.list.clear()
//            adapter.list.addAll(posts)
            adapter.notifyDataSetChanged()

//            like.setOnClickListener {
//                viewModel.likeById()
//            }
//
//            binding.shareBtn.setOnClickListener {
//                viewModel.share()
//            }
        }
    }
}