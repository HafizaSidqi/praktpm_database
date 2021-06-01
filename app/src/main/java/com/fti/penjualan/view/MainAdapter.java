package com.fti.penjualan.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fti.penjualan.R;
import com.fti.penjualan.entity.DataPenjualan;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataPenjualan> list;
    MainContact.view mView;

    public MainAdapter(Context context, List<DataPenjualan> list, MainContact.view mView) {
        this.context = context;
        this.list = list;
        this.mView = mView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inisiasi layout yang diakses
        View view = LayoutInflater.from(context).inflate(R.layout.activity_itemjual, parent, false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        //dari viewHolder untuk masuk ke recyclerview nya
        final DataPenjualan item = list.get(position);
        holder.tvId.setText(item.getId());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{ //memanggil id layout
        TextView tvId, tvTgl, tvInput, tvOutput, tvInputBersih, cardview;
        public viewHolder(@NonNull View itemView){
            super(itemView);
            tvId = itemView.findViewById(R.id.et_iditem);
            tvTgl = itemView.findViewById(R.id.et_tglitem);
            tvInput = itemView.findViewById(R.id.et_inputitem);
            tvOutput = itemView.findViewById(R.id.et_outputitem);
            tvInputBersih = itemView.findViewById(R.id.et_inputbersih);
            cardview = itemView.findViewById(R.id.cv_item);
        }
    }
}
