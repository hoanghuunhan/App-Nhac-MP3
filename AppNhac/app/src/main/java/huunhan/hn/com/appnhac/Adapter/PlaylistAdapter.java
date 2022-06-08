package huunhan.hn.com.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import huunhan.hn.com.appnhac.Model.Playlist;
import huunhan.hn.com.appnhac.R;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {

    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView tvTenPlaylist;
        ImageView ivBackground, ivPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.dong_playlist, null);

            viewHolder.tvTenPlaylist = convertView.findViewById(R.id.tv_ten_playlist);
            viewHolder.ivPlaylist = convertView.findViewById(R.id.iv_playlist);
            viewHolder.ivBackground = convertView.findViewById(R.id.iv_background_playlist);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhPlaylist()).into(viewHolder.ivBackground);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.ivPlaylist);
        viewHolder.tvTenPlaylist.setText(playlist.getTen());

        return convertView;
    }
}
