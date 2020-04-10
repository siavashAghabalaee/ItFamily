package com.zavosh.itfamili.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zavosh.itfamili.R;
import com.zavosh.itfamili.myviews.MyTextView;
import com.zavosh.itfamili.retrofit.mymodels.questionListlistrequest.QuestionListResult;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private Activity activity;
    private List<QuestionListResult> list;

    public QuestionAdapter(Activity activity, List<QuestionListResult> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_question_list,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {

        QuestionListResult item = list.get(position);

        holder.qustion.setText(item.getQuestion());
        holder.answer.setText(item.getAnswer());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {

        MyTextView qustion;
        MyTextView answer;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            qustion=itemView.findViewById(R.id.question);
            answer=itemView.findViewById(R.id.answer);
        }
    }
}
