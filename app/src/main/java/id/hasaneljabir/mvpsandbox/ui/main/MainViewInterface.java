package id.hasaneljabir.mvpsandbox.ui.main;

import id.hasaneljabir.mvpsandbox.model.movie.MovieResponse;

public interface MainViewInterface {
    void showToast(String s);

    void showProgressBar();

    void hideProgressBar();

    void displayMovies(MovieResponse movieResponse);

    void displayError(String s);
}