package dev.tatuan.qldh2.DanhSachDongHo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.tatuan.qldh2.ItemClickListener;
import dev.tatuan.qldh2.R;

/**
 * Created by tatuan on 26/04/2018.
 */

public class ViewHolderDongHo extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView img_hinhAnh;
    TextView tv_tenDongHo, tv_giaDongHo;
    ItemClickListener itemClickListener;

    public ViewHolderDongHo(View itemView) {
        super(itemView);
        img_hinhAnh = itemView.findViewById(R.id.img_hinhAnh);
        tv_tenDongHo = itemView.findViewById(R.id.tv_tenDongHo);
        tv_giaDongHo = itemView.findViewById(R.id.tv_giaDongHo);


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
