package huunhan.hn.com.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import huunhan.hn.com.appnhac.Adapter.MainViewPagerAdapter;
import huunhan.hn.com.appnhac.Fragment.Fragment_Tim_Kiem;
import huunhan.hn.com.appnhac.Fragment.Fragment_Trang_Chu;
import huunhan.hn.com.appnhac.R;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        initData();

    }

    private void initData() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(), "Trang chu");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(), "Tim kiem");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    private void anhXa() {
        tabLayout = findViewById(R.id.my_tablayout);
        viewPager = findViewById(R.id.my_viewpager);
    }
}