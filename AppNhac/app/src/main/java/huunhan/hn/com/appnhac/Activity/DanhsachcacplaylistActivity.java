package huunhan.hn.com.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import huunhan.hn.com.appnhac.Adapter.DanhsachcacPlaylistAdapter;
import huunhan.hn.com.appnhac.Model.Playlist;
import huunhan.hn.com.appnhac.R;
import huunhan.hn.com.appnhac.Service.APIService;
import huunhan.hn.com.appnhac.Service.Dataservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachcacplaylistActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewDanhsachcacplaylist;
    DanhsachcacPlaylistAdapter danhsachcacPlaylistAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacplaylist);

        anhXa();
        initData();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.getDataDanhsachcacPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangPlaylist = (ArrayList<Playlist>) response.body();
                danhsachcacPlaylistAdapter = new DanhsachcacPlaylistAdapter(getApplicationContext(), mangPlaylist);
                recyclerViewDanhsachcacplaylist.setAdapter(danhsachcacPlaylistAdapter);
                recyclerViewDanhsachcacplaylist.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void initData() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play Lists");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhXa() {
        toolbar = findViewById(R.id.toolbar_danhsachcacplaylist);
        recyclerViewDanhsachcacplaylist = findViewById(R.id.recyclerView_danhsachcacplaylist);

    }
}