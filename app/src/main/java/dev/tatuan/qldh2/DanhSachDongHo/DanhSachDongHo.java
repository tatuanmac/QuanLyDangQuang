package dev.tatuan.qldh2.DanhSachDongHo;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import dev.tatuan.qldh2.ChinhSuaItem.HienThiThongTin;
import dev.tatuan.qldh2.ItemClickListener;
import dev.tatuan.qldh2.R;
import dev.tatuan.qldh2.UploadNewItem.ThemAnh;

public class DanhSachDongHo extends AppCompatActivity {

    TextView tenhang;

    DatabaseReference databaseReference;

    //
    StaggeredGridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    String HangDongHo;

    Button btnThemDongHo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_dong_ho);

        tenhang = findViewById(R.id.tenhang);

        //lay ten hang dong ho
        Bundle bundle = getIntent().getExtras();
        HangDongHo = bundle.getString("tenhang");

        Toast.makeText(this, HangDongHo + "", Toast.LENGTH_SHORT).show();

        tenhang.setText(HangDongHo);
        //
        recyclerView = findViewById(R.id.recyclerOfDongHo);

        btnThemDongHo = findViewById(R.id.btn_addnewitem);
        btnThemDongHo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachDongHo.this, ThemAnh.class);
                intent.putExtra("tenhang", HangDongHo);
                startActivity(intent);
            }
        });


        getData();

    }

    private void getData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Watch").child(HangDongHo);
        FirebaseRecyclerAdapter<DongHoData, ViewHolderDongHo> adapter = new FirebaseRecyclerAdapter<DongHoData, ViewHolderDongHo>(DongHoData.class, R.layout.danhsachdonghoitem, ViewHolderDongHo.class, databaseReference) {
            @Override
            protected void populateViewHolder(final ViewHolderDongHo viewHolder, final DongHoData model, int position) {
                Picasso.with(getApplicationContext()).load(String.valueOf(model.getLinkHA())).into(viewHolder.img_hinhAnh);

                viewHolder.tv_tenDongHo.setText(String.valueOf(model.getTenDH()));

                long tien = Long.parseLong(model.getGiaDH());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    viewHolder.tv_giaDongHo.setText(NumberFormat.getNumberInstance(Locale.US).format(tien));
                }
                final DongHoData clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {


                        Intent intent = new Intent(getApplicationContext(), HienThiThongTin.class);

                        intent.putExtra("id", clickItem.getId());
                        intent.putExtra("name", clickItem.getTenDH());
                        intent.putExtra("type", clickItem.getLoaiDH());
                        intent.putExtra("price", clickItem.getGiaDH());
                        intent.putExtra("energy", clickItem.getNangLuong());
                        intent.putExtra("glass", clickItem.getMatKinh());
                        intent.putExtra("madein", clickItem.getXuatXu());
                        intent.putExtra("image", clickItem.getLinkHA());
                        intent.putExtra("tenhang", HangDongHo);

                        startActivity(intent);
                    }
                });
            }
        };
        gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

    }

}
