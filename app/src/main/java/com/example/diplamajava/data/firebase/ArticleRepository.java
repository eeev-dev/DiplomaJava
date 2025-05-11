package com.example.diplamajava.data.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private final DatabaseReference databaseReference;

    public ArticleRepository() {
        // Инициализируем ссылку на Firebase Realtime Database
        this.databaseReference = FirebaseDatabase.getInstance().getReference("articles");
    }

    // Получаем список всех статей
    public void getArticles(final OnResult callback) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Article> articles = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String title = snapshot.child("title").getValue(String.class);
                    String body = snapshot.child("body").getValue(String.class);
                    articles.add(new Article(title, body));
                }
                callback.onComplete(articles);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError.toException());
            }
        });
    }

    // Интерфейс для колбэков
    public interface OnResult {
        void onComplete(List<Article> articles);
        void onError(Exception exception);
    }
}
