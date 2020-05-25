package com.jetbrains;

import java.util.ArrayList;

public class Hospital implements Comparable<Hospital>{

    public int capacity;
    public ArrayList<Resident> residentList;
    public String hospitalName;
    public String order;
    int preferenceLevel=1;

    public Hospital(String hospitalName,String order) {
        this.hospitalName = hospitalName;
        this.order=order;
    }

    public Hospital(String name, int capacity, ArrayList<Resident> residentList,String order) {
        this.capacity=capacity;
        this.hospitalName=name;
        this.residentList = residentList;
        this.order=order;
    }

    public int getPreferenceLevel() {
        return preferenceLevel;
    }

    public void setPreferenceLevel(int preferenceLevel) {
        this.preferenceLevel = preferenceLevel;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Resident> getResidentList() {
        return residentList;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public void setResidentList(ArrayList<Resident> residentList) {
        this.residentList = residentList;
    }

    public String getOrder() {
        return order;
    }

    @Override
    public int compareTo(Hospital hospital) {
        return this.order.compareTo(hospital.order);
    }

    @Override
    public String toString() {
        return hospitalName;
    }

}
