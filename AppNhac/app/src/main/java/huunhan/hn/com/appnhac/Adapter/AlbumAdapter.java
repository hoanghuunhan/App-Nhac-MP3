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

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> albums;

    public AlbumAdapter(Context context, ArrayList<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_album, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albums.get(position);
        holder.tvTenAlbum.setText(album.getTenAlbum());
        holder.tvTenCasiAlbum.setText(album.getTenCasiAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.ivHinhAlbum);

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHinhAlbum;
        TextView tvTenAlbum, tvTenCasiAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHinhAlbum = itemView.findViewById(R.id.iv_album);
            tvTenAlbum = itemView.findViewById(R.id.tv_ten_album);
            tvTenCasiAlbum = itemView.findViewById(R.id.tv_ten_casi_album);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("idalbum", albums.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
