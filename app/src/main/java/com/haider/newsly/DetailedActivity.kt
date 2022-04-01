package com.haider.newsly

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.haider.newsly.databinding.ActivityDetailedBinding

class DetailedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailedBinding
    private val PARCELABLE_OBJECT = "parcelableObject"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article: Article? = intent.getParcelableExtra(PARCELABLE_OBJECT)
        val urlToImage = article?.urlToImage
        val title = article?.title
        val description = article?.description

        binding.title.text = title
        binding.description.text = description
        Glide.with(this).load(urlToImage).into(binding.image)

    }

}