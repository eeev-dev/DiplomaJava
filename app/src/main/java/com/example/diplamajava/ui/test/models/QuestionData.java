package com.example.diplamajava.ui.test.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionData {
    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question(
                "Ты хотел(а) бы участвовать в экспедициях, исследовать горные породы и понимать, как устроена Земля изнутри?",
                Arrays.asList("Геология полезных ископаемых", "Подземная разработка", "Открытые горные работы")
        ));
        questions.add(new Question(
                "Тебя привлекает работа на открытом воздухе, где нужно управлять техникой, планировать добычу и наблюдать за производством?",
                Arrays.asList("Открытые горные работы", "Промышленная безопасность")
        ));
        questions.add(new Question(
                "Ты бы хотел(а) разрабатывать системы добычи ресурсов под землёй, проектировать шахты и тоннели?",
                Arrays.asList("Подземная разработка", "Мосты и тоннели", "Промышленная безопасность")
        ));
        questions.add(new Question(
                "Ты получаешь удовольствие от программирования, создания приложений или сайтов?",
                Arrays.asList("Прикладная информатика", "Информатика и вычислительная техника")
        ));
        questions.add(new Question(
                "Ты интересуешься машинным обучением, обработкой текста или созданием интеллектуальных ассистентов?",
                Arrays.asList("Компьютерная лингвистика", "Информатика и вычислительная техника")
        ));
        questions.add(new Question(
                "Тебе нравится автоматизировать процессы, работать с датчиками, роботами или управлять системами?",
                Arrays.asList("Автоматическое управление", "Информатика и вычислительная техника")
        ));
        questions.add(new Question(
                "Ты хотел(а) бы участвовать в создании архитектурных проектов, зданий и городских пространств?",
                Arrays.asList("Архитектура", "Художественное проектирование")
        ));
        questions.add(new Question(
                "Ты любишь рисовать, проектировать интерфейсы, интерьер или заниматься графическим дизайном?",
                Arrays.asList("Дизайн", "Художественное проектирование")
        ));
        questions.add(new Question(
                "Тебе нравится разбираться, как устроены мосты, дороги и тоннели, и ты хочешь решать транспортные задачи?",
                Arrays.asList("Автомобильные и железные дороги", "Мосты и тоннели", "Строительная механика")
        ));
        questions.add(new Question(
                "Ты хотел(а) бы заниматься строительными расчётами, проектировать надёжные конструкции и анализировать нагрузки?",
                Arrays.asList("Строительная механика", "Мосты и тоннели")
        ));
        questions.add(new Question(
                "Тебе интересно работать с картами, измерениями, навигацией и геоинформационными системами?",
                Arrays.asList("Геодезия и геоинформатика")
        ));
        questions.add(new Question(
                "Ты бы хотел(а) проектировать системы водоснабжения, канализации или участвовать в очистке воды?",
                Arrays.asList("Водоснабжение и водоотведение", "Геоэкология")
        ));
        questions.add(new Question(
                "Тебя волнует промышленная безопасность, защита природы и предотвращение аварий?",
                Arrays.asList("Промышленная безопасность и геоэкология", "Подземная разработка")
        ));
        questions.add(new Question(
                "Ты интересуешься точными науками и хочешь разрабатывать алгоритмы или работать с «железом» компьютеров?",
                Arrays.asList("Информатика и вычислительная техника", "Прикладная информатика")
        ));
        questions.add(new Question(
                "Ты хотел(а) бы анализировать данные, строить модели, создавать цифровые карты и работать с геоинформацией?",
                Arrays.asList("Геодезия и геоинформатика", "Прикладная информатика")
        ));

        return questions;
    }
}

