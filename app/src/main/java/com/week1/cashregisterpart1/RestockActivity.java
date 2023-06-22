package com.week1.cashregisterpart1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class
RestockActivity extends AppCompatActivity {

    private ListView listView;
    private Button okButton, backButton;
    private TextView txtProductName, txtProductQuantity, txtTotalPrice;
    private EditText editTextProductNumber;
    private ProductAdapter adapter;
    private List<Product> productList;
    private AlertDialog.Builder builder;
    private Product selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        listView = findViewById(R.id.product_listview);
        txtProductName = findViewById(R.id.textviewProductName);
        txtProductQuantity = findViewById(R.id.textviewQuantity);
        txtTotalPrice = findViewById(R.id.textviewTotalAmount);
        okButton = findViewById(R.id.buttonOk);
        backButton = findViewById(R.id.back_btn);

        builder = new AlertDialog.Builder(this);
        productList = new ArrayList<>();
        productList = ProductList.getInstance().getProductList();

        adapter = new ProductAdapter(this, productList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedProduct = adapter.getItem(position);
                txtProductName.setText(selectedProduct.getName());
            }
        });

        editTextProductNumber = findViewById(R.id.number_picker);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = editTextProductNumber.getText().toString();
                if (selectedProduct == null || userInput.isEmpty()) {
                    Toast.makeText(RestockActivity.this, "Please select a product and enter the quantity", Toast.LENGTH_SHORT).show();
                    return;
                }

                int quantity = Integer.parseInt(userInput);
                if (quantity < 0) {
                    Toast.makeText(RestockActivity.this, "Invalid quantity", Toast.LENGTH_SHORT).show();
                    return;
                }

                double totalPrice = quantity * selectedProduct.getPrice();
                txtProductQuantity.setText(String.valueOf(quantity));
                txtTotalPrice.setText(String.valueOf(totalPrice));

                builder.setMessage("Do you want to update the stock of this product?\nAdd " + quantity + " item(s) to " + selectedProduct.getName())
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ProductList.getInstance().updateProductQuantity(selectedProduct.getName(), selectedProduct.getQuantity() + quantity);
                                productList = ProductList.getInstance().getProductList();
                                adapter.notifyDataSetChanged();
                                Toast.makeText(RestockActivity.this, "Stock updated successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                editTextProductNumber.setText("");
                            }
                        });

                AlertDialog alert = builder.create();
                alert.setTitle("Confirm Purchase");
                alert.show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestockActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
    }
}
