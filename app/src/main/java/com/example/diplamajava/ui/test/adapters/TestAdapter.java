package com.example.diplamajava.ui.test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diplamajava.R;
import com.example.diplamajava.ui.test.models.Question;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.QuestionViewHolder> {

    private final List<Question> questionList;
    private final Context context;

    private final OnAnswerSelectedListener answerListener;

    public TestAdapter(Context context, List<Question> questionList, OnAnswerSelectedListener listener) {
        this.context = context;
        this.questionList = questionList;
        this.answerListener = listener;
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionText;
        RadioGroup radioGroup;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.question_text);
            radioGroup = itemView.findViewById(R.id.radio_group_answers);
        }
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questionList.get(position);

        // Устанавливаем текст вопроса
        holder.questionText.setText(question.getQuestion());

        // Сброс слушателя и выбранного значения, чтобы избежать багов с recycled view
        holder.radioGroup.setOnCheckedChangeListener(null);
        holder.radioGroup.clearCheck();

        // Устанавливаем новый слушатель
        holder.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_button_yes) {
                answerListener.onYesSelected(question.getDepartments());
            } else if (checkedId == R.id.radio_button_no) {
                // пользователь выбрал "Нет"
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }
}
