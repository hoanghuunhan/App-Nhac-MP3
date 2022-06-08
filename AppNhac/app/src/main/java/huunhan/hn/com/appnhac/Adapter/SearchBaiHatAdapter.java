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
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import huunhan.hn.com.appnhac.Activity.PlayNhacActivity;
import huunhan.hn.com.appnhac.Model.Baihat;
import huunhan.hn.com.appnhac.R;
import huunhan.hn.com.appnhac.Service.APIService;
import huunhan.hn.com.appnhac.Service.Dataservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.Viewholder> {
    Context context;
    ArrayList<Baihat> mangbaihat;

    public SearchBaiHatAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_search_bai_hat, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Baihat baihat = mangbaihat.get(position);
        holder.tvTenbaihat.setText(baihat.getTenbaihat());
        holder.tvCasi.setText(baihat.getCasi());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.ivBaihat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tvTenbaihat, tvCasi;
        ImageView ivBaihat, ivLuotthich;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvTenbaihat = itemView.findViewById(R.id.tv_search_tenbaihat);
            tvCasi = itemView.findViewById(R.id.tv_search_tencasi);
            ivBaihat = itemView.findViewById(R.id.iv_search);
            ivLuotthich = itemView.findViewById(R.id.iv_search_luotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
            ivLuotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivLuotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotthich("1", mangbaihat.get(getPosition()).getIdBaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("Success")) {
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    ivLuotthich.setEnabled(false);
                }
            });
        }
    }
}
