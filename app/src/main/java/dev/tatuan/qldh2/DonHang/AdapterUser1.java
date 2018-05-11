package dev.tatuan.qldh2.DonHang;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dev.tatuan.qldh2.DanhSachDonHang.DonHang2;
import dev.tatuan.qldh2.ItemClickListener;
import dev.tatuan.qldh2.R;

/**
 * Created by tatuan on 25/04/2018.
 */

public class AdapterUser1 extends RecyclerView.Adapter<ViewHolderItemDonHang1> {
    Context mContext;
    ArrayList<UserData1> userData1;

    public AdapterUser1(Context mContext, ArrayList<UserData1> userData1) {
        this.mContext = mContext;
        this.userData1 = userData1;
    }

    @Override
    public ViewHolderItemDonHang1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_quan_ly_don_hang, parent, false);

//qua may ao coi dk chua
        // chua co phone o oi

        return new ViewHolderItemDonHang1(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderItemDonHang1 holder, int position) {
        UserData1 data1 = userData1.get(position);
        final String phone = data1.getSodienthoai();
        holder.tv_user_Phone.setText(phone);
        holder.tv_user_Address.setText(data1.getDiachi());
        holder.tv_user_Name.setText(data1.getTen());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(mContext, DonHang2.class);
                intent.putExtra("phone", phone);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userData1.size();
    }
}
