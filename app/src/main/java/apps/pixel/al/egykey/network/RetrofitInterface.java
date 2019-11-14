package apps.pixel.al.egykey.network;


import java.util.List;

import apps.pixel.al.egykey.models.ResponseHomeOthers;
import apps.pixel.al.egykey.models.job.JobModel;
import apps.pixel.al.egykey.models.jobDetails.JobDetailsModel;
import apps.pixel.al.egykey.models.retaurants.SelectedCat;
import apps.pixel.al.egykey.models.selectedRestaurant.SelectedRestaurant;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitInterface {

    @GET("api/Resturant")
    Observable<List<SelectedCat>> getAllRestaurants();

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
    Observable<List<SelectedCat>> searchOnRestaurant(@Query("Search") String name);

    //Beauty
    //BeautyCenter
    @GET("api/BeautyCenter")
    Observable<List<SelectedCat>> getAllBeautyCenterData();

    @GET("api/BeautyDetails/{id}")
    Observable<SelectedRestaurant> getAllBeautyData(@Path("id") String id);

    @GET("api/Beauty/Call/{id}")
    Observable<ResponseHomeOthers> getCallsBeauty(@Path("id") String id);

    @GET("api/Beauty/Favourits/{id}")
    Observable<ResponseHomeOthers> getLikeBeauty(@Path("id") String id);

    @GET("api/Beauty/UnLike/{id}")
    Observable<ResponseHomeOthers> getUnLikeBeauty(@Path("id") String id);

    @GET("api/Beauty/Share/{id}")
    Observable<ResponseHomeOthers> getShareBeauty(@Path("id") String id);

    @GET("api/BeautySearch")
    Observable<List<SelectedCat>> searchOnBeauty(@Query("Search") String name);
    //GYM
    @GET("api/Gym")
    Observable<List<SelectedCat>> getAllGyms();

    @GET("api/GymDetails/{id}")
    Observable<SelectedRestaurant> getAllGymData(@Path("id") String id);

    @GET("api/Gym/Call/{id}")
    Observable<ResponseHomeOthers> getCallsGym(@Path("id") String id);

    @GET("api/Gym/Favourits/{id}")
    Observable<ResponseHomeOthers> getLikeGym(@Path("id") String id);

    @GET("api/Gym/UnLike/{id}")
    Observable<ResponseHomeOthers> getUnLikeGym(@Path("id") String id);

    @GET("api/Gym/Share/{id}")
    Observable<ResponseHomeOthers> getShareGym(@Path("id") String id);

    @GET("api/GymSearch")
    Observable<List<SelectedCat>> searchOnGym(@Query("Search") String name);

    //hospital
    @GET("api/Hospital")
    Observable<List<SelectedCat>> getAllHospital();

    @GET("api/HospitalDetails/{id}")
    Observable<SelectedRestaurant> getHospitalDetails(@Path("id") String id);

    @GET("api/Hospital/Call/{id}")
    Observable<ResponseHomeOthers> getCallsHospital(@Path("id") String id);

    @GET("api/Hospital/Favourits/{id}")
    Observable<ResponseHomeOthers> getLikeHospital(@Path("id") String id);

    @GET("api/Hospital/UnLike/{id}")
    Observable<ResponseHomeOthers> getUnLikeHospital(@Path("id") String id);

    @GET("api/Hospital/Share/{id}")
    Observable<ResponseHomeOthers> getShareHospital(@Path("id") String id);

    @GET("api/HospitalSearch")
    Observable<List<SelectedCat>> searchOnHospital(@Query("Search") String name);

    //Pharmacy
    @GET("api/Pharmacy")
    Observable<List<SelectedCat>> getAllPharmacy();

    @GET("api/PharmacyDetails/{id}")
    Observable<SelectedRestaurant> getPharmacyDetails(@Path("id") String id);

    @GET("api/Pharmacy/Call/{id}")
    Observable<ResponseHomeOthers> getCallsPharmacy(@Path("id") String id);

    @GET("api/Pharmacy/Favourits/{id}")
    Observable<ResponseHomeOthers> getLikePharmacy(@Path("id") String id);

    @GET("api/Pharmacy/UnLike/{id}")
    Observable<ResponseHomeOthers> getUnLikePharmacy(@Path("id") String id);

    @GET("api/Pharmacy/Share/{id}")
    Observable<ResponseHomeOthers> getSharePharmacy(@Path("id") String id);

    @GET("api/PharmacySearch")
    Observable<List<SelectedCat>> searchOnPharmacy(@Query("Search") String name);


}