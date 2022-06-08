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
import huunhan.hn.com.appnhac.Model.Playlist;
import huunhan.hn.com.appnhac.R;

public class DanhsachcacPlaylistAdapter extends RecyclerView.Adapter<DanhsachcacPlaylistAdapter.ViewHolder> {

    Context context;
    ArrayList<Playlist> mangPlaylist;

    public DanhsachcacPlaylistAdapter(Context context, ArrayList<Playlist> mangPlaylist) {
        this.context = context;
        this.mangPlaylist = mangPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_danh_sach_cac_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = mangPlaylist.get(position);
        holder.tvDanhsachcacPlaylist.setText(playlist.getTen());
        Picasso.with(context).load(playlist.getIcon()).into(holder.ivDanhsachcacPlaylist);

    }

    @Override
    public int getItemCount() {
        return mangPlaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDanhsachcacPlaylist;
        ImageView ivDanhsachcacPlaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDanhsachcacPlaylist = itemView.findViewById(R.id.tv_danhsachcacplaylist);
            ivDanhsachcacPlaylist = itemView.findViewById(R.id.iv_danhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("itemplaylist", mangPlaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
