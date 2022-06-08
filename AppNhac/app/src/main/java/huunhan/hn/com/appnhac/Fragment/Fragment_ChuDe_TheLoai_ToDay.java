package huunhan.hn.com.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import huunhan.hn.com.appnhac.Activity.DanhsachbaihatActivity;
import huunhan.hn.com.appnhac.Activity.DanhsachcacchudeActivity;
import huunhan.hn.com.appnhac.Activity.DanhsachtheloaitheochudeActivity;
import huunhan.hn.com.appnhac.Model.ChuDe;
import huunhan.hn.com.appnhac.Model.ChudeVaTheloai;
import huunhan.hn.com.appnhac.Model.Playlist;
import huunhan.hn.com.appnhac.Model.Theloai;
import huunhan.hn.com.appnhac.R;
import huunhan.hn.com.appnhac.Service.APIService;
import huunhan.hn.com.appnhac.Service.Dataservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_ToDay extends Fragment {

    View view;
    HorizontalScrollView horizontalScrollView;
    TextView tvXemthem;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        tvXemthem = view.findViewById(R.id.tv_xemthem);
        tvXemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachcacchudeActivity.class);
                startActivity(intent);
            }
        });
        getData();
        return view;
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<ChudeVaTheloai> callback = dataservice.getDataChudeVaTheloai();
        callback.enqueue(new Callback<ChudeVaTheloai>() {
            @Override
            public void onResponse(Call<ChudeVaTheloai> call, Response<ChudeVaTheloai> response) {
                ChudeVaTheloai chudeVaTheloai = response.body();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(chudeVaTheloai.getChuDe());

                final ArrayList<Theloai> theloaiArrayList = new ArrayList<>();
                theloaiArrayList.addAll(chudeVaTheloai.getTheloai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(700,250);
                layoutParams1.setMargins(10,20,10,30);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(250,250);
                layoutParams2.setMargins(10,20,10,30);

                for (int i=0; i<chuDeArrayList.size(); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getIdChude() != null) {
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhchude()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams1);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude", chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                for (int j=0; j<theloaiArrayList.size(); j++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theloaiArrayList.get(j).getIdTheloai() != null) {
                        Picasso.with(getActivity()).load(theloaiArrayList.get(j).getHinhTheloai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams2);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai", theloaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }

                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChudeVaTheloai> call, Throwable t) {
                Log.d("chude", t.getMessage());
            }
        });
    }
}
