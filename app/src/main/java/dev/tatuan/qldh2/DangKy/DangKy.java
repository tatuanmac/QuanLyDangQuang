package dev.tatuan.qldh2.DangKy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dev.tatuan.qldh2.DonHang.UserData1;
import dev.tatuan.qldh2.R;


/**
 * Created by tatuan on 17/04/2018.
 */

public class DangKy extends AppCompatActivity {
    EditText edt_sdt, edt_MatKhau, edt_HoTen;
    TextView tv_DangKy;

    ProgressDialog mDialog;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        dangky();
    }

    private void dangky() {

        tv_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = new ProgressDialog(DangKy.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();


                final DatabaseReference dbDangKy = FirebaseDatabase.getInstance().getReference("account").child("manager");
                dbDangKy.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edt_sdt.getText().toString()).exists()) {
                            mDialog.dismiss();
                            Toast.makeText(DangKy.this, "SĐT ĐÃ TỒN TẠI ", Toast.LENGTH_SHORT).show();

                        } else {
                            mDialog.dismiss();
                            //
                            DData data = new DData(edt_sdt.getText().toString(),edt_MatKhau.getText().toString(), edt_HoTen.getText().toString());
                            //
                            dbDangKy.child(edt_sdt.getText().toString()).setValue(data);

                            Toast.makeText(DangKy.this, "ĐĂNG KÝ THÀNH CÔNG ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Main.class));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void init() {
        edt_sdt = findViewById(R.id.signUp_Phone);

        edt_MatKhau = findViewById(R.id.signUp_passWord);

        edt_HoTen = findViewById(R.id.signUp_Name);


        tv_DangKy = findViewById(R.id.tv_signUp);
    }

}
