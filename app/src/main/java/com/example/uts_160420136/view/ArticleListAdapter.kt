package com.example.uts_160420136.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.ArticleListItemBinding
import com.example.uts_160420136.model.Article
import com.example.uts_160420136.util.ButtonDetailArticle


class ArticleListAdapter(val articleList:ArrayList<Article>) : RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>(),
    ButtonDetailArticle {
    class ArticleViewHolder(val view: ArticleListItemBinding):RecyclerView.ViewHolder(view.root)

    fun updateArticleList(newArticleList:List<Article>) {
        articleList.clear()
        articleList.addAll(newArticleList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ArticleListItemBinding>(inflater, R.layout.article_list_item, parent, false)

        return ArticleViewHolder(v)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.view.article = articleList[position]
        holder.view.detaillistener = this
    }

    override fun onClick(view: View) {
        val action = HomeFragmentDirections.actionArticleDetailFragment(view.tag.toString().toInt())
        Navigation.findNavController(view).navigate(action)
    }

}