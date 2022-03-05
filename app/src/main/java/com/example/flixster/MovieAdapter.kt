package com.example.flixster

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val MOVIE_EXTRA = "MOVIE_EXTRA"
class MovieAdapter(private val context: Context, private val movies: MutableList<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val ivPoster = itemView.findViewById<ImageView>(R.id.imageView)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)
        fun bind(movie: Movie){
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            Glide.with(context).load(movie.posterImageUrl).into(ivPoster)

        }
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val movie =movies[adapterPosition]
            //Toast.makeText(context,movie.title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("movie_title",movie.title)
            intent.putExtra(MOVIE_EXTRA,movie)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
