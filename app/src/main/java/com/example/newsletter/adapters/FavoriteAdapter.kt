package com.example.newsletter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsletter.R
import com.example.newsletter.data.FavoriteDataBase
import com.example.newsletter.models.Article
import com.example.newsletter.models.ArticleFavorite
import com.example.newsletter.models.Source
import java.util.Date


class FavoriteAdapter(private val context: Context, val handler: ListArticlesHandler, private var favorisList : MutableList<ArticleFavorite>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var favoriteDataBase: FavoriteDataBase


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        favoriteDataBase = FavoriteDataBase(context)
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: ArticleFavorite= favorisList[position]
        var details = Article("",Source("",""),article.author,article.title,
                article.description,article.url, article.urlToImage,Date(),"",1)

        // Display Neighbour Name
        holder.mArticleTitle.text = article.title

        holder.mArticleDescription.text = article.description
        holder.mArticleName.text    = article.author

        holder.mArticleFavoris.setImageResource(R.drawable.ic_baseline_delete_24)

        holder.mArticleTitle.setOnClickListener {
            handler.showArticle(details)
        }
        holder.mArticleDescription.setOnClickListener {
            handler.showArticle(details)
        }
        holder.mArticleFavoris.setOnClickListener(View.OnClickListener {
            article.id?.let { it1 -> favoriteDataBase.remove_fav(it1) }
            removeItem(position)
        })


        // Display  Avatar
        Glide.with(context)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_image_24)
                .skipMemoryCache(false)
                .into(holder.mArticleAvatar)
    }

    class ViewHolder(view: View) :
            RecyclerView.ViewHolder(view) {
        val mArticleAvatar: ImageView
        val mArticleName: TextView
        val mArticleTitle: TextView
        val mArticleDate: TextView
        val mArticleDescription: TextView
        val mArticleFavoris: ImageButton

        init {
            // Enable click on item
            mArticleAvatar = view.findViewById(R.id.item_list_avatar)
            mArticleName = view.findViewById(R.id.item_list_author)
            mArticleTitle = view.findViewById(R.id.item_list_name)
            mArticleDate = view.findViewById(R.id.item_list_date)
            mArticleDescription = view.findViewById(R.id.item_list_desc)
            mArticleFavoris = view.findViewById(R.id.item_list_favorite_button)
        }
    }

    override fun getItemCount(): Int {
        return favorisList.size
    }

    private fun removeItem(position: Int){
        favorisList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, favorisList.size)
    }
}