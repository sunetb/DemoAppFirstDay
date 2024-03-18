package com.example.myfirstapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView goodmorning;
    Button helloButton;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goodmorning = findViewById(R.id.morning);
        goodmorning.setText("Welcome");
        System.out.println("hello");
        helloButton = findViewById(R.id.button);
        helloButton.setOnClickListener(this);
        inputText = findViewById(R.id.editTextText);
      }

    @Override
    public void onClick(View v) {
       String userInputText = inputText.getText().toString();
       goodmorning.setText("Hello " + userInputText);

   }
}