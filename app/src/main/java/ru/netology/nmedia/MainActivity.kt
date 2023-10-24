package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.supFun.formatShortened
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id =  1,
            author =  "Нетология. Университет интернет-профессий будущего",
            published =  "21 мая в 18:36",
            content =  "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likeByMe = false,
            likes = 1500,
            shares = 320000
        )

        with(binding){
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likesCounter.text = formatShortened(post.likes)
            shareCounter.text = formatShortened(post.shares)

            if (post.likeByMe){
                binding.like.setImageResource(R.drawable.liked_24)
            }


            like.setOnClickListener{
                post.likeByMe = !post.likeByMe
                like.setImageResource(if (post.likeByMe) R.drawable.liked_24 else R.drawable.baseline_favorite_border_24)
                if (post.likeByMe) {
                    post.likes++
                } else {
                    post.likes--
                }
                likesCounter.text = formatShortened(post.likes)
            }

            shareBtn.setOnClickListener{
                post.shares++
                shareCounter.text = formatShortened(post.shares)
            }
        }
    }
}