package apps.pixel.al.egykey.network;


import java.util.List;

import apps.pixel.al.egykey.models.ResponseHomeOthers;
import apps.pixel.al.egykey.models.job.JobModel;
import apps.pixel.al.egykey.models.jobDetails.JobDetailsModel;
import apps.pixel.al.egykey.models.retaurants.Restaurants;
import apps.pixel.al.egykey.models.selectedRestaurant.SelectedRestaurant;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitInterface {

    @GET("api/Resturant")
    Observable<List<Restaurants>> getAllRestaurants();

    @GET("api/ResturantDetails/{id}")
    Observable<SelectedRestaurant> getSelectedRestaurant(@Path("id") String id);

    @GET("api/Call/{id}")
    Observable<ResponseHomeOthers> getCalls(@Path("id") String id);

    @GET("/api/Share/{id}")
    Observable<ResponseHomeOthers> getShare(@Path("id") String id);

    @GET("/api/Favourits/{id}")
    Observable<ResponseHomeOthers> getFavourite(@Path("id") String id);

    @GET("api/UnLike/{id}")
    Observable<ResponseHomeOthers> getUnLike(@Path("id") String id);

    @GET("api/Jobs/{id}")
    Observable<List<JobModel>> getAllJobs(@Path("id") String id);

    @GET("api/JobDetails/{id}")
    Observable<JobDetailsModel> getJobDetails(@Path("id") String id);

    @GET("api/RestaurantSearch")
    Observable<List<Restaurants>> searchOnRestaurant(@Query("Search") String name);

}