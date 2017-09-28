package ru.sanderror.testximxim.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.sanderror.testximxim.R;
import ru.sanderror.testximxim.framents.TabsFragment;

public class MainActivity extends AppCompatActivity {
    private TabsFragment mTabsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            mTabsFragment = new TabsFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, mTabsFragment)
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mTabsFragment != null && mTabsFragment.isAdded())
            getSupportFragmentManager()
                .putFragment(outState, "tabFragment", mTabsFragment);
    }
}
