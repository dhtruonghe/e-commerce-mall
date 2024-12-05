/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author haitr
 */
public class Cart {

    private int ProductID;
    private String ProductName;
    private int quantity;
    private double UnitPrice;
    private float discount;

    public Cart() {
    }

    public Cart(int ProductID, String ProductName, int quantity, double UnitPrice, float discount) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.quantity = quantity;
        this.UnitPrice = UnitPrice;
        this.discount = discount;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cart cart = (Cart) obj;
        return this.getProductID() == cart.getProductID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductID());
    }

}
