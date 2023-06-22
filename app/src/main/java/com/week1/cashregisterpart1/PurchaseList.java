package com.week1.cashregisterpart1;

import java.util.ArrayList;


import java.util.List;

public class PurchaseList {
    private List<PurchasedProduct> purchases;
    private static PurchaseList instance;

    private PurchaseList() {
        purchases = new ArrayList<>();
    }

    public void addPurchase(PurchasedProduct purchase) {
        purchases.add(purchase);
    }

    public List<PurchasedProduct> getPurchases() {
        return purchases;
    }

    public static PurchaseList getInstance() {
        if (instance == null) {
            instance = new PurchaseList();
        }
        return instance;
    }
}