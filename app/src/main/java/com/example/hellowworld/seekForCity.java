package com.example.hellowworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class seekForCity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_seek_for_city);
        String[] data={"海淀","朝阳","顺义","怀柔", "通州", "昌平", "延庆","丰台","石景山",};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(seekForCity.this,android.R.layout.simple_list_item_1,data);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, i, l) -> {
            String result = ((TextView) view).getText().toString();
//            Toast.makeText(seekForCity.this, "城市是：" + result, Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.putExtra("data_return", result);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}