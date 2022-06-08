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
import huunhan.hn.com.appnhac.Model.Album;
import huunhan.hn.com.appnhac.R;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> albumArrayList;

    public AllAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_all_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        holder.tvTenAlbum.setText(album.getTenAlbum());
        holder.tvTenCasi.setText(album.getTenCasiAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.ivAllAlbum);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAllAlbum;
        TextView tvTenCasi, tvTenAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAllAlbum = itemView.findViewById(R.id.iv_all_album);
            tvTenAlbum = itemView.findViewById(R.id.tv_ten_all_album);
            tvTenCasi = itemView.findViewById(R.id.tv_ten_casi_all_album);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("idalbum", albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
