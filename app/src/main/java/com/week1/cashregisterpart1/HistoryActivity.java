package com.week1.cashregisterpart1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ListView listView;
    private PurchasedProductAdapter adapter;
    private List<PurchasedProduct> purchasedProducts;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        backButton = findViewById(R.id.backButton2);
        listView = findViewById(R.id.list_view);

        purchasedProducts = PurchaseList.getInstance().getPurchases();
        adapter = new PurchasedProductAdapter(this, purchasedProducts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PurchasedProduct clickedProduct = purchasedProducts.get(position);
                showProductDetailsDialog(clickedProduct);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, ManagerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showProductDetailsDialog(PurchasedProduct product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Product Details");

        String details = "Product Name: " + product.getName() + "\n"
                + "Quantity: " + product.getQuantity() + "\n"
                + "Price: " + product.getPrice() + "\n"
                + "Timestamp: " + product.getTimestamp();

        builder.setMessage(details);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
