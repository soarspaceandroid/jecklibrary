package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @StringJeck(value = "gaofei")
    String name;

    @StringJeck(value = "27")
    int age;

    @StringJeck(value = "12.23251")
    float money;


    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.test);
        StringCheckHelper helper = new StringCheckHelper(this);
        Log.e("soar" , "----"+name+"------"+age+"------"+money);
    }
    @MethodJeck(id = R.id.test)
    private void setSoar(int id){
        switch (id){
            case R.id.test:
                textView.setText("test ------ you click this");
        }
    }

}
