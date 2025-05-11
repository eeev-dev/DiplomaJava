package com.example.diplamajava.data.firebase;

import org.simmetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;

import java.util.List;

public class ArticleSearch {
    // Метод для поиска наиболее подходящей статьи
    public static Article findBestMatch(String query, List<Article> articles) {
        StringMetric metric = StringMetrics.levenshtein();

        String cleanedQuery = preprocess(query); // Очистка строки от ненужных символов
        float bestScore = 0f;
        Article bestMatch = null;

        // Проходим по всем статьям и ищем наиболее подходящую по названию
        for (Article article : articles) {
            String title = preprocess(article.getTitle()); // Очистка названия
            float score = metric.compare(cleanedQuery, title);

            if (score > bestScore) {
                bestScore = score;
                bestMatch = article;
            }
        }

        // Порог схожести (можно настроить в зависимости от требований)
        return bestScore > 0.4f ? bestMatch : null;
    }

    private static String preprocess(String text) {
        return text.toLowerCase()
                .replaceAll("[^a-zа-я0-9\\s]", "") // Убираем знаки
                .replaceAll("\\s+", " ") // Нормализуем пробелы
                .trim(); // Убираем пробелы по краям
    }
}
