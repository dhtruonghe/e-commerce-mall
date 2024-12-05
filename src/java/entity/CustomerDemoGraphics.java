/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author haitr
 */
public class CustomerDemoGraphics {

    private String customerTypeID, customerDesc;

    public CustomerDemoGraphics() {
    }

    public CustomerDemoGraphics(String customerTypeID, String customerDesc) {
        this.customerTypeID = customerTypeID;
        this.customerDesc = customerDesc;
    }

    public String getCustomerTypeID() {
        return customerTypeID;
    }

    public String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerTypeID(String customerTypeID) {
        this.customerTypeID = customerTypeID;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }

    @Override
    public String toString() {
        return "CustomerDemographics{" + "customerTypeID=" + customerTypeID + ", customerDesc=" + customerDesc + '}';
    }
}
