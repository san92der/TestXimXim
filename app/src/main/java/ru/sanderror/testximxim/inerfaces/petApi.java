package ru.sanderror.testximxim.inerfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.sanderror.testximxim.models.PostModel;

public interface petApi {
    @GET("xim/api.php")
    Call<PostModel> getData(@Query("query") String query);
}