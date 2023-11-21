package ru.netology.nmedia.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val newPostContract = registerForActivityResult(NewPostActivityContract()){result ->
            result?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()

        }

        val changePostContract = registerForActivityResult(ChangePostActivityContract()){result ->
            result?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()

        }

        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onEdit(post: Post) {
                changePostContract.launch(post)
                viewModel.edit(post)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plane"
                }
                var chooser = Intent.createChooser(intent, null)
                startActivity(chooser)
            }

        })
        binding.rcView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcView.adapter = adapter

        viewModel.data.observe(this) { posts ->
            val newPost = adapter.currentList.size < posts.size
            adapter.submitList(posts) {
                if (newPost) {
                    binding.rcView.smoothScrollToPosition(0)
                }
            }
            adapter.notifyDataSetChanged()
        }

        binding.newPostButton.setOnClickListener {
            newPostContract.launch()
        }
    }
}