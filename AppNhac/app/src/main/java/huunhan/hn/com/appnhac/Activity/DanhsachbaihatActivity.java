package huunhan.hn.com.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import huunhan.hn.com.appnhac.Adapter.DanhsachbaihatAdapter;
import huunhan.hn.com.appnhac.Model.Album;
import huunhan.hn.com.appnhac.Model.Baihat;
import huunhan.hn.com.appnhac.Model.Playlist;
import huunhan.hn.com.appnhac.Model.Quangcao;
import huunhan.hn.com.appnhac.Model.Theloai;
import huunhan.hn.com.appnhac.R;
import huunhan.hn.com.appnhac.Service.APIService;
import huunhan.hn.com.appnhac.Service.Dataservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {

    Quangcao quangcao;
    Playlist playlist;
    Theloai theloai;
    Album album;

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhsachbaihat;
    FloatingActionButton floatingActionButton;
    ImageView ivDanhsachcakhuc;
    ArrayList<Baihat> mangBaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        anhXa();
        dataIntent();
        initData();
        if (quangcao != null && !quangcao.getTenBaihat().equals("")) {
            setValueInView(quangcao.getTenBaihat(), quangcao.getHinhbaihat());
            getDataQuangcao(quangcao.getIdQuangCao());
        }
        if (playlist != null && !playlist.getTen().equals("")) {
            setValueInView(playlist.getTen(), playlist.getIcon());
            getDataPlaylist(playlist.getIdPlaylist());
        }
        if (theloai != null && !theloai.getTenTheloai().equals("")) {
            setValueInView(theloai.getTenTheloai(), theloai.getHinhTheloai());
            getDataTheloai();
        }
        if (album != null && !album.getTenAlbum().equals("")) {
            setValueInView(album.getTenAlbum(), album.getHinhAlbum());
            getDataAlbum();
        }
    }

    private void getDataAlbum() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.getDanhsachbaihattheoAlbum(album.getIdAlbum());
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(getApplicationContext(), mangBaihat);
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void getDataTheloai() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.getDanhsachBaihattheoTheloai(theloai.getIdTheloai());
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(getApplicationContext(), mangBaihat);
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void getDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.getDanhsachBaihatTheoPlaylist(idplaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangBaihat);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void getDataQuangcao(String idQuangCao) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.getDanhsachBaihatTheoQuangCao(idQuangCao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangBaihat);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(ivDanhsachcakhuc);
    }

    private void initData() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void anhXa() {
        ivDanhsachcakhuc = findViewById(R.id.iv_danhsachcakhuc);
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        toolbar = findViewById(R.id.toolbar_danhsach);
        recyclerViewDanhsachbaihat = findViewById(R.id.recyclerview_danhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingaction);
    }

    private void dataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")){
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
            }
            if (intent.hasExtra("itemplaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            if (intent.hasExtra("idtheloai")) {
                theloai = (Theloai) intent.getSerializableExtra("idtheloai");
            }
            if (intent.hasExtra("idalbum")) {
                album = (Album) intent.getSerializableExtra("idalbum");
            }
        }
    }

    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayNhacActivity.class);
                intent.putExtra("cacbaihat", mangBaihat);
                startActivity(intent);
            }
        });
    }
}