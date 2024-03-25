package com.example.myfirstapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView goodmorning;
    Button helloButton;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goodmorning = findViewById(R.id.morning);
        goodmorning.setText(MyData.getInstance().myName);
        System.out.println("hello");
        helloButton = findViewById(R.id.button);
        helloButton.setOnClickListener(this);
        inputText = findViewById(R.id.editTextText);
      }

    @Override
    public void onClick(View v) {
       String userInputText = inputText.getText().toString();
       goodmorning.setText("Hello " + userInputText);
       MyData.getInstance().myName = "Hello " + userInputText;


       //Spawn new background thread for doing network traffic
        Thread background = new Thread (new Runnable(){
           public void run(){
               String data = readURL("https://raw.githubusercontent.com/sunetb/U/master/greeting.txt");
               System.out.println(data);

               //post the code for updating the View in the main thread
               new Handler(Looper.getMainLooper()).post(new Runnable() {
                   @Override
                   public void run() {
                       goodmorning.setText(data);
                   }
               });

           }
       });
       background.start();

    }
    String readURL(String inputUrl){
        String value = "";
        URL url = null;
        try {
            url = new URL(inputUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            System.out.println(con);
            con.setRequestMethod("GET");
            InputStream i = con.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(i));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                value += inputLine + "\n";
            }
            in.close();
        } catch (ProtocolException ex) {
            throw new RuntimeException(ex);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return value;
    }

}