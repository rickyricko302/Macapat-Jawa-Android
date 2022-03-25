package id.hikki.macapatjawa.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import id.hikki.macapatjawa.Model.DataItem;
import id.hikki.macapatjawa.R;

import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder>{
    DataItem[] data;
    OnClickItem click;
    public AdapterRecycler(DataItem[] data){
        this.data = data;
    }
    @NonNull
    @Override
    public AdapterRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tembang,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycler.ViewHolder holder, int position) {
        holder.text.setText(String.valueOf(position+1)+". "+data[position].getJeneng());
        if(position == data.length-1){
            holder.card.setAlpha(0);
        }
    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        MaterialCardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.itemText);
            card = itemView.findViewById(R.id.cardItem);
            card.setOnClickListener(v->{
                click.click(v,getAdapterPosition());
             //   Toast.makeText(itemView.getContext(), String.valueOf(getAdapterPosition()),Toast.LENGTH_SHORT).show();
            });
        }
    }
    public void setClick(OnClickItem click){
        this.click = click;
    }
    public interface OnClickItem{
        void click(View v, int posisi);
    }
}
