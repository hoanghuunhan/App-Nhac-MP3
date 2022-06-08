package huunhan.hn.com.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import huunhan.hn.com.appnhac.Activity.DanhsachbaihatActivity;
import huunhan.hn.com.appnhac.Model.Quangcao;
import huunhan.hn.com.appnhac.R;

public class BannerAdapter extends PagerAdapter {

    Context context;
    ArrayList<Quangcao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<Quangcao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);

        ImageView ivBackgroundBanner = view.findViewById(R.id.iv_backgroundbanner);
        ImageView ivSongBanner = view.findViewById(R.id.iv_banner);
        TextView tvTitleBanner = view.findViewById(R.id.tv_title_banner);
        TextView tvNoidung = view.findViewById(R.id.tv_noidung);

        Picasso.with(context).load(arrayListBanner.get(position).getHinhanh()).into(ivBackgroundBanner);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhbaihat()).into(ivSongBanner);
        tvTitleBanner.setText(arrayListBanner.get(position).getTenBaihat());
        tvNoidung.setText(arrayListBanner.get(position).getNoidung());
        
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("banner", arrayListBanner.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
