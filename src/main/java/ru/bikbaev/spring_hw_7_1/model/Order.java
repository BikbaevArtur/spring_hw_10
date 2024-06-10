package ru.bikbaev.spring_hw_7_1.model;

import lombok.Data;

@Data
public class Order {
    private int idProduct;
    private String productName;
    private int quantity;
    private int purchasePriceProduct;
}
