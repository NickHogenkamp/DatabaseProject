package com.databases.SQLlitePractise;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Database database;
    private Button showWorkoutsButton;
    private Button showExercisesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this);

        showWorkoutsButton = findViewById(R.id.show_workouts_button);
        showWorkoutsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWorkouts();
            }
        });

        showExercisesButton = findViewById(R.id.show_exercise_button);
        showExercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExercises();
            }
        });
    }

    public void showWorkouts() {
        Cursor res = database.getWorkouts();
            if(res.getCount() == 0) {
                showMessage("Error", "Nothing found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("WORKOUT_ID : "+ res.getString(0)+"\n");
                    buffer.append("WORKOUT_NAME : "+ res.getString(1)+"\n\n");
                }
        showMessage("Workouts",buffer.toString());
    }

    public void showExercises() {
        Cursor res = database.getExercises();
        if(res.getCount() == 0) {
            showMessage("Error", "Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("EXERCISE_ID : "+ res.getString(0)+"\n");
            buffer.append("EXERCISE_NAME : "+ res.getString(1)+"\n");
            buffer.append("WORKOUT_ID : "+ res.getString(2)+"\n\n");
        }
        showMessage("Exercises",buffer.toString());
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
