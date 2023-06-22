package com.week1.cashregisterpart1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PurchasedProductAdapter extends ArrayAdapter<PurchasedProduct> {

    public PurchasedProductAdapter(Context context, List<PurchasedProduct> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the custom layout for each item
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitem, parent, false);
        }

        // Get the current product object
        PurchasedProduct product = getItem(position);

        // Find and set the data to the views in the custom layout
        TextView productNameTextView = convertView.findViewById(R.id.product_name);
        TextView quantityTextView = convertView.findViewById(R.id.quantity);
        TextView priceTextView = convertView.findViewById(R.id.price);

        productNameTextView.setText(product.getName());
        quantityTextView.setText("Quantity: " + product.getQuantity());
        priceTextView.setText("Price: $" + product.getPrice());
//        priceTextView.setText("Price: $" + product.getTimestamp());

        return convertView;
    }
}
