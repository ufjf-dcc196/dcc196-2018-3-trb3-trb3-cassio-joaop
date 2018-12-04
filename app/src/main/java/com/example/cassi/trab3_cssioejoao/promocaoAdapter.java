package com.example.cassi.trab3_cssioejoao;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class promocaoAdapter  extends RecyclerView.Adapter<promocaoAdapter.ViewHolder> {

    private Cursor cursor;
    private promocaoAdapter.OnEventClickListener evListener;
    private promocaoAdapter.OnEventLongClickListener evLongListener;

    public promocaoAdapter(Cursor c){
        cursor = c;
    }

    public void setCursor(Cursor c){
        cursor = c;
        notifyDataSetChanged();
    }

    public interface OnEventClickListener {
        void onEventClick(View EventView, int position);
    }
    public void setOnEventClickListener(promocaoAdapter.OnEventClickListener listener){
        this.evListener = listener;
    }
    public interface OnEventLongClickListener {
        void onEventLongClick(View EventView, int position);
    }
    public void setOnEventLongClickListener(promocaoAdapter.OnEventLongClickListener listener){
        this.evLongListener = listener;
    }

    @NonNull
    @Override
    public promocaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View PromocaoView =inflater.inflate(R.layout.rcl_layout2, viewGroup, false);
        promocaoAdapter.ViewHolder viewHolder = new promocaoAdapter.ViewHolder(PromocaoView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull promocaoAdapter.ViewHolder viewHolder, int position) {
        int idxNome = cursor.getColumnIndexOrThrow(PromocaoContract.Promocao.COLUMN_NAME_NOME);
        cursor.moveToPosition(position);
        viewHolder.txtNome.setText(cursor.getString(idxNome));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNome;
        public ViewHolder(final View itemView) {
            super(itemView);

            txtNome = (TextView)itemView.findViewById(R.id.txt_layoutColumn2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(evListener !=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            evListener.onEventClick(itemView, position);
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    if(evLongListener !=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            evLongListener.onEventLongClick(itemView, position);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }

        public void onClick(View v) {
            if(evListener !=null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    evListener.onEventClick(v, position);
                }
            }
        }

        public boolean onLongClick(View v) {
            if(evLongListener !=null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    evLongListener.onEventLongClick(v, position);
                    return true;
                }
            }
            return false;
        }
    }
}