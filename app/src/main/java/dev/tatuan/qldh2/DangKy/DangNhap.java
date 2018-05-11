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

import dev.tatuan.qldh2.R;
import dev.tatuan.qldh2.admin.MainAdmin;

public class DangNhap extends AppCompatActivity {
    EditText signIn_Phone, signIn_passWord;
    TextView tv_signIn;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signIn_Phone = findViewById(R.id.signIn_Phone);
        signIn_passWord = findViewById(R.id.signIn_passWord);
        tv_signIn = findViewById(R.id.tv_signIn);

        mDialog = new ProgressDialog(DangNhap.this);
        mDialog.setMessage("Waiting...");
        Dangnhap();

    }

    public void Dangnhap() {
        tv_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check null
                if (signIn_Phone.getText().toString().equals("")) {
                    //Dialog
                    Toast.makeText(DangNhap.this, ":))", Toast.LENGTH_SHORT).show();
                } else {
                    User();
                    mDialog.dismiss();

                }
            }
        });
    }

    public void User() {
        //User
        DatabaseReference table_user = FirebaseDatabase.getInstance().getReference("account").child("manager");
        mDialog.show();
        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //
                if (dataSnapshot.child(signIn_Phone.getText().toString()).exists()) {

                    //get user information
                    DData data = dataSnapshot.child(signIn_Phone.getText().toString()).getValue(DData.class);

                    if (data.getMatkhau().equals(signIn_passWord.getText().toString())) {


                        Intent intent = new Intent(getApplicationContext(), MainAdmin.class);
                        //
                        intent.putExtra("Name", data.getTen().trim());
                        intent.putExtra("Phone", signIn_Phone.getText().toString());
                        startActivity(intent);
                        finish();
                    } else
                        Toast.makeText(DangNhap.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
