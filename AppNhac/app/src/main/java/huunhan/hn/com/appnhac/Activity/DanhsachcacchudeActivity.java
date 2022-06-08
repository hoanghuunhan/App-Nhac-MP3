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

import huunhan.hn.com.appnhac.Adapter.DanhsachChudeAdapter;
import huunhan.hn.com.appnhac.Model.ChuDe;
import huunhan.hn.com.appnhac.R;
import huunhan.hn.com.appnhac.Service.APIService;
import huunhan.hn.com.appnhac.Service.Dataservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachcacchudeActivity extends AppCompatActivity {

    Toolbar toolbarcacchude;
    RecyclerView recyclerViewcacchude;
    DanhsachChudeAdapter danhsachChudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacchude);

        anhXa();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<ChuDe>> callback = dataservice.getAllChude();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangChude = (ArrayList<ChuDe>) response.body();
                danhsachChudeAdapter = new DanhsachChudeAdapter(getApplicationContext(), mangChude);
                recyclerViewcacchude.setAdapter(danhsachChudeAdapter);
                recyclerViewcacchude.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void anhXa() {
        toolbarcacchude = findViewById(R.id.toolbar_danhsachchude);
        recyclerViewcacchude = findViewById(R.id.recyclerView_danhsachchude);
        setSupportActionBar(toolbarcacchude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả Chủ đề");
        toolbarcacchude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}