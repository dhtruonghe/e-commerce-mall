/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author haitr
 */
public class CustomerCustomerDemo {
    
    private String customerID,customerTypeID;

    public CustomerCustomerDemo() {
    }

    public CustomerCustomerDemo(String customerID, String customerTypeID) {
        this.customerID = customerID;
        this.customerTypeID = customerTypeID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerTypeID() {
        return customerTypeID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setCustomerTypeID(String customerTypeID) {
        this.customerTypeID = customerTypeID;
    }

    @Override
    public String toString() {
        return "CustomerCustomerDemo{" + "customerID=" + customerID + ", customerTypeID=" + customerTypeID + '}';
    }
}
