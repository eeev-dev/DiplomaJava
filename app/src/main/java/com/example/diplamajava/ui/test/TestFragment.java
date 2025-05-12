package com.example.diplamajava.ui.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diplamajava.R;
import com.example.diplamajava.ui.test.models.Question;
import com.example.diplamajava.ui.test.models.QuestionData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFragment extends Fragment {
    ListView linksView;
    TextView openLinkList, questionText, progress;
    TextView first_place, second_place, third_place;
    Button btnAssign, btnBack;
    RadioGroup radioGroup;
    int position = 0;
    LinearLayout questionLayout, resultsLayout;
    List<String> departments = new ArrayList<>();
    List<Question> questions = QuestionData.getQuestions();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linksView = view.findViewById(R.id.list_view_texts);
        btnAssign = view.findViewById(R.id.btnNext);
        questionText = view.findViewById(R.id.tvQuestion);
        radioGroup = view.findViewById(R.id.radio_group_answers);
        first_place = view.findViewById(R.id.first_place);
        second_place = view.findViewById(R.id.second_place);
        third_place = view.findViewById(R.id.third_place);
        questionLayout = view.findViewById(R.id.question);
        resultsLayout = view.findViewById(R.id.links);
        btnBack = view.findViewById(R.id.btnExit);
        progress = view.findViewById(R.id.tvProgress);

        radioGroup.setOnCheckedChangeListener(null);
        radioGroup.clearCheck();

        // Ссылки

        List<String[]> tests = new ArrayList<>(Arrays.asList(
                new String[]{"Тест на личность", "https://proforientator.ru/profline/test/e60f99de247544e88ea0ad7824b7f1ef"},
                new String[]{"Тест на профессию", "https://proforientator.ru/profline/test/a70d02f8cff66823df169e38c9a0a326"},
                new String[]{"Тест по методике Е.А.Климова", "https://www.profguide.io/test/klimov.html"}
        ));

        LinkAdapter linkAdapter = new LinkAdapter(requireContext(), tests);
        linksView.setVisibility(View.GONE);
        linksView.setAdapter(linkAdapter);

        openLinkList = view.findViewById(R.id.openLinkList);

        openLinkList.setOnClickListener(v -> {
            if (linksView.getVisibility() == View.VISIBLE) {
                linksView.setVisibility(View.GONE);
            } else {
                linksView.setVisibility(View.VISIBLE);
            }
        });

        // Тест

        progress.setText((position + 1) + "/" + questions.size());
        questionText.setText(questions.get(0).getQuestion());

        btnAssign.setOnClickListener(v -> {
            position++;
            if (position >= questions.size()) {
                List<String> results = countResults();

                first_place.setText(results.get(0));
                second_place.setText(results.get(1));
                third_place.setText(results.get(2));

                questionLayout.setVisibility(View.GONE);
                resultsLayout.setVisibility(View.VISIBLE);
            } else {
                if (radioGroup.getCheckedRadioButtonId() != -1) {
                    Question question = questions.get(position);
                    questionText.setText(question.getQuestion());
                    radioGroup.clearCheck();
                    progress.setText((position + 1) + "/" + questions.size());
                }
            }
        });

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_button_yes) {
                departments.addAll(questions.get(position).getDepartments());
            } else if (checkedId == R.id.radio_button_no) {
                // пользователь выбрал "Нет"
            }
        });

        btnBack.setOnClickListener(v -> {
            position = 0;
            questionText.setText(questions.get(position).getQuestion());
            radioGroup.clearCheck();
            departments.clear();
            questionLayout.setVisibility(View.VISIBLE);
            resultsLayout.setVisibility(View.GONE);
        });
    }

    public List<String> countResults() {
        List<String> results = departments;

        // Подсчёт вхождений
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String item : results) {
            frequencyMap.put(item, frequencyMap.getOrDefault(item, 0) + 1);
        }

        // Сортировка по убыванию количества
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(frequencyMap.entrySet());
        sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Добавление трёх самых частых элементов
        List<String> topResults = new ArrayList<>();
        for (int i = 0; i < Math.min(3, sortedEntries.size()); i++) {
            topResults.add(sortedEntries.get(i).getKey());
        }

        return topResults;
    }
}