package dev.tatuan.qldh2.DonHang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dev.tatuan.qldh2.R;

public class QuanLyDonHang extends AppCompatActivity {
    RecyclerView rcOfDonHang;
    ArrayList<UserData1> data1;
    UserData1 DHData;
    AdapterUser1 adapter1;
    DatabaseReference dataDonHang;
    RecyclerView.LayoutManager layoutManager;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_don_hang);

        data1 = new ArrayList<>();
        adapter1 = new AdapterUser1(getApplicationContext(), data1);

        dataDonHang = FirebaseDatabase.getInstance().getReference("account").child("user");

        layoutManager = new LinearLayoutManager(this);
        rcOfDonHang = findViewById(R.id.rcOfDonHang);
        getData();

        rcOfDonHang.setLayoutManager(layoutManager);
        rcOfDonHang.setAdapter(adapter1);
    }

    public void getData() {

        dataDonHang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    data1.clear();
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot dataa : dataSnapshot.getChildren()) {
                        DHData = dataa.getValue(UserData1.class);//
                        data1.add(DHData);
                    }
                }
                    adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
