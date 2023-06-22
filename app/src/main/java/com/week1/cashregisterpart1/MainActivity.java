package com.week1.cashregisterpart1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button buyButton, manageButton;
    private TextView txtProductName, txtProductQuantity, txtTotalPrice;
    private EditText editTextProductNumber;
    private ProductAdapter adapter;
    private List<Product> productList;
    private Product selectedProduct;
    private AlertDialog.Builder builder;
    private int productQuantity;
    private double productPrice;
    private String userInput;
    private double totalPrice;
    private String productName;
    private int productNumber;

    private ArrayList<PurchasedProduct> purchasedProducts;
    private PurchaseList purchaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.product_listview);
        txtProductName = findViewById(R.id.textviewProductName);
        txtProductQuantity = findViewById(R.id.textviewQuantity);
        txtTotalPrice = findViewById(R.id.textviewTotalAmount);
        buyButton = findViewById(R.id.buttonBuy);
        manageButton = findViewById(R.id.buttonManage);
        editTextProductNumber = findViewById(R.id.number_picker);

        builder = new AlertDialog.Builder(this);
        purchasedProducts = new ArrayList<>();

        purchaseList = PurchaseList.getInstance();
        productList = ProductList.getInstance().getProductList();

        adapter = new ProductAdapter(this, productList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedProduct = productList.get(position);
                productName = selectedProduct.getName();
                productQuantity = selectedProduct.getQuantity();
                productPrice = selectedProduct.getPrice();
                txtProductName.setText(productName);
            }
        });

        editTextProductNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    userInput = editTextProductNumber.getText().toString();
                    if (userInput.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please enter product number", Toast.LENGTH_SHORT).show();
                    } else {
                        productNumber = Integer.parseInt(userInput);
                        if (productNumber > productQuantity) {
                            Toast.makeText(MainActivity.this, "We have less stock", Toast.LENGTH_SHORT).show();
                        } else {
                            txtProductQuantity.setText(userInput);
                            totalPrice = productNumber * productPrice;
                            txtTotalPrice.setText(String.valueOf(totalPrice));
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedProduct == null || userInput.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please select a product and enter product number", Toast.LENGTH_SHORT).show();
                } else {
                    builder.setMessage("Do you want to confirm this purchase?\nYour Purchase is " + userInput + " " + productName + " for " + totalPrice)
                            .setTitle("Confirm Purchase")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                                    Date currentDate = new Date();
                                    String timestamp = dateFormat.format(currentDate);
                                    purchasedProducts.add(new PurchasedProduct(productName, productNumber, totalPrice, timestamp));
                                    purchaseList.addPurchase(new PurchasedProduct(productName, productNumber, totalPrice, timestamp));
                                    selectedProduct.setQuantity(selectedProduct.getQuantity() - productNumber);
                                    adapter.notifyDataSetChanged();

                                    editTextProductNumber.setText("");
                                    txtProductQuantity.setText("Total Quantity");
                                    txtTotalPrice.setText("Total Price");
                                    txtProductName.setText("Selected Product");

                                    Toast.makeText(MainActivity.this, "Thanks for Purchasing!", Toast.LENGTH_SHORT).show();
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
            }
        });

        manageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });
    }
}
