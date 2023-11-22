package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

//class EditPostActivityContract : ActivityResultContract<Post, String?>() {
//
//    val viewModel: PostViewModel by viewModels()
//    override fun createIntent(context: Context, input: Post): Intent {
//        val intent = Intent(context, EditPostActivity::class.java)
//        intent.putExtra("key", input.content)
//        return intent
//    }
//
//    override fun parseResult(resultCode: Int, intent: Intent?): String? =
//        if (resultCode == Activity.RESULT_OK) {
//            intent?.getStringExtra(Intent.EXTRA_TEXT)
//        } else {
//            null
//        }
//}

