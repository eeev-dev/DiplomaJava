package com.example.diplamajava.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.diplamajava.R;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.diplamajava.ui.chat.ChatFragment;
import com.example.diplamajava.ui.test.TestFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        // getSupportActionBar().hide();
        // Загружаем фрагмент по умолчанию
        loadFragments(new HomeFragment());

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Используем setOnItemSelectedListener для BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return onNavigationItemSelectedAction(item);  // Перенаправляем к методу обработки
            }
        });
    }

    // Метод для загрузки фрагментов
    public boolean loadFragments(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.parent_container, fragment)
                    .commit();
        }
        return true;
    }

    // Метод для обработки нажатий на элементы меню
    private boolean onNavigationItemSelectedAction(MenuItem item) {
        Fragment fragment = null;
        int itemId = item.getItemId();

        // Выбираем фрагмент в зависимости от нажатой кнопки
        if (itemId == R.id.homeId) {
            fragment = new HomeFragment();
        } else if (itemId == R.id.searchId) {
            fragment = new SearchFragment();
        } else if (itemId == R.id.chatId) {
            fragment = new ChatFragment();
        } else if (itemId == R.id.testId) {
            fragment = new TestFragment();
        } else if (itemId == R.id.profileId) {
            fragment = new ProfilFragment();
        }

        // Загружаем выбранный фрагмент
        return loadFragments(fragment);
    }
}
