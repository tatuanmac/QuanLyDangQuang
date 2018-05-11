package dev.tatuan.qldh2.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import dev.tatuan.qldh2.DonHang.QuanLyDonHang;
import dev.tatuan.qldh2.HangDongHo.DongHo;
import dev.tatuan.qldh2.R;

public class MainAdmin extends AppCompatActivity {
    protected TextView tv_admin_name;
    protected CardView cardViewDonHang, cardViewQTV, cardViewQLDH;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_admin);
        init();
        Click();
    }

    private void init() {
        tv_admin_name = findViewById(R.id.tv_admin_name);
        Bundle bundle = getIntent().getExtras();
        String adname = bundle.getString("Name");
        tv_admin_name.setText(adname);

        //
        cardViewDonHang = findViewById(R.id.cardViewDonHang);
        cardViewQTV = findViewById(R.id.cardViewQTV);
        cardViewQLDH = findViewById(R.id.cardViewQLDH);

    }

    private void Click() {
        cardViewDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this, QuanLyDonHang.class));
            }
        });
        cardViewQTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this, QuanTriVien.class));

            }
        });
        cardViewQLDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this, DongHo.class));

            }
        });


    }

    @Override
    public void onBackPressed() {

    }
}
