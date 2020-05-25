package com.jetbrains;

import java.util.Comparator;
import java.util.TreeSet;

public class Resident implements Comparable<Resident> {

    public String residentName;
    public TreeSet<Hospital> hospitalList;
    int preferenceLevel=1;

    public Resident(String residentName) {
        this.residentName = residentName;
    }

    public Resident(String name, TreeSet<Hospital> hospitalList) {
        this.residentName=name;
        this.hospitalList = hospitalList;
    }

    public int getPreferenceLevel() {
        return preferenceLevel;
    }

    public void setPreferenceLevel(int preferenceLevel) {
        this.preferenceLevel = preferenceLevel;
    }

    public void setHospitalList(TreeSet<Hospital> hospitalList) {
        this.hospitalList = hospitalList;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public TreeSet<Hospital> getHospitalList() {
        return hospitalList;
    }

    public String getResidentName() {
        return residentName;
    }

    @Override
    public int compareTo(Resident resident) {
        return this.residentName.compareTo(resident.residentName);
    }

    @Override
    public String toString() {
        return residentName;
    }
}
