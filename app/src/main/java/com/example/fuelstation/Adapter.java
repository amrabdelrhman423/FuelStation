package com.example.fuelstation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuelstation.moduls.benz;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    private OnClickItemListener itemListener;
    public interface OnClickItemListener{
        void OnItemClick(int position);
    }
    public void setItemListener(OnClickItemListener itemListener){
        this.itemListener=itemListener;
    }

    Context context;
    List<benz> benzs;

    public Adapter(Context context, List<benz> benzs) {
        this.context = context;
        this.benzs = benzs;
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item,parent,false);
        return new ViewHolder(view,itemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        benz d =benzs.get(position);
        holder.type.setText(d.getBenztype());
        holder.value.setText(d.getBenzvalue());

    }

    @Override
    public int getItemCount() {
        return benzs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView type,value;
        public ViewHolder(@NonNull View itemView,final OnClickItemListener listener) {
            super(itemView);
            type=(TextView) itemView.findViewById(R.id.type);
            value=(TextView)itemView.findViewById(R.id.value);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener !=null){
                        int postion =getAdapterPosition();
                        if (postion!=RecyclerView.NO_POSITION){
                            listener.OnItemClick(postion);
                        }
                    }
                }
            });
        }
    }
}
