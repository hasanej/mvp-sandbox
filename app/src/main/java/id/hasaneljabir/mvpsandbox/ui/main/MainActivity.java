package id.hasaneljabir.mvpsandbox.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.hasaneljabir.mvpsandbox.R;
import id.hasaneljabir.mvpsandbox.adapter.MovieAdapter;
import id.hasaneljabir.mvpsandbox.model.movie.MovieResponse;

public class MainActivity extends AppCompatActivity implements MainViewInterface {
    @BindView(R.id.rvMovie)
    RecyclerView rvMovie;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    RecyclerView.Adapter adapter;
    MainPresenter mainPresenter;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupMVP();
        setupViews();
        getMovieList();
    }

    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
    }

    private void setupViews() {
        rvMovie.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getMovieList() {
        mainPresenter.getMovies();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayMovies(MovieResponse movieResponse) {
        if (movieResponse != null) {
            Log.d(TAG, movieResponse.getResults().get(1).getTitle());
            adapter = new MovieAdapter(movieResponse.getResults(), MainActivity.this);
            rvMovie.setAdapter(adapter);
        } else {
            Log.d(TAG, "Movies response null");
        }
    }

    @Override
    public void displayError(String e) {
        showToast(e);
    }
}