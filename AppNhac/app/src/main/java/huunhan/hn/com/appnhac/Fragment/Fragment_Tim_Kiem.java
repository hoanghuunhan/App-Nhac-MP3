package huunhan.hn.com.appnhac.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import huunhan.hn.com.appnhac.Adapter.SearchBaiHatAdapter;
import huunhan.hn.com.appnhac.Model.Baihat;
import huunhan.hn.com.appnhac.R;
import huunhan.hn.com.appnhac.Service.APIService;
import huunhan.hn.com.appnhac.Service.Dataservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {

    View view;
    Toolbar toolbarSearchBaihat;
    RecyclerView recyclerViewSearchbathat;
    TextView tvKhongcodulieu;
    SearchBaiHatAdapter searchBaiHatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timkiem, container, false);
        toolbarSearchBaihat = view.findViewById(R.id.toolbar_seach_baihat);
        recyclerViewSearchbathat = view.findViewById(R.id.recyclerView_search_bai_hat);
        tvKhongcodulieu = view.findViewById(R.id.tv_khong_du_lieu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarSearchBaihat);
        toolbarSearchBaihat.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_Search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchTuKhoaBaiHat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void SearchTuKhoaBaiHat(String query) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.getSearchBaiHat(query);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> mangbaihat = (ArrayList<Baihat>) response.body();
                if (mangbaihat.size() > 0) {
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(), mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewSearchbathat.setLayoutManager(linearLayoutManager);
                    recyclerViewSearchbathat.setAdapter(searchBaiHatAdapter);
                    tvKhongcodulieu.setVisibility(View.GONE);
                    recyclerViewSearchbathat.setVisibility(View.VISIBLE);
                } else {
                    recyclerViewSearchbathat.setVisibility(View.GONE);
                    tvKhongcodulieu.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
