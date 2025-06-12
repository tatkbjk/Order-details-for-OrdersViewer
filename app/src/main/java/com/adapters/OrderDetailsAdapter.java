package com.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.k22411csampleproject.R;
import com.thaianhthu.models.OrderDetails;

import java.util.List;

public class OrderDetailsAdapter extends ArrayAdapter<OrderDetails> {
    private final int resource;
    public OrderDetailsAdapter(@NonNull Context context, int resource, @NonNull List<OrderDetails> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }
    @SuppressLint("ViewHolder")
    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(resource, parent, false);

        OrderDetails od = getItem(position);

        TextView txtProductName = convertView.findViewById(R.id.txtProductName);
        TextView txtQuantity = convertView.findViewById(R.id.txtQuantity);
        TextView txtPrice = convertView.findViewById(R.id.txtPrice);
        TextView txtDiscount = convertView.findViewById(R.id.txtDiscount);
        TextView txtVAT = convertView.findViewById(R.id.txtVAT);
        TextView txtTotal = convertView.findViewById(R.id.txtTotalValue);

        if (od != null) {
            txtProductName.setText(getContext().getString(R.string.product_name, String.valueOf(od.getProductName() != null ? od.getProductName() : "")));
            txtQuantity.setText(getContext().getString(R.string.quantity, od.getQuantity()));
            txtPrice.setText(getContext().getString(R.string.price, od.getPrice()));
            txtDiscount.setText(getContext().getString(R.string.discount, od.getDiscount()));
            txtVAT.setText(getContext().getString(R.string.vat, od.getVAT()));
            txtTotal.setText(getContext().getString(R.string.total_price, od.getTotalPrice()));
        } else {
            txtProductName.setText("");
            txtQuantity.setText("");
            txtPrice.setText("");
            txtDiscount.setText("");
            txtVAT.setText("");
            txtTotal.setText("");
        }
        return convertView;
    }
}
