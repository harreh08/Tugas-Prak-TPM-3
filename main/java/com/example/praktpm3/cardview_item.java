package com.example.praktpm3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class cardview_item extends RecyclerView.Adapter<cardview_item.CardViewViewHolder> {
    private ArrayList<Moviemodel> listMovie;
    Context context;

    public cardview_item(ArrayList<Moviemodel> list, Context context) {
        this.listMovie = list;
        this.context = context;
    }


    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viwType) {
           View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_cardview_item,viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        Moviemodel movieModel = listMovie.get(position);

        Glide.with(holder.itemView.getContext())
                .load(movieModel.getPhoto())
                .apply(new RequestOptions().override(350,550))
                .into(holder.imgPhoto);

        holder.tvName.setText(movieModel.getName());
        holder.tvDetail.setText(movieModel.getDetail());
        holder.imgPhoto.setImageResource(movieModel.getPhoto());

        holder.btnShare.setOnClickListener(view -> {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, listMovie.get(holder.getAdapterPosition()).getName());
            shareIntent.setType("text/plain");

            context.startActivity(Intent.createChooser(shareIntent,"Send to"));
        });

        holder.btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previewIntent = new Intent(context, PreviewActivity.class);
                previewIntent.putExtra(PreviewActivity.EXTRA_NAME, listMovie.get(holder.getAdapterPosition()).getName());
                previewIntent.putExtra(PreviewActivity.EXTRA_DETAIL,listMovie.get(holder.getAdapterPosition()).getDetail());
                previewIntent.putExtra(PreviewActivity.EXTRA_IMAGES, listMovie.get(holder.getAdapterPosition()).getPhoto());
                context.startActivity(previewIntent);
            }
        });
    }

    @Override
    public int getItemCount() { return listMovie.size();}

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDetail;
        Button btnShare, btnPreview;

        CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDetail = itemView.findViewById(R.id.tv_item_detail);
            btnShare = itemView.findViewById(R.id.btn_set_share);
            btnPreview = itemView.findViewById(R.id.btn_set_preview);
        }
    }
}