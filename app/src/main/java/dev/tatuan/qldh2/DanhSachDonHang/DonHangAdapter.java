package dev.tatuan.qldh2.DanhSachDonHang;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import dev.tatuan.qldh2.R;


/**
 * Created by Anh on 21/04/2018.
 */

public class DonHangAdapter extends RecyclerView.Adapter<DonHangViewHolder> {

    private ArrayList<String> arrListDH = new ArrayList<>();//thu lai di
    private Context mContext;
    private ArrayList<DonHangData> arrDH;
    private ItemDonHangAdapter adapter;

    private HashMap<String, ArrayList<DonHangData>> mapDonHang;

    public DonHangAdapter(ArrayList<String> arrListDH, Context mContext) {
        this.arrListDH = arrListDH;
        this.mContext = mContext;
    }

    @Override
    public DonHangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_donhang, parent, false);
        return new DonHangViewHolder(view);
    }
    //
    @Override
    public void onBindViewHolder(DonHangViewHolder holder, int position) {

        if (mapDonHang != null && mapDonHang.size() > 0){
            arrDH = mapDonHang.get(arrListDH.get(position));

            holder.tv_time.setText("Time to buy: " + arrListDH.get(position));

            adapter = new ItemDonHangAdapter(arrDH, mContext);


            holder.recyclerDonHang.setAdapter(adapter);
            holder.recyclerDonHang.setLayoutManager(new LinearLayoutManager(mContext));
        }



    }

    @Override
    public int getItemCount() {
        return arrListDH.size();
    }

    public void setMapDonHang(HashMap<String, ArrayList<DonHangData>> mapDonHang) {
        this.mapDonHang = mapDonHang;
    }
}
