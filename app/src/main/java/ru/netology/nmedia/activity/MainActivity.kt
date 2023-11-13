package ru.netology.nmedia.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel
import util.AndroidUtils
import util.AndroidUtils.focusAndShowKeyboard


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
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
        viewModel.edited.observe(this) { post ->
            if (post.id != 0L) {
                binding.content.setText(post.content)
                binding.content.focusAndShowKeyboard()
                binding.editCst.visibility = View.VISIBLE
                binding.editable.text = post.content
            }
        }

        binding.clearBtn.setOnClickListener {
            binding.editable.text = ""
            binding.editCst.visibility = View.GONE
            binding.content.clearFocus()
            binding.content.setText("")
            AndroidUtils.hideKeyboard(it)
        }

        binding.saveBtn.setOnClickListener {
            val text = binding.content.text.toString()
            if (text.isBlank()) {               // check is Blank
                Toast.makeText(
                    this,
                    R.string.error_empty_content,
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            viewModel.changeContent(text)    // call change
            viewModel.save()

            binding.content.setText("")      // clear field EditText
            binding.content.clearFocus()
            AndroidUtils.hideKeyboard(it)
            binding.editCst.visibility = View.GONE

        }
    }
}