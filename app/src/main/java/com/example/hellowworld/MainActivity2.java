package com.example.hellowworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.*;


public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    // TODO: progress bar
    private TextView textView_city_name;
    private TextView textView_update_time;
    private TextView textView_today_wendu;
    private TextView textView_shidu;
    private TextView textView_pm25;
    private TextView textView_quality;
    private TextView textView_fengxiang;
    private TextView textView_fengli;
    private TextView textView_date;
    private TextView textView_type;
    private TextView textView_current_wendu;
    private ImageView imageView_chooseCity;
    private ImageView imageView_weather;
    private ImageView imageView_refresh;
    private String remember_city_code;
    private String remember_city_name;
    public static Handler mHandler;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        remember_city_code = pref.getString("city_code", "101010100");
        remember_city_name = pref.getString("city_name", "北京");
        Log.d(TAG, remember_city_code);
        QueryWeatherUtil.queryWeatherCode(remember_city_code);

//        Widgets Binding
        textView_city_name = (TextView) findViewById(R.id.textView2);
        textView_update_time = (TextView) findViewById(R.id.textView3);
        textView_today_wendu = (TextView) findViewById(R.id.textView8);
        textView_pm25 = (TextView) findViewById(R.id.textView6);
        textView_quality = (TextView) findViewById(R.id.textView11);
        textView_shidu = (TextView) findViewById(R.id.textView4);
//        textView_fengxiang = (TextView) findViewById(R.id.);
        textView_fengli = (TextView) findViewById(R.id.textView10);
        textView_date = (TextView) findViewById(R.id.textView7);
        textView_type = (TextView) findViewById(R.id.textView9);
        textView_current_wendu = (TextView) findViewById(R.id.textView12);
        imageView_chooseCity = (ImageView) findViewById(R.id.imageView2);
        imageView_weather = (ImageView) findViewById(R.id.imageView7);
        imageView_refresh = (ImageView) findViewById(R.id.imageView5);

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

        imageView_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryWeatherUtil.queryWeatherCode(remember_city_code);
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
                    editor = pref.edit();
                    editor.putString("city_code", city_code);
                    editor.putString("city_name", city_name);
                    remember_city_name = city_name;
                    Log.d(TAG, city_code);
                    editor.apply();
                    QueryWeatherUtil.queryWeatherCode(city_code);
                }
        }
    }

    private void updateTodayWeather(TodayWeather obj) {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.format(getString(R.string.city_name), remember_city_name));
        textView_city_name.setText(remember_city_name);
        textView_update_time.setText(obj.getUpdatetime() + "更新");
        textView_quality.setText(obj.getQuality());
        textView_fengli.setText(obj.getFengxiang() + obj.getFengli());
        textView_pm25.setText(obj.getPm25());
        textView_today_wendu.setText(obj.getLow() + "   " + obj.getHigh());
        textView_date.setText(obj.getDate());
        textView_type.setText(obj.getType());
        textView_shidu.setText("湿度：" + obj.getShidu());
        textView_current_wendu.setText(obj.getWendu() + "℃");

        switch (obj.getType()) {
            case "暴雪":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_baoxue);
                break;
            case "暴雨":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_baoyu);
                break;
            case "大暴雨":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_dabaoyu);
                break;
            case "大雪":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_daxue);
                break;
            case "大雨":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_dayu);
                break;
            case "多云":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_duoyun);
                break;
            case "雷阵雨":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_leizhenyu);
                break;
            case "雷阵雨冰雹":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_leizhenyubingbao);
                break;
            case "晴":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_qing);
                break;
            case "沙尘暴":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_shachenbao);
                break;
            case "特大暴雨":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_tedabaoyu);
                break;
            case "雾":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_wu);
                break;
            case "小雪":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_xiaoxue);
                break;
            case "小雨":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_xiaoyu);
                break;
            case "阴":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_yin);
                break;
            case "雨夹雪":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_yujiaxue);
                break;
            case "阵雪":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_zhenxue);
                break;
            case "阵雨":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_zhenyu);
                break;
            case "中雪":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_zhongxue);
                break;
            case "中雨":
                imageView_weather.setImageResource(R.drawable.biz_plugin_weather_zhongyu);
                break;
        }

    }

}