package com.esirteam.issueweekview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewDisplayable;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private WeekView<Lesson> mWeekView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWeekView = findViewById(R.id.weekView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWeekView.submit(Collections.<WeekViewDisplayable<Lesson>>emptyList());
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupWeekView() {
        mWeekView.setNumberOfVisibleDays(5);

        mWeekView.goToCurrentTime();

    }

}
