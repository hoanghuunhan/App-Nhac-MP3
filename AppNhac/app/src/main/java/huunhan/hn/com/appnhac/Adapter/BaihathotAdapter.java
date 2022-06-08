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

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.Viewholder> {

    Context context;
    ArrayList<Baihat> baihatArrayList;

    public BaihathotAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_baihathot, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Baihat baihat = baihatArrayList.get(position);
        holder.tvTen.setText(baihat.getTenbaihat());
        holder.tvCasi.setText(baihat.getCasi());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.ivHinh);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView ivHinh, ivLuotthich;
        TextView tvTen, tvCasi;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tv_ten_baihathot);
            tvCasi = itemView.findViewById(R.id.tv_ten_casi_baihathot);
            ivHinh = itemView.findViewById(R.id.iv_baihathot);
            ivLuotthich = itemView.findViewById(R.id.iv_luotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

            ivLuotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivLuotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotthich("1", baihatArrayList.get(getPosition()).getIdBaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("Success")){
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
