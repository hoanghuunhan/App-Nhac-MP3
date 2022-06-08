package huunhan.hn.com.appnhac.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import huunhan.hn.com.appnhac.Activity.DanhsachbaihatActivity;
import huunhan.hn.com.appnhac.Activity.PlayNhacActivity;
import huunhan.hn.com.appnhac.Adapter.PlayNhacAdapter;
import huunhan.hn.com.appnhac.R;

public class Fragment_Play_Danh_Sach_Bai_Hat extends Fragment {
    View view;
    RecyclerView recyclerViewPlaynhac;
    PlayNhacAdapter playNhacAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_cac_bai_hat,container, false);
        recyclerViewPlaynhac = view.findViewById(R.id.recyclerView_play_baihat);
        if (PlayNhacActivity.mangBaihat.size() > 0) {
            playNhacAdapter = new PlayNhacAdapter(getActivity(), PlayNhacActivity.mangBaihat);
            recyclerViewPlaynhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlaynhac.setAdapter(playNhacAdapter);
        }

        return view;
    }
}
