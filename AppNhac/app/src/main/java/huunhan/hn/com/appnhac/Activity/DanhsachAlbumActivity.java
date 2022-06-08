package huunhan.hn.com.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import huunhan.hn.com.appnhac.Adapter.AllAlbumAdapter;
import huunhan.hn.com.appnhac.Model.Album;
import huunhan.hn.com.appnhac.R;
import huunhan.hn.com.appnhac.Service.APIService;
import huunhan.hn.com.appnhac.Service.Dataservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachAlbumActivity extends AppCompatActivity {

    Toolbar toolbarAllAlbum;
    RecyclerView recyclerViewAllAlbum;
    AllAlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_album);

        anhXa();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.getDanhsachAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> mangAlbum = (ArrayList<Album>) response.body();
                albumAdapter = new AllAlbumAdapter(getApplicationContext(), mangAlbum);
                recyclerViewAllAlbum.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                recyclerViewAllAlbum.setAdapter(albumAdapter);
                recyclerViewAllAlbum.setHasFixedSize(true);

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void anhXa() {
        toolbarAllAlbum = findViewById(R.id.toolbar_allalbum);
        recyclerViewAllAlbum = findViewById(R.id.recyclerView_allalbum);
        setSupportActionBar(toolbarAllAlbum);
        getSupportActionBar().setTitle("Danh sach Album");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAllAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}