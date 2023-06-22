package com.week1.cashregisterpart1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, List<Product> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitem, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.productNameTextView = convertView.findViewById(R.id.product_name);
            viewHolder.quantityTextView = convertView.findViewById(R.id.quantity);
            viewHolder.priceTextView = convertView.findViewById(R.id.price);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product product = getItem(position);

        viewHolder.productNameTextView.setText(product.getName());
        viewHolder.quantityTextView.setText("Quantity: " + product.getQuantity());
        viewHolder.priceTextView.setText("Price: $" + product.getPrice());

        return convertView;
    }

    private static class ViewHolder {
        TextView productNameTextView;
        TextView quantityTextView;
        TextView priceTextView;
    }

    public void updateData() {
        notifyDataSetChanged();
    }
}
