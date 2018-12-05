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
    private promocaoAdapter.OnPromoClickListener evListener;
    private promocaoAdapter.OnPromoLongClickListener evLongListener;

    public promocaoAdapter(Cursor c){
        cursor = c;
    }

    public void setCursor(Cursor c){
        cursor = c;
        notifyDataSetChanged();
    }

    public interface OnPromoClickListener {
        void onPromoClick(View PromoView, int position);
    }
    public void setOnPromoClickListener(promocaoAdapter.OnPromoClickListener listener){
        this.evListener = listener;
    }
    public interface OnPromoLongClickListener {
        void onPromoLongClick(View PromoView, int position);
    }
    public void setOnPromoLongClickListener(promocaoAdapter.OnPromoLongClickListener listener){
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
                            evListener.onPromoClick(itemView, position);
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
                            evLongListener.onPromoLongClick(itemView, position);
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
                    evListener.onPromoClick(v, position);
                }
            }
        }

        public boolean onLongClick(View v) {
            if(evLongListener !=null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    evLongListener.onPromoLongClick(v, position);
                    return true;
                }
            }
            return false;
        }
    }
}