package com.goldentime.syon.goldentime.lesson;

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
import com.goldentime.syon.goldentime.classpackage.ClassAdapter;
import com.goldentime.syon.goldentime.classpackage.ClassBean;
import com.goldentime.syon.goldentime.myutils.ConstantsValue;
import com.goldentime.syon.goldentime.question.QuestionActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    Context ctx;
    List<LessonBean> lessonBeans;
    String classname;

    public LessonAdapter(Context ctx, List<LessonBean> lessonBeans,String c) {
        this.ctx = ctx;
        this.lessonBeans = lessonBeans;
        this.classname =c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LessonAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lesson_list, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.chapter_number.setText(lessonBeans.get(i).getChapterNo() + ".");
        viewHolder.chapter_name.setText(lessonBeans.get(i).getChapterName());
        Picasso.with(ctx).load(lessonBeans.get(i).getColourName()).into(viewHolder.chapter_bg);
        viewHolder.chapter_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctx.startActivity(new Intent(ctx, QuestionActivity.class).putExtra(ConstantsValue.CHAPTER_ID, lessonBeans.get(i).getChapterId()).putExtra(ConstantsValue.CHAPTER_NAME,lessonBeans.get(i).getChapterName()).putExtra(ConstantsValue.CLASS_NAME,classname));
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessonBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView chapter_number, chapter_name;
        ImageView chapter_bg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chapter_number = itemView.findViewById(R.id.tv_chapter_no);
            chapter_name = itemView.findViewById(R.id.tv_chapter_name);
            chapter_bg = itemView.findViewById(R.id.chapter_bg);
        }
    }
}
