package com.goldentime.syon.goldentime.welcome;

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
import com.goldentime.syon.goldentime.myutils.ConstantsValue;
import com.goldentime.syon.goldentime.series.SeriesActivity;

import java.util.List;

public class BooksListAdapter extends RecyclerView.Adapter<BooksListAdapter.ViewHolder> {
    Context context;
    List<BookDetailsBean> data;

    public BooksListAdapter(Context context, List<BookDetailsBean> data) {
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.books_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.bookname.setText(data.get(position).getSubName());
        viewHolder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,SeriesActivity.class).putExtra(ConstantsValue.SUBJECT_ID,data.get(position).getSubId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookname;
        ImageView book;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            book = itemView.findViewById(R.id.book_stand);
            bookname=itemView.findViewById(R.id.book_name);
        }
    }
}
