package ru.sanderror.testximxim.framents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.sanderror.testximxim.loaders.ListItemsLoader;
import ru.sanderror.testximxim.inerfaces.OnItemsLoaded;
import ru.sanderror.testximxim.inerfaces.OnRecyclerViewItemClickListener;
import ru.sanderror.testximxim.adapters.PostsAdapter;
import ru.sanderror.testximxim.R;
import ru.sanderror.testximxim.loaders.Query;
import ru.sanderror.testximxim.models.ListItem;


public class ListFragment extends Fragment
        implements OnRecyclerViewItemClickListener, OnItemsLoaded {

    private RecyclerView mRecyclerView;
    private PostsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private List<ListItem> mListItems;
    private Query mQuery;

    public ListFragment(){
        mListItems = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);

        mRecyclerView = view.findViewById(R.id.posts_recycle_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PostsAdapter(mListItems, getContext(), this);
        mRecyclerView.setAdapter(mAdapter);

        if(savedInstanceState == null ) {
            LoadData();
        }
        return view;
    }

    public void LoadData(){
        if(mQuery != null && mQuery.getResponse().size() == 0) {
            ListItemsLoader loader = new ListItemsLoader("http://kot3.com/");
            loader.loadData(mQuery.getQuery(), this);
        }
        else {
            mListItems.addAll(mQuery.getResponse());
            mAdapter.notifyDataSetChanged();

            mLayoutManager.scrollToPosition(mQuery.getPosition());
        }
    }

    public ListFragment setQuery(Query query){
        mQuery = query;
        return this;
    }

    @Override
    public void onLoaded(List<ListItem> response) {
        mQuery.setResponse(response);
        mListItems.addAll(response);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        mQuery.setPosition(mLayoutManager.findFirstVisibleItemPosition());
    }

    @Override
    public void onItemClick(ListItem item) {
        getFragmentManager()
            .beginTransaction()
            .replace(R.id.tabs_container, new ItemDetailFragment().SetPet(item,1))
            .addToBackStack(null)
            .commit();
    }


}
