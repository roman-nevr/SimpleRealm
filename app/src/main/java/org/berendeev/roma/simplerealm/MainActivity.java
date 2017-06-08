package org.berendeev.roma.simplerealm;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import org.berendeev.roma.simplerealm.presentation.ui.adapter.FragmentsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.view_pager) ViewPager viewPager;

    private FragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);

        initViewPager();
    }

    private void initViewPager() {
        pagerAdapter = new FragmentsPagerAdapter(getSupportFragmentManager(), getTitleList(tabLayout));

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    private List<String> getTitleList(TabLayout tabLayout) {
        List<String> titles = new ArrayList<>();
        for (int index = 0; index < tabLayout.getTabCount(); index++) {
            titles.add(tabLayout.getTabAt(index).getText().toString());
        }
        return titles;
    }
}
