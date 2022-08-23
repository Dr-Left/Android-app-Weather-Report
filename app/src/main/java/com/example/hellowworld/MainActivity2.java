package com.example.hellowworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView imageView_chooseCity = (ImageView) findViewById(R.id.imageView2);
        imageView_chooseCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity2.this, "123", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity2.this, seekForCity.class);
                startActivityForResult(intent, 1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
//                    Toast.makeText(this, returnedData, Toast.LENGTH_SHORT).show();
                    TextView textView = (TextView) findViewById(R.id.textView);
                    TextView textView2 = (TextView) findViewById(R.id.textView2);
                    textView.setText(String.format(getString(R.string.city_name), returnedData));
                    textView2.setText(returnedData);
                }
        }
    }
}