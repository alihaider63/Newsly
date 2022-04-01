package com.haider.newsly

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.haider.newsly.databinding.NewslyActivityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewslyActivity : AppCompatActivity(), Listener {

    private lateinit var binding: NewslyActivityBinding
    lateinit var adapter: ArticlesAdapter
    private val articles = mutableListOf<Article>()
    private var pageNum = 2
    private var totalResults = -1
    private val PARCELABLE_OBJECT = "parcelableObject"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewslyActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linearLayoutManager = LinearLayoutManager(this)

        adapter = ArticlesAdapter(articles,this,this)
        binding.RecyclerView.adapter = adapter
        binding.RecyclerView.layoutManager = linearLayoutManager
            getArticles()

    }

    private fun getArticles() {
        val news = ArticleService.articleInstance.getArticles("apple","popularity",pageNum)
        news.enqueue(object: Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val newsObj = response.body()
                Log.d("Success",newsObj.toString())
                newsObj?.let {
                    totalResults = newsObj.totalResults
                    articles.addAll(newsObj.articles)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Error","Error in fetching Articles",t)
            }
        })
    }

    override fun onItemClicked(article: Article) {
        val intent = Intent(this.applicationContext, DetailedActivity::class.java)
        intent.putExtra(PARCELABLE_OBJECT, article)
        startActivity(intent)
    }
}