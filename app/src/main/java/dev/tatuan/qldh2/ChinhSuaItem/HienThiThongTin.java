package dev.tatuan.qldh2.ChinhSuaItem;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import dev.tatuan.qldh2.R;
import dev.tatuan.qldh2.UploadNewItem.ObjectUpload;

public class HienThiThongTin extends AppCompatActivity {
    ImageView imgView;
    Button btnChange;
    Button btnDelete;
    EditText edt_change_name, edt_change_type, edt_change_price, edt_change_energy, edt_change_glass, edt_change_madein;
    DatabaseReference mData;
    String name, type, price, glass, energy, madein, image, HangDongHo;
    String id2, name2, type2, price2, glass2, energy2, madein2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_thong_tin);

        //
        imgView = findViewById(R.id.imgViewChange);

        //
        btnChange = findViewById(R.id.btnChange);
        btnDelete = findViewById(R.id.btnDelete);
        //
        edt_change_name = findViewById(R.id.edt_change_name);
        edt_change_type = findViewById(R.id.edt_change_type);
        edt_change_price = findViewById(R.id.edt_change_price);
        edt_change_energy = findViewById(R.id.edt_change_energy);
        edt_change_glass = findViewById(R.id.edt_change_glass);
        edt_change_madein = findViewById(R.id.edt_change_madein);


        Bundle bundle = getIntent().getExtras();

        HangDongHo = bundle.getString("tenhang");

        id2 = bundle.getString("id");
        name = bundle.getString("name");
        type = bundle.getString("type");
        price = bundle.getString("price");
        energy = bundle.getString("energy");
        glass = bundle.getString("glass");
        madein = bundle.getString("madein");
        image = bundle.getString("image");


        //
        mData = FirebaseDatabase.getInstance().getReference("Watch").child(HangDongHo).child(id2);

        //
        edt_change_name.setText(name);
        edt_change_type.setText(type);
        edt_change_price.setText(price);
        edt_change_energy.setText(energy);
        edt_change_glass.setText(glass);
        edt_change_madein.setText(madein);
        Picasso.with(this).load(image).into(imgView);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name2 = edt_change_name.getText().toString().trim();
                type2 = edt_change_type.getText().toString().trim();
                price2 = edt_change_price.getText().toString().trim();
                glass2 = edt_change_energy.getText().toString().trim();
                energy2 = edt_change_glass.getText().toString().trim();
                madein2 = edt_change_madein.getText().toString().trim();
                changeInfor();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem();
            }
        });

    }

    private void deleteItem() {
        final AlertDialog.Builder aler = new AlertDialog.Builder(this);
        aler.setCancelable(false);
        aler.setMessage("Xoá sản phẩm đã chọn?");
        aler.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                DatabaseReference myRef = mData.child("Watch").child(HangDongHo).child(id2);
                mData.removeValue();
                dialog.dismiss();
                finish();

            }
        });
        aler.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        aler.show();


    }

    private void changeInfor() {
        mData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    ObjectUpload objectDataChange = new ObjectUpload(id2, image, name2, price2, type2, glass2, madein2, energy2);
                    mData.setValue(objectDataChange);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
