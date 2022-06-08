package huunhan.hn.com.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huunhan.hn.com.appnhac.Activity.PlayNhacActivity;
import huunhan.hn.com.appnhac.Model.Baihat;
import huunhan.hn.com.appnhac.R;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.Viewholder> {

    Context context;
    ArrayList<Baihat> mangBaihat;


    public PlayNhacAdapter(Context context, ArrayList<Baihat> mangBaihat) {
        this.context = context;
        this.mangBaihat = mangBaihat;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_play_baihat, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Baihat baihat = mangBaihat.get(position);
        holder.tvTencasi.setText(baihat.getCasi());
        holder.tvIndex.setText(position + 1 + "");
        holder.tvTenbaihat.setText(baihat.getTenbaihat());

    }

    @Override
    public int getItemCount() {
        return mangBaihat.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder  {
        TextView tvIndex, tvTencasi, tvTenbaihat;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvIndex = itemView.findViewById(R.id.tv_play_nhac_index);
            tvTenbaihat = itemView.findViewById(R.id.tv_play_nhac_tenbaihat);
            tvTencasi = itemView.findViewById(R.id.tv_play_nhac_tencasi);
        }


    }

}
