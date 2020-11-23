package com.example.newsletter.adapters

import android.content.Context
import android.content.SharedPreferences
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
import com.example.newsletter.models.ArticleResponse
import java.text.SimpleDateFormat

class ListArticlesAdapter(
        items: ArticleResponse, val handler: ListArticlesHandler, val context : Context
) : RecyclerView.Adapter<ListArticlesAdapter.ViewHolder>() {
    private val mArticles: ArticleResponse = items
    private lateinit var favoriteDataBase: FavoriteDataBase


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        favoriteDataBase = FavoriteDataBase(context)

        //Create Table

        val prefs: SharedPreferences =
                context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val firstStart = prefs.getBoolean("firstStart", true)

        if (firstStart) {
            createTableOnFirstStart()
        }

        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val article: Article = mArticles.articles[position]
        val context = holder.itemView.context
        val sdfPattern = SimpleDateFormat("yyMMddHHmmssSSS")
        val dateId: java.util.Date = article.publishedAt
        val idString = sdfPattern.format(dateId)
        article.id = idString
        readCursorData(article,holder)


        holder.mArticleTitle.text = article.title
        holder.mArticleDescription.text = article.description
        holder.mArticleName.text    = article.author

        val sdfOut = SimpleDateFormat("dd-MM-yyyy")
        val date: java.util.Date = article.publishedAt
        val dateString = sdfOut.format(date)
        holder.mArticleDate.text = dateString



        holder.mArticleTitle.setOnClickListener {
            handler.showArticle(article)
        }
        holder.mArticleDescription.setOnClickListener {
            handler.showArticle(article)
        }
        holder.mArticleFavoris.setOnClickListener {
            if (article.favoris == 0 ){
                holder.mArticleFavoris.setImageResource(R.drawable.ic_baseline_favoris_filled_24)
                article.favoris = 1
                favoriteDataBase.insertIntoTheDatabase(
                        if (article.id!=null) article.id else "",
                        if (article.title!=null) article.title else "",
                        if (article.description!=null) article.description else "",
                        if (article.author!=null) article.author else "",
                        if (article.urlToImage!=null) article.urlToImage else "",
                        if (article.url!=null) article.url else "",
                        1)

            }
            else
            {
                article.favoris = 0
                favoriteDataBase.remove_fav(article.id)
                holder.mArticleFavoris.setImageResource(R.drawable.ic_baseline_favoris_empty_24)
            }
        }
        holder.mArticleAvatar.setOnClickListener {
            handler.showArticle(article)
        }

        // Display  Avatar
        Glide.with(context)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_image_24)
                .skipMemoryCache(false)
                .into(holder.mArticleAvatar)
    }

    override fun getItemCount(): Int {
        return mArticles.articles.size
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

    private fun createTableOnFirstStart() {
        val prefs: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("firstStart", false)
        editor.apply()
    }

    private fun readCursorData(
            article: Article,
            viewHolder: ViewHolder
    ) {
        val cursor = favoriteDataBase.read_all_data(article.id)
        val db = favoriteDataBase.readableDatabase
        try {
            while (cursor.moveToNext()) {
                val item_fav_status =
                        cursor.getInt(cursor.getColumnIndex(FavoriteDataBase.FAVORITE_STATUS))
                article.favoris = item_fav_status

                //check fav status
                if (item_fav_status != null && item_fav_status == 1) {
                    viewHolder.mArticleFavoris.setImageResource(R.drawable.ic_baseline_favoris_filled_24)

                } else if (item_fav_status != null && item_fav_status == 0) {
                    viewHolder.mArticleFavoris.setImageResource(R.drawable.ic_baseline_favoris_empty_24)
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed) cursor.close()
            db.close()
        }
    }
}