package com.example.shymko_hw_lesson21_2901021_customview



import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView

class PosterViewGroup(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.poster_view_group, this)

        val ivImage: ImageView = findViewById(R.id.ivImage)
        val tvName: TextView = findViewById(R.id.tvName)
        val tvGenre: TextView = findViewById(R.id.tvGenre)
        val rbRating: RatingBar = findViewById(R.id.rbRating)
        val tvPrice: TextView = findViewById(R.id.tvPrice)
        val tvCurrency: TextView = findViewById(R.id.tvCurrency)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.PosterViewGroup)
        ivImage.setImageDrawable(attributes.getDrawable(R.styleable.PosterViewGroup_ivImage))
        tvName.text = attributes.getString(R.styleable.PosterViewGroup_tvName)
        tvGenre.text = attributes.getString(R.styleable.PosterViewGroup_tvGenre)
        rbRating.rating = attributes.getFloat(R.styleable.PosterViewGroup_rbRating, 3.5F)
        tvPrice.text = attributes.getInt(R.styleable.PosterViewGroup_tvPrice, 100).toString()
        tvCurrency.text = attributes.getString(R.styleable.PosterViewGroup_tvCurrency)
        attributes.recycle()
    }
}
