package huunhan.hn.com.appnhac.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> arrayListFragment = new ArrayList<>();
    private ArrayList<String> arrayTitle = new ArrayList<>();

    public MainViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrayListFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayListFragment.size();
    }
    public void addFragment(Fragment fragment, String title) {
        arrayListFragment.add(fragment);
        arrayTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position);
    }
}
