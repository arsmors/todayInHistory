package com.arsens.todayinhistory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;

import java.util.Calendar;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidNetworking.initialize(getApplicationContext());

        getAndShowData();

    }

    public void onRefresh(View view) {
        getAndShowData();
    }



    public void getAndShowData() {

        Calendar calendar = Calendar.getInstance();


        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        AndroidNetworking.get("http://numbersapi.com/" + month + "/" + day + "/date")
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        changeMainText(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        int a = 0;
                    }
                });
    }

    public void changeMainText(String newText) {
        TextView textViewMain = findViewById(R.id.textViewMain);
        textViewMain.setText(newText);
    }
}
