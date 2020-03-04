package apps.pixel.the.key.network;


import java.util.List;

import apps.pixel.the.key.models.ResponseHomeOthers;
import apps.pixel.the.key.models.coupon.CouponModel;
import apps.pixel.the.key.models.externalLogin.RequestModel;
import apps.pixel.the.key.models.home.sliderResponse;
import apps.pixel.the.key.models.job.JobModel;
import apps.pixel.the.key.models.jobDetails.JobDetailsModel;
import apps.pixel.the.key.models.login.LoginRequestModel;
import apps.pixel.the.key.models.login.UserModel;
import apps.pixel.the.key.models.menu.MainMenu;
import apps.pixel.the.key.models.menu.SubMenu;
import apps.pixel.the.key.models.menu.SubMenuDetails;
import apps.pixel.the.key.models.offers.OffersModel;
import apps.pixel.the.key.models.register.RegisterRequestModel;
import apps.pixel.the.key.models.register.RegisterResponseModel;
import apps.pixel.the.key.models.retaurants.SelectedCat;
import apps.pixel.the.key.models.selectedRestaurant.SelectedRestaurant;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitInterface {


    //login
    @POST("api/Account/Login")
    Observable<UserModel> Login(@Body LoginRequestModel loginRequestModel);

    // Register
    @POST("api/Account/Register")
    Observable<UserModel> Register(@Body RegisterRequestModel registerRequestModel);


    //SocialLogin
    @POST("api/Account/SocialLogin")
    Observable<UserModel> loginSocialMedia(@Body RequestModel RequestModel);


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

    @GET("api/RestaurantCoupons/{id}")
    Observable<CouponModel> getCoupon(@Path("id") String id);

    @GET("api/RestaurantOffers/GetAllOffers/{id}")
    Observable<List<OffersModel>> getAllOffers(@Path("id") String id);

    @GET("api/JobDetails/{id}")
    Observable<JobDetailsModel> getJobDetails(@Path("id") String id);

    @GET("api/RestaurantSearch")
    Observable<List<SelectedCat>> searchOnRestaurant(@Query("Search") String name);

    @GET("api/sliders")
    Observable<List<sliderResponse>> getSliders();

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

    //http://pixelserver-001-site61.ctempurl.com/
    @GET("api/MenuRestaurant/{id}")
    Observable<List<MainMenu>> getMainMenu(@Path("id") String id);

    @GET("api/SupSupMenu/{id}")
    Observable<List<SubMenu>> getSubMenu(@Path("id") String id, @Query("idMenu") String subId);

    //api/SupSupMenuDetails
    @GET("api/SupSupMenuDetails/{id}")
    Observable<SubMenuDetails> getSubMenuDetails(@Path("id") String id);

}