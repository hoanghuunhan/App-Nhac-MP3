package huunhan.hn.com.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import huunhan.hn.com.appnhac.Activity.DanhsachtheloaitheochudeActivity;
import huunhan.hn.com.appnhac.Model.ChuDe;
import huunhan.hn.com.appnhac.R;

public class DanhsachChudeAdapter extends RecyclerView.Adapter<DanhsachChudeAdapter.ViewHolder> {

    Context context;
    ArrayList<ChuDe> mangChude;

    public DanhsachChudeAdapter(Context context, ArrayList<ChuDe> mangChude) {
        this.context = context;
        this.mangChude = mangChude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_danhsachchude, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = mangChude.get(position);
        Picasso.with(context).load(chuDe.getHinhchude()).into(holder.ivdachsachchude);
    }

    @Override
    public int getItemCount() {
        return mangChude.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivdachsachchude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivdachsachchude = itemView.findViewById(R.id.iv_dongcacchude);
            ivdachsachchude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachtheloaitheochudeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("chude", mangChude.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
