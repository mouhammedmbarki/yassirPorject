package com.example.mbarki_mohamed_yassir_project.presentation.listMovie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mbarki_mohamed_yassir_project.databinding.ItemMovieBinding
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.movieList.UIMovieListModel
import com.example.mbarki_mohamed_yassir_project.utils.networksecurity.GlideApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ListMovieAdapter @Inject constructor(@ApplicationContext private val application: Context) :
    RecyclerView.Adapter<ListMovieAdapter.ViewHolder>() {
    private var movieList: List<UIMovieListModel> = emptyList()
    private var movieApdaterViewInetcation:MovieApdaterViewInetcation?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(movieList[position])

    override fun getItemCount(): Int = movieList.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(uiMovieListModel: UIMovieListModel) {
            binding.apply {
                uiMovieListModel.also { (imagePath, title, dateRelease,id) ->
                    GlideApp.with(application)
                        .load(imagePath)
                        .into(movieImage)
                    movieTitle.text = title
                    movieDateRelease.text = dateRelease
                    itemContainer.setOnClickListener{
                        movieApdaterViewInetcation?.openHikeMovie(id)
                    }
                }
            }
        }
    }
    fun setMovieApdaterViewInetcation(movieApdaterViewInetcation:MovieApdaterViewInetcation){
        this.movieApdaterViewInetcation=movieApdaterViewInetcation
    }
    fun setData(movieList: List<UIMovieListModel>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }
}