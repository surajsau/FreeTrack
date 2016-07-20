package com.halfplatepoha.freetrack.network.api;

import com.halfplatepoha.freetrack.network.model.request.LoginRequest;
import com.halfplatepoha.freetrack.network.model.request.ProvideTrackingRequest;
import com.halfplatepoha.freetrack.network.model.request.SendGPSRequest;
import com.halfplatepoha.freetrack.network.model.response.ConsumerLatestEntity;
import com.halfplatepoha.freetrack.network.model.response.ProvideTrackingResponse;
import com.halfplatepoha.freetrack.network.model.response.RouteEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by surajkumarsau on 20/07/16.
 */
public interface FTApi {

    @GET("consumer/track/location")
    Call<ConsumerLatestEntity> getProviderLatestLocation(@Query("tid")Long id);

    @GET("consumer/trackings")
    Call<RouteEntity> getAllActiveTracking();

    @POST("provider/tracking/location")
    Call<Void> sendGpsLocation(@Body SendGPSRequest request);

    @POST("provider/track")
    Call<ProvideTrackingResponse> provideTracking(@Body ProvideTrackingRequest request);

    @POST("user/login")
    Call<Void> login(@Body LoginRequest request);

    @Headers("Content-Type: application/json;charset=UTF-8")
    @PUT("provider/stop/track")
    Call<Void> stopTracking(@Query("tid") String trackingId);
}
