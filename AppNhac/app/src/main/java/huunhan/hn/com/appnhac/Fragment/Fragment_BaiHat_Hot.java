package huunhan.hn.com.appnhac.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import huunhan.hn.com.appnhac.Adapter.BaihathotAdapter;
import huunhan.hn.com.appnhac.Model.Baihat;
import huunhan.hn.com.appnhac.R;
import huunhan.hn.com.appnhac.Service.APIService;
import huunhan.hn.com.appnhac.Service.Dataservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_BaiHat_Hot extends Fragment {

    View view;
    RecyclerView recyclerviewBaihathot;
    BaihathotAdapter baihathotAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihat_hot, container, false);
        recyclerviewBaihathot = view.findViewById(R.id.recyclerView_baihathot);
        getData();
        return view;
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.getDataBaihatHot();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> baihatArrayList = (ArrayList<Baihat>) response.body();
                baihathotAdapter = new BaihathotAdapter(getActivity(), baihatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerviewBaihathot.setHasFixedSize(true);
                recyclerviewBaihathot.setLayoutManager(linearLayoutManager);
                recyclerviewBaihathot.setAdapter(baihathotAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }
}
