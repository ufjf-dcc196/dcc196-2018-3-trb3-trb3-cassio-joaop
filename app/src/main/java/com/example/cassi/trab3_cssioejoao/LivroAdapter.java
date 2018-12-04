package com.example.cassi.trab3_cssioejoao;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LivroAdapter  extends RecyclerView.Adapter<LivroAdapter.ViewHolder> {

    private Cursor cursor;
    private OnEventClickListener evListener;
    private OnEventLongClickListener evLongListener;

    public LivroAdapter(Cursor c){
        cursor = c;
    }

    public void setCursor(Cursor c){
        cursor = c;
        notifyDataSetChanged();
    }

    public interface OnEventClickListener {
        void onEventClick(View EventView, int position);
    }
    public void setOnEventClickListener(OnEventClickListener listener){
        this.evListener = listener;
    }
    public interface OnEventLongClickListener {
        void onEventLongClick(View EventView, int position);
    }
    public void setOnEventLongClickListener(OnEventLongClickListener listener){
        this.evLongListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View LivroView =inflater.inflate(R.layout.rcl_livro_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(LivroView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        int idxTitulo = cursor.getColumnIndexOrThrow(LivroContract.Livro.COLUMN_NAME_TITULO);
        cursor.moveToPosition(position);
        viewHolder.txtTitulo.setText(cursor.getString(idxTitulo));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitulo;
        public ViewHolder(final View itemView) {
            super(itemView);

            txtTitulo = (TextView)itemView.findViewById(R.id.txt_layoutTitulo);
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



