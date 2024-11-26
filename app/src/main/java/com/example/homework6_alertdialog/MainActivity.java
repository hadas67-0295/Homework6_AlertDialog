package com.example.homework6_alertdialog;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    final private String USERINPUT_KEY = "userInput";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPreferences = getSharedPreferences("userInputPrefs", MODE_PRIVATE);
        Button btnSaveInputUser = findViewById(R.id.btnSaveText);
        EditText etInputUser = findViewById(R.id.etInputText);


        btnSaveInputUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String userInput = etInputUser.getText().toString();
                editor.putString(USERINPUT_KEY, userInput);
                editor.apply();
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Wow")
                        .setMessage("Awesome")
                        .setNegativeButton("Cancel",null)
                        .setPositiveButton("yas",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                Toast.makeText(MainActivity.this, "You wrote: " + userInput, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }
}
