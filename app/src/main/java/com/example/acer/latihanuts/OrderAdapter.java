package com.example.acer.latihanuts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private ArrayList<Order> data;
    private static ClickListener listener;

    public OrderAdapter(ArrayList<Order> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //Menentukan layout untuk 1 roe dari recycle view
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v = inflater.inflate(R.layout.row_order, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //Mengisi widged dengan data dari arrayList
        //data yang dihubungkan dengan holder, disebut data binding
        viewHolder.toppings.setText(data.get(i).getToppings().toString());
        viewHolder.type.setText(data.get(i).getQty() + " " +  data.get(i).getType());
        viewHolder.subtotal.setText(String.valueOf(data.get(i).getSubtotal()));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView toppings, type, subtotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            toppings = itemView.findViewById(R.id.textView_toppings);
            subtotal = itemView.findViewById(R.id.textView_subtotal);
            type = itemView.findViewById(R.id.textView_qty_type);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    listener.recycleViewClick(v, ViewHolder.this.getLayoutPosition());
                }
            });

        }
    }
}
