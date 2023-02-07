package com.cv.dosomethingapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ActivityAPIInterface {
    @GET("activity")
    Call<Activity> getActivityAction();
}
