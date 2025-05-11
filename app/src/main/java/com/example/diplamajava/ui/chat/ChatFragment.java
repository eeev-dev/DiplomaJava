package com.example.diplamajava.ui.chat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.diplamajava.BuildConfig;
import com.example.diplamajava.R;
import com.example.diplamajava.data.firebase.Article;
import com.example.diplamajava.data.firebase.ArticleRepository;
import com.example.diplamajava.data.firebase.ArticleSearch;
import com.example.diplamajava.data.openai.ChatMessage;
import com.example.diplamajava.data.openai.ChatRequest;
import com.example.diplamajava.data.openai.ChatResponse;
import com.example.diplamajava.data.openai.OpenAiApi;
import com.example.diplamajava.data.openai.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;


public class ChatFragment extends Fragment {
    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    // Список сообщений для адаптера
    private List<Message> messageList;
    // Список сообщений для бота
    List<ChatMessage> messages = new ArrayList<>();
    private EditText messageEditText;
    private Button sendButton;
    List<Article> articles = new ArrayList<>();
    ArticleRepository articleRepository = new ArticleRepository();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chatRecyclerView = view.findViewById(R.id.chatRecyclerView);
        messageEditText = view.findViewById(R.id.messageEditText);
        sendButton = view.findViewById(R.id.sendButton);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(requireContext(), messageList);

        chatRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        chatRecyclerView.setAdapter(chatAdapter);

        // Получение из Firebase
        /*articleRepository.getArticles(new ArticleRepository.OnResult() {
            @Override
            public void onComplete(List<Article> articlesFromFirebase) {
                articles.addAll(articlesFromFirebase);
            }

            @Override
            public void onError(Exception exception) {
                Log.e("FIREBASE_ERROR", "Ошибка загрузки: " + exception.getMessage());
            }
        });*/

        articles.add(new Article("Справка об обучении", "Справку можно получить в деканате..."));
        articles.add(new Article("Перевод в другой вуз", "Инструкция по переводу..."));

        sendButton.setOnClickListener(v -> {
            String messageText = messageEditText.getText().toString().trim();
            if (!messageText.isEmpty()) {
                Message userMessage = new Message(messageText, true);
                addBubbleToAdapter(userMessage);

                // Ответ бота
                chatWithBot(messageText);
            }
        });
    }
    // Отрисовка облачка сообщения пользователя на экране
    public void addBubbleToAdapter(Message chatMessage) {
        messageList.add(chatMessage);
        chatAdapter.notifyItemInserted(messageList.size() - 1);
        chatRecyclerView.scrollToPosition(messageList.size() - 1);
        messageEditText.setText("");
    }
    public void chatWithBot(String userInput) {
        // Поиск среди имеющихся статей
        Article found = ArticleSearch.findBestMatch(userInput, articles);
        if (found != null) {
            addBubbleToAdapter(new Message(found.getBody(), false));
        } else {
            // Отправка запроса ии
            chatWithAiBot(userInput);
        }
    }
    public void chatWithAiBot(String messageText) {
        // Получаем экземпляр API
        OpenAiApi api = RetrofitInstance.getApiInstance();

        messages.add(new ChatMessage("user", messageText));

        ChatRequest request = new ChatRequest(messages);

        Call<ChatResponse> call = api.chatCompletion("Bearer " + BuildConfig.OPENAI_API_KEY, request);

        call.enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String reply = response.body().choices.get(0).message.content;
                    Message botMessage = new Message(reply, false);
                    addBubbleToAdapter(botMessage);
                } else {
                    Log.e("GPT", "Ошибка ответа: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                Log.e("GPT", "Ошибка запроса: " + t.getMessage());
            }
        });
    }
}