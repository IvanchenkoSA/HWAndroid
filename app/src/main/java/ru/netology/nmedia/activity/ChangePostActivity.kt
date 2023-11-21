package ru.netology.nmedia.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityChangePostBinding

class ChangePostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChangePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            binding.content.setText(extras.getString("key"))
            binding.content.requestFocus()
        }

        binding.save.setOnClickListener {
            val text = binding.content.text?.toString()

            if (text.isNullOrBlank()) {
                setResult(RESULT_CANCELED)
            } else {
                setResult(RESULT_OK, Intent().putExtra(Intent.EXTRA_TEXT, text))
            }
            finish()
        }

//        intent?.let {
//            val text = it.getStringExtra(Intent.EXTRA_TEXT)
//            if (text.isNullOrBlank()) {
//                Snackbar.make(binding.root, R.string.text_empty_error, LENGTH_INDEFINITE)
//                    .setAction("ok") {
//                        finish()
//                    }
//                    .show()
//                return@let
//            }
//            binding.content.setText(text)
//            binding.content.requestFocus()
//        }

    }
}