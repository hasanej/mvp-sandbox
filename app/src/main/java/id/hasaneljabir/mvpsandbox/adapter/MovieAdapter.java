package id.hasaneljabir.mvpsandbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.hasaneljabir.mvpsandbox.R;
import id.hasaneljabir.mvpsandbox.model.movie.Result;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    List<Result> movieList;
    Context context;

    public MovieAdapter(List<Result> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        MovieHolder movieHolder = new MovieHolder(view);
        return movieHolder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + movieList.get(position).getPosterPath()).into(holder.ivMovie);
        holder.tvTitle.setText(movieList.get(position).getTitle());
        holder.tvReleaseDate.setText(movieList.get(position).getReleaseDate());
        holder.tvDescription.setText(movieList.get(position).getOverview());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        ImageView ivMovie;
        TextView tvTitle, tvReleaseDate, tvDescription;

        public MovieHolder(View view) {
            super(view);
            ivMovie = view.findViewById(R.id.ivMovie);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvReleaseDate = view.findViewById(R.id.tvReleaseDate);
            tvDescription = view.findViewById(R.id.tvDescription);
        }
    }
}