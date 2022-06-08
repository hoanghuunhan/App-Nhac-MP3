package huunhan.hn.com.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import huunhan.hn.com.appnhac.Adapter.DanhsachtheloaitheochudeAdapter;
import huunhan.hn.com.appnhac.Model.ChuDe;
import huunhan.hn.com.appnhac.Model.Theloai;
import huunhan.hn.com.appnhac.R;
import huunhan.hn.com.appnhac.Service.APIService;
import huunhan.hn.com.appnhac.Service.Dataservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {

    ChuDe chuDe;
    Toolbar toolbartheloaitheochude;
    RecyclerView recyclerViewtheloaiTheochude;
    DanhsachtheloaitheochudeAdapter danhsachtheloaitheochudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);

        getDataIntent();
        initData();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Theloai>> callback = dataservice.getTheloaiTheochude(chuDe.getIdChude());
        callback.enqueue(new Callback<List<Theloai>>() {
            @Override
            public void onResponse(Call<List<Theloai>> call, Response<List<Theloai>> response) {
                ArrayList<Theloai> mangTheloai = (ArrayList<Theloai>) response.body();
                danhsachtheloaitheochudeAdapter = new DanhsachtheloaitheochudeAdapter(getApplicationContext(), mangTheloai);
                recyclerViewtheloaiTheochude.setAdapter(danhsachtheloaitheochudeAdapter);
                recyclerViewtheloaiTheochude.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                recyclerViewtheloaiTheochude.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<Theloai>> call, Throwable t) {

            }
        });
    }

    private void initData() {
        toolbartheloaitheochude = findViewById(R.id.toolbar_theloaitheochude);
        recyclerViewtheloaiTheochude = findViewById(R.id.recyclerView_theloaitheochude);
        setSupportActionBar(toolbartheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChude());
        toolbartheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("chude")) {
                chuDe = (ChuDe) intent.getSerializableExtra("chude");
            }
        }
    }
}