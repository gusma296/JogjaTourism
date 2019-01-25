package corp.gusma.jogjatourism;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.security.PublicKey;
import java.util.List;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.ViewHolder> {
    List<DataWisata> dataWisata;
    Context context;

    public WisataAdapter(List<DataWisata> DataWisata,Context context){
        this.dataWisata = DataWisata;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wisata_item,parent,false);
        WisataAdapter.ViewHolder viewHolder = new WisataAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataWisata list = dataWisata.get(position);
        holder.nama.setText(list.getNama());
        holder.alamat.setText(list.getAlamat());
        holder.detail.setText(list.getDetail());

        Glide.with(context)
                .load(list.getGambar())
                .into(holder.gambar);
        holder.gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ScrollingActivity.class);
                intent.putExtra("NAMA",list.getNama());
                intent.putExtra("ALAMAT",list.getAlamat());
                intent.putExtra("DETAIL",list.getDetail());
                intent.putExtra("GAMBAR",list.getGambar());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataWisata.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nama,alamat,detail;
        public ImageView gambar;
        public ViewHolder(View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.tvNama);
            alamat = (TextView) itemView.findViewById(R.id.tvAlamat);
            detail = (TextView) itemView.findViewById(R.id.tvDetail);
            gambar = (ImageView) itemView.findViewById(R.id.gambar);
        }
    }
}
