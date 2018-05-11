package dev.tatuan.qldh2.HangDongHo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import dev.tatuan.qldh2.DanhSachDongHo.DanhSachDongHo;
import dev.tatuan.qldh2.R;

public class DongHo extends AppCompatActivity {
    TextView tv_atlantic, tv_bruno, tv_citizen, tv_casio, tv_jacque, tv_stuhrling;
    String atlantic = "Atlantic";
    String bruno = "Bruno";
    String citizen = "Citizen";
    String Casio = "Casio";
    String jacque = "Jacque Lemans";
    String stuhrling = "Stuhrling Original Tourbillon";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_ho);
        init();
        click();
    }

    private void click() {
        //Atlantic
        tv_atlantic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DanhSachDongHo.class);
                intent.putExtra("tenhang", atlantic);
                startActivity(intent);
            }
        });
        //Bruno
        tv_bruno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DanhSachDongHo.class);
                intent.putExtra("tenhang", bruno);
                startActivity(intent);
            }
        });
        //Citizen
        tv_citizen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DanhSachDongHo.class);
                intent.putExtra("tenhang", citizen);
                startActivity(intent);
            }
        });
        //Casio
        tv_casio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DanhSachDongHo.class);
                intent.putExtra("tenhang", Casio);
                startActivity(intent);
            }
        });
        //Jacque Leman
        tv_jacque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DanhSachDongHo.class);
                intent.putExtra("tenhang", jacque);
                startActivity(intent);
            }
        });

        //Stuhrling
        tv_stuhrling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DanhSachDongHo.class);
                intent.putExtra("tenhang", stuhrling);
                startActivity(intent);
            }
        });
    }

    private void init() {
        tv_atlantic = findViewById(R.id.cv_atlantic);

        tv_bruno = findViewById(R.id.cv_bruno);

        tv_citizen = findViewById(R.id.cv_citizen);

        tv_casio = findViewById(R.id.cv_casio);

        tv_jacque = findViewById(R.id.cv_jacque);

        tv_stuhrling = findViewById(R.id.cv_stuhrling);


    }


}
