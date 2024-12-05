/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author haitr
 */
public class Region {
    private int regionID;
    private String regionDescription;

    public Region() {
    }

    public Region(int regionID, String regionDescription) {
        this.regionID = regionID;
        this.regionDescription = regionDescription;
    }

    public int getRegionID() {
        return regionID;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    @Override
    public String toString() {
        return "Region{" + "regionID=" + regionID + ", regionDescription=" + regionDescription + '}';
    }
}
