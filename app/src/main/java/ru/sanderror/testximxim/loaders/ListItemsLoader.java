package ru.sanderror.testximxim.loaders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.sanderror.testximxim.inerfaces.OnItemsLoaded;
import ru.sanderror.testximxim.inerfaces.petApi;
import ru.sanderror.testximxim.models.ListItem;
import ru.sanderror.testximxim.models.PostModel;

public class ListItemsLoader {

    private petApi mPetApi;

    public ListItemsLoader(String baseUrl) {
        Retrofit retrofit;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mPetApi = retrofit.create(petApi.class);
    }

    public void loadData(String query, final OnItemsLoaded callback){
        mPetApi
            .getData(query)
            .enqueue(new Callback<PostModel>() {
                @Override
                public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                    List<ListItem> posts = new ArrayList<>();
                    posts.addAll(response.body().getData());
                    callback.onLoaded(posts);
                }

                @Override
                public void onFailure(Call<PostModel> call, Throwable t) {
                    t.printStackTrace();
                }
            });
    }
}
