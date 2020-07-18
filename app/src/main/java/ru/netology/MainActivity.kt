package ru.netology

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.dto.Post
import ru.netology.util.verboseTime
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val date: Date? = SimpleDateFormat("dd-MM-yyyy").parse("17-07-2020")
        val post = Post(
            1,
            "Arshinsky Vadim",
            "Initial post! This post will start new thread in the future labs. Like me pls",
            date!!,
            0,
            0,
            3
        )

        initPost(post)
        refreshPost(post)
    }

    private fun initPost(post: Post) {
        likeButton.setOnClickListener {
            if (post.likedByMe) {
                post.likes--
                post.likedByMe = false
            } else {
                post.likes++
                post.likedByMe = true
            }
            refreshPost(post)
        }
        commentButton.setOnClickListener {
            if (post.commentedByMe) {
                post.comments--
                post.commentedByMe = false
            } else {
                post.comments++
                post.commentedByMe = true
            }
            refreshPost(post)
        }
        shareButton.setOnClickListener {
            if (post.sharedByMe) {
                post.shares--
                post.sharedByMe = false
            } else {
                post.shares++
                post.sharedByMe = true
            }
            refreshPost(post)
        }
    }

    fun refreshPost(post: Post) {
        val curentDate = Date(System.currentTimeMillis())
        val elapsed = (curentDate.time - post.created.time) / 1_000
        createdTv.text = verboseTime(elapsed)

        authorTv.text = post.author
        contentTv.text = post.content
        likeTv.text = post.likes.toString()
        commentTv.text = post.comments.toString()
        shareTv.text = post.shares.toString()
        if (post.likes == 0) likeTv.visibility = View.INVISIBLE else likeTv.visibility =
            View.VISIBLE
        if (post.comments == 0) commentTv.visibility = View.INVISIBLE else commentTv.visibility =
            View.VISIBLE
        if (post.shares == 0) shareTv.visibility = View.INVISIBLE else shareTv.visibility =
            View.VISIBLE

        if (post.likedByMe) {
            likeButton.setImageResource(R.drawable.like_active)
            likeTv.setTextColor(resources.getColor(R.color.colorRed))
        } else {
            likeButton.setImageResource(R.drawable.like_inactive)
            likeTv.setTextColor(resources.getColor(R.color.colorBlack))
        }
        if (post.commentedByMe) {
            commentButton.setImageResource(R.drawable.comment_active)
            commentTv.setTextColor(resources.getColor(R.color.colorRed))
        } else {
            commentButton.setImageResource(R.drawable.comment)
            commentTv.setTextColor(resources.getColor(R.color.colorBlack))
        }
        if (post.sharedByMe) {
            shareButton.setImageResource(R.drawable.share_active)
            shareTv.setTextColor(resources.getColor(R.color.colorRed))
        } else {
            shareButton.setImageResource(R.drawable.share)
            shareTv.setTextColor(resources.getColor(R.color.colorBlack))
        }

    }

}
