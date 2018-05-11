package dev.tatuan.qldh2.DanhSachDonHang;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import dev.tatuan.qldh2.R;

public class DonHang2 extends AppCompatActivity {
    TextView tv_phone;
    private RecyclerView recylerDonHang;
    private DonHangAdapter adapter;
    private DatabaseReference mData;
//    private String phone;
    private ArrayList<String> arrListDH;
    private HashMap<String, ArrayList<DonHangData>> mapDonHang;
    String Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang2);

        tv_phone = findViewById(R.id.tv_phone);
        Bundle bundle = getIntent().getExtras();
         Phone = bundle.getString("phone");
        tv_phone.setText("Danh sách đơn hàng của tài khoản: " + Phone);

        recylerDonHang = findViewById(R.id.rcOfDonHang);
        getData();
        new LoadData().execute();
    }

    private void getData() {
        mData = FirebaseDatabase.getInstance().getReference();
        mapDonHang = new HashMap<>();

        //
        arrListDH = new ArrayList<>();


        //
//        SharedPreferences sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
//        phone = sharedPreferences.getString("phone", "");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new DonHangAdapter(arrListDH, this);
        recylerDonHang.setAdapter(adapter);
        recylerDonHang.setLayoutManager(layoutManager);
    }

    private class LoadData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //child tới node đơn hàng + số điện thoại
            mData.child("donhangcuatoi").child(Phone).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    arrListDH.clear();
                    // lấy ra các value của node
                    if (dataSnapshot.getValue() != null) {
                        for (final DataSnapshot data : dataSnapshot.getChildren()) {
                            arrListDH.add(data.getKey());

                            final ArrayList<DonHangData> arrDonHang = new ArrayList<>();

                            //child tới node mua ngay trong giỏ hàng
                            mData.child("donhangcuatoi").child(Phone).child(data.getKey()).addValueEventListener(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue() != null) {
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                            arrDonHang.add(dataSnapshot1.getValue(DonHangData.class));
                                        }
                                        mapDonHang.put(data.getKey(), arrDonHang);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                publishProgress();
                            }
                        }, 500);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            adapter.setMapDonHang(mapDonHang);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
