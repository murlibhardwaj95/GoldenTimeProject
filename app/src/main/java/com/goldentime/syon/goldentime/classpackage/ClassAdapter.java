package com.goldentime.syon.goldentime.classpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldentime.syon.goldentime.R;
import com.goldentime.syon.goldentime.lesson.LessonActivity;
import com.goldentime.syon.goldentime.myutils.ConstantsValue;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.Viewholder> {

    Context ctx;
    List<ClassBean> classbean;

    public ClassAdapter(Context ctx, List<ClassBean> classbean) {
        this.ctx = ctx;
        this.classbean = classbean;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Viewholder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.series_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, final int i) {
        viewholder.class_txt.setText("No.of chapter" + classbean.get(i).getChaptersCnt());
        viewholder.classwise_book.setText(classbean.get(i).getClassName());
        Picasso.with(ctx).load(classbean.get(i).getClassImageXHDPI()).into(viewholder.classes);
        viewholder.classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(ConstantsValue.PHOTO,classbean.get(i).getClassImageXHDPI());
                bundle.putString(ConstantsValue.CLASS_NAME,classbean.get(i).getClassName());
                bundle.putString(ConstantsValue.SERIES_NAME,"Computer Series");
                bundle.putInt(ConstantsValue.CLASS_ID,classbean.get(i).getClassId());
                bundle.putInt(ConstantsValue.CHAPTER,classbean.get(i).getChaptersCnt());
                bundle.putInt(ConstantsValue.ACITIVITY,classbean.get(i).getActivitiesCnt());
                bundle.putInt(ConstantsValue.EXCERSIZE,classbean.get(i).getExcercisesCnt());
                bundle.putInt(ConstantsValue.PAGE_COUNT,classbean.get(i).getPagesCnt());

                ctx.startActivity(new Intent(ctx, LessonActivity.class).putExtras(bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return classbean.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView classwise_book, class_txt;
        ImageView classes;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            classes = itemView.findViewById(R.id.series_bok);
            classwise_book = itemView.findViewById(R.id.series_name);
            class_txt = itemView.findViewById(R.id.series_txt);
        }
    }
}
