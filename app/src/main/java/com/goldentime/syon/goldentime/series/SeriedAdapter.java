package com.goldentime.syon.goldentime.series;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldentime.syon.goldentime.R;
import com.goldentime.syon.goldentime.classpackage.ClassActivity;
import com.goldentime.syon.goldentime.myutils.ConstantsValue;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriedAdapter extends RecyclerView.Adapter<SeriedAdapter.ViewHolder> {
    Context context;
    List<SeriesBean> data;

    public SeriedAdapter(Context context, List<SeriesBean> data) {
            this.context = context;
            this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.series_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.bookname.setText(data.get(position).getSeriesName());
        viewHolder.series_txt.setText(data.get(position).getDescription());
        Picasso.with(context).load(data.get(position).getSeriesImageXHDPI()).into(viewHolder.series_book);
        viewHolder.series_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ClassActivity.class).putExtra(ConstantsValue.SERIES_ID,data.get(position).getSeriesId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView series_book;
        TextView bookname,series_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookname = itemView.findViewById(R.id.series_name);
            series_book = itemView.findViewById(R.id.series_bok);
series_txt = itemView.findViewById(R.id.series_txt);
        }
    }
}
