package ru.sanderror.testximxim.framents;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.List;

import ru.sanderror.testximxim.R;
import ru.sanderror.testximxim.models.ListItem;

public class ItemDetailFragment extends Fragment {
    private ListItem mListItem;
    private int mNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_detail, container, false);

        TextView tvNumber = view.findViewById(R.id.post_number);
        TextView tvTitle = view.findViewById(R.id.post_title);
        ImageView ivImage = view.findViewById(R.id.post_image);

        if(savedInstanceState != null) {
            String itemString = savedInstanceState.getString("item_detail");
            Gson gson = new Gson();
            Type type = new TypeToken<ListItem>(){}.getType();
            mListItem = gson.fromJson(itemString, type);
            mNumber = savedInstanceState.getInt("item_number");
        }

        tvNumber.setText(String.valueOf(mNumber));
        tvTitle.setText(mListItem.getTitle());
        Picasso.with(getActivity())
                .load(mListItem.getUrl())
                .placeholder( R.drawable.progress_animation )
                .into(ivImage);

        return view;
    }

    public ItemDetailFragment SetPet(ListItem pet, int number){
        this.mListItem = pet;
        this.mNumber = number;

        return this;
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String jsonData = new Gson().toJson(mListItem);

        outState.putString("item_detail", jsonData);
        outState.putInt("item_number", mNumber);
    }
}