package com.kingcorp.miniportal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kingcorp.miniportal.R;
import com.kingcorp.miniportal.listeners.OnMoviePosterClickListener;
import com.kingcorp.miniportal.models.MovieShortInfo;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder> {
    private ArrayList<MovieShortInfo> mMoviesList = new ArrayList<>();

    private OnMoviePosterClickListener mMovieClickListener;

    public MoviesListAdapter(OnMoviePosterClickListener movieClickListener) {
        this.mMovieClickListener = movieClickListener;
    }

    @NonNull
    @Override
    public MoviesListAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MoviesListAdapter.MovieViewHolder viewHolder = new MovieViewHolder(inflater.inflate(R.layout.item_movie_poster, parent, false));;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesListAdapter.MovieViewHolder holder, int position) {
        MovieShortInfo movie = mMoviesList.get(position);
        holder.bind(movie.getMovieFiles().getPosterUrl(), movie.getTitle(), movie.getId());
    }

    @Override
    public int getItemCount() {
        if (mMoviesList != null)
            return mMoviesList.size();
        else
            return 0;
    }

    public void add(MovieShortInfo movie) {
        mMoviesList.add(movie);
        notifyItemInserted(mMoviesList.size() - 1);
    }

    public void addAll(ArrayList<MovieShortInfo> moviesList) {
        for (MovieShortInfo movie : moviesList) {
            add(movie);
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView poster;
        TextView title;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);

        }

        public void bind(String imgUrl, String titleText, int id) {
            Glide.with(itemView)
                    .load(imgUrl)
                    .centerCrop()
                    .into(poster);

            title.setText(titleText);

            itemView.setOnClickListener(view -> mMovieClickListener.onMoviePosterClick(id));
        }
    }
}
