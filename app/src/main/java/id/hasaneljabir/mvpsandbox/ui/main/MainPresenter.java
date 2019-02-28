package id.hasaneljabir.mvpsandbox.ui.main;

import android.util.Log;

import id.hasaneljabir.mvpsandbox.model.movie.MovieResponse;
import id.hasaneljabir.mvpsandbox.network.NetworkClient;
import id.hasaneljabir.mvpsandbox.network.NetworkInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainPresenterInterface {
    MainViewInterface mainViewInterface;
    private String TAG = "MainPresenter";

    public MainPresenter(MainViewInterface mainViewInterface) {
        this.mainViewInterface = mainViewInterface;
    }

    @Override
    public void getMovies() {
        getObservable().subscribeWith(getObserver());
    }

    public Observable<MovieResponse> getObservable() {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getMovies("004cbaf19212094e32aa9ef6f6577f22")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<MovieResponse> getObserver() {
        return new DisposableObserver<MovieResponse>() {
            @Override
            public void onNext(@NonNull MovieResponse movieResponse) {
                Log.d(TAG, "OnNext" + movieResponse.getTotalResults());
                mainViewInterface.displayMovies(movieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "Error" + e);
                e.printStackTrace();
                mainViewInterface.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Completed");
                mainViewInterface.hideProgressBar();
            }
        };
    }
}