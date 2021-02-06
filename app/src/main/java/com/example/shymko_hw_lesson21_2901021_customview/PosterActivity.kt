package com.example.shymko_hw_lesson21_2901021_customview


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shymko_hw_lesson21_2901021_customview.databinding.ActivityPosterBinding

class PosterActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, PosterActivity::class.java)
            context.startActivity(intent)
        }
    }
    private lateinit var binding : ActivityPosterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        setupBinding()
        setupListener()
    }

    private fun setupBinding() {
        binding = ActivityPosterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupListener() {
        binding.btnButtonActivityPoster.setOnClickListener {
            MainActivity.start(this)
        }
    }
}