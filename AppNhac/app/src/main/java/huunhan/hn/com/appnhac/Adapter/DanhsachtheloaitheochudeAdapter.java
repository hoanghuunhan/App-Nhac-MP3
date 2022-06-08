package huunhan.hn.com.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import huunhan.hn.com.appnhac.Activity.DanhsachbaihatActivity;
import huunhan.hn.com.appnhac.Model.Theloai;
import huunhan.hn.com.appnhac.R;

public class DanhsachtheloaitheochudeAdapter extends RecyclerView.Adapter<DanhsachtheloaitheochudeAdapter.ViewHolder> {

    Context context;
    ArrayList<Theloai> theloaiArrayList;

    public DanhsachtheloaitheochudeAdapter(Context context, ArrayList<Theloai> theloaiArrayList) {
        this.context = context;
        this.theloaiArrayList = theloaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_theloaitheochude, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Theloai theloai = theloaiArrayList.get(position);
        holder.tvTheloaitheochude.setText(theloai.getTenTheloai());
        Picasso.with(context).load(theloai.getHinhTheloai()).into(holder.ivTheloaitheochude);
    }

    @Override
    public int getItemCount() {
        return theloaiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivTheloaitheochude;
        TextView tvTheloaitheochude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTheloaitheochude = itemView.findViewById(R.id.tv_tentheloai_theochude);
            ivTheloaitheochude = itemView.findViewById(R.id.iv_theloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("idtheloai", theloaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
