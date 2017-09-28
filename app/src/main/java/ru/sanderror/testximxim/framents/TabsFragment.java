package ru.sanderror.testximxim.framents;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import ru.sanderror.testximxim.R;
import ru.sanderror.testximxim.loaders.Query;
import ru.sanderror.testximxim.models.ListItem;



public class TabsFragment extends Fragment implements TabLayout.OnTabSelectedListener{
    private TabLayout mTabLayout;
    private List<ListFragment> mFragmentList;
    private List<Query> mQueryList;
    private Type listType = new TypeToken<List<Query>>(){}.getType();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment, container, false);

        mTabLayout = view.findViewById(R.id.tab_layout);

        mFragmentList = new ArrayList<>();
        mQueryList = new ArrayList<>();

        mQueryList.add(new Query("cat", new ArrayList<ListItem>()));
        mQueryList.add(new Query("dog", new ArrayList<ListItem>()));

        Stack<String> tabTitles = new Stack<>();
        String[] titles = getResources().getStringArray(R.array.tabs_title_array);
        for(String s : titles) tabTitles.push(s);

        int position = 0;
        if(savedInstanceState != null){
            position = savedInstanceState.getInt("mTabsFragmentPosition", 0);
            String json = savedInstanceState.getString("data");
            mQueryList = new Gson().fromJson(json, listType);
        }
        for(Query query : mQueryList) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitles.pop()));
            mFragmentList.add(new ListFragment().setQuery(query));
        }
        selectTab(position);

        mTabLayout.setOnTabSelectedListener(this);
        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        selectTab(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {}

    @Override
    public void onTabReselected(TabLayout.Tab tab) {}

    public void selectTab(int num){
        replaceFragment(mFragmentList.get(num));
        TabLayout.Tab tab = mTabLayout.getTabAt(num);
        tab.select();
    }

    private void replaceFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public int getCurrentTab(){
        return mTabLayout.getSelectedTabPosition();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data", new Gson().toJson(mQueryList, listType));
        outState.putInt("mTabsFragmentPosition", getCurrentTab());
    }
}
