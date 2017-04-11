package com.example.siddharthsaha.smartpay_frontend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siddharth Saha on 4/11/2017.
 */

public class Product {
    String name;
    String age;
    int photoId;

    public Product(String name, String age, int photoID) {
        this.name = name;
        this.age = age;
        this.photoId = photoID;
    }

    private List<Product> products;

//    private void initializeData(){
//        products = new ArrayList<>();
//        products.add(new Product("Emma Wilson", "23 years old", R.drawable.clock));
//        products.add(new Product("Lavery Maiss", "25 years old", R.drawable.clock));
//        products.add(new Product("Lillie Watts", "35 years old", R.drawable.clock));
//    }
}
