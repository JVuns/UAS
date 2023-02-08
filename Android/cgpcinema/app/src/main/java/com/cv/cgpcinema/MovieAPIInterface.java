package com.cv.cgpcinema;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieAPIInterface {
    @GET("activity")
    Call<Activity> getActivityAction();
}
