package com.example.hellowworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;


public class MainActivity2 extends AppCompatActivity {

    private TextView textView_city_name;
    private TextView textView_update_time;
    private TextView textView_wendu;
    private TextView textView_pm25;
    private TextView textView_quality;
    private TextView textView_fengxiang;
    private TextView textView_fengli;
    private TextView textView_date;
    private TextView textView_type;
    private ImageView imageView_chooseCity;
    public static Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        Widgets Binding
        textView_city_name = (TextView) findViewById(R.id.textView2);
        textView_update_time = (TextView) findViewById(R.id.textView3);
        textView_wendu = (TextView) findViewById(R.id.textView8);
        textView_pm25 = (TextView) findViewById(R.id.textView6);
        textView_quality = (TextView) findViewById(R.id.textView11);
//        textView_fengxiang = (TextView) findViewById(R.id.);
        textView_fengli = (TextView) findViewById(R.id.textView10);
        textView_date = (TextView) findViewById(R.id.textView7);
        textView_type = (TextView) findViewById(R.id.textView9);
        imageView_chooseCity = (ImageView) findViewById(R.id.imageView2);

        mHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case QueryWeatherUtil.UPDATE_TODAY_WEATHER:
                        updateTodayWeather((TodayWeather) msg.obj);
                        break;
                    default:
                }
            }
        };

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
                    String city_name = data.getStringExtra("city_name");
                    String city_code = data.getStringExtra("city_code");
//                    Toast.makeText(this, returnedData, Toast.LENGTH_SHORT).show();
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(String.format(getString(R.string.city_name), city_name));
                    textView_city_name.setText(city_name);

                    QueryWeatherUtil.queryWeatherCode(city_code);
                }
        }
    }

    private void updateTodayWeather(TodayWeather obj) {
        textView_update_time.setText(obj.getUpdatetime());
        textView_quality.setText(obj.getQuality());
        textView_fengli.setText(obj.getFengli());
        textView_pm25.setText(obj.getPm25());
        textView_wendu.setText(obj.getLow() + "℃~" + obj.getHigh() + "℃");
        textView_date.setText(obj.getDate());
        textView_type.setText(obj.getType());
    }

}