package com.example.shymko_hw_lesson21_2901021_customview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.shymko_hw_lesson21_2901021_customview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var animationClockWiseRotation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animationClockWiseRotation = AnimationUtils.loadAnimation(this, R.anim.rotation)
        setupbinding()
        clockwiseRotation()
        setupListener()
        startDrawRegulator()

    }

    private fun setupbinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupListener() {
        binding.btnButtonActivityMain.setOnClickListener {
            PosterActivity.start(this)
        }
        binding.btnSpeed0.setOnClickListener {
            binding.emotionalFaceView.stage = RegulatorView.STAGE0
            animationClockWiseRotation.duration = 90000000L
            binding.ivFan.animation = animationClockWiseRotation
        }
        binding.btnSpeed1.setOnClickListener {
            binding.emotionalFaceView.stage = RegulatorView.STAGE1
            animationClockWiseRotation.duration = 2000L
            binding.ivFan.animation = animationClockWiseRotation
            //clockwiseRotation(2000)
        }
        binding.btnSpeed2.setOnClickListener {
            binding.emotionalFaceView.stage = RegulatorView.STAGE2
            animationClockWiseRotation.duration = 500L
            binding.ivFan.animation = animationClockWiseRotation
            //clockwiseRotation()
        }
    }

    private fun startDrawRegulator() {
        binding.emotionalFaceView.stage = RegulatorView.STAGE0
    }

    private fun clockwiseRotation() {
        animationClockWiseRotation = AnimationUtils.loadAnimation(this, R.anim.rotation)
//        animationClockWiseRotation.duration = 2000L
        binding.ivFan.animation = animationClockWiseRotation
    }
}

