package id.hasaneljabir.mvpsandbox.network;

import id.hasaneljabir.mvpsandbox.model.movie.MovieResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {
    @GET("discover/movie")
    Observable<MovieResponse> getMovies(@Query("api_key") String api_key);
}