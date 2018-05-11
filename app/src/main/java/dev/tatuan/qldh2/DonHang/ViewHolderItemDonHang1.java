package dev.tatuan.qldh2.DonHang;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import dev.tatuan.qldh2.ItemClickListener;
import dev.tatuan.qldh2.R;

/**
 * Created by tatuan on 25/04/2018.
 */

public class ViewHolderItemDonHang1 extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tv_user_Phone;
    TextView tv_user_Address;
    TextView tv_user_Name;

    ItemClickListener itemClickListener;

    public ViewHolderItemDonHang1(android.view.View itemView) {
        super(itemView);


        tv_user_Phone = itemView.findViewById(R.id.tv_user_phone);
        tv_user_Address = itemView.findViewById(R.id.tv_user_address);
        tv_user_Name = itemView.findViewById(R.id.tv_user_name);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
