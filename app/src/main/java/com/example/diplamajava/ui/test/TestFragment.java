package com.example.diplamajava.ui.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diplamajava.R;
import com.example.diplamajava.ui.test.adapters.LinkAdapter;
import com.example.diplamajava.ui.test.adapters.OnAnswerSelectedListener;
import com.example.diplamajava.ui.test.adapters.TestAdapter;
import com.example.diplamajava.ui.test.models.Question;
import com.example.diplamajava.ui.test.models.QuestionData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFragment extends Fragment implements OnAnswerSelectedListener {
    RecyclerView testRecyclerView;
    ListView linksView;
    TextView openLinkList;
    LinearLayout results;
    Button showResults;
    private final List<String> selectedDepartments = new ArrayList<>();

    @Override
    public void onYesSelected(List<String> departments) {
        selectedDepartments.addAll(departments);
    }

    public List<String> getSelectedDepartments() {
        return selectedDepartments;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        testRecyclerView = view.findViewById(R.id.recycler_view_questions);
        linksView = view.findViewById(R.id.list_view_texts);
        showResults = view.findViewById(R.id.btnAssign);

        List<String[]> tests = new ArrayList<>(Arrays.asList(
                new String[]{"Тест на личность", "https://proforientator.ru/profline/test/e60f99de247544e88ea0ad7824b7f1ef"},
                new String[]{"Тест на профессию", "https://proforientator.ru/profline/test/a70d02f8cff66823df169e38c9a0a326"},
                new String[]{"Тест по методике Е.А.Климова", "https://www.profguide.io/test/klimov.html"}
        ));

        LinkAdapter linkAdapter = new LinkAdapter(requireContext(), tests);

        linksView.setVisibility(View.GONE);

        openLinkList = view.findViewById(R.id.openLinkList);

        openLinkList.setOnClickListener(v -> {
            if (linksView.getVisibility() == View.VISIBLE) {
                linksView.setVisibility(View.GONE);
            } else {
                linksView.setVisibility(View.VISIBLE);
            }
        });

        linksView.setAdapter(linkAdapter);

        List<Question> questions = QuestionData.getQuestions();
        TestAdapter testAdapter = new TestAdapter(requireContext(), questions, this);

        testRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        testRecyclerView.setAdapter(testAdapter);

        results = view.findViewById(R.id.results);

        showResults.setOnClickListener(v -> {
            // Проверка, что на все вопросы есть ответы

            results.setVisibility(View.VISIBLE);
        });
    }

    public List<String> countResults() {
        List<String> results = getSelectedDepartments();

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