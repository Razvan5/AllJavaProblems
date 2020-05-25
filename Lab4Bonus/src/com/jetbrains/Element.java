package com.jetbrains;

import java.util.List;

public class Element {

    Hospital H;
    Resident R;

    Element(Hospital hos,Resident res){
        this.H = hos;
        this.R = res;
    }


    public void setR(Resident r) {
        R = r;
    }

    public void setH(Hospital h) {
        H = h;
    }


    public Hospital getH() {
        return H;
    }

    public Resident getR() {
        return R;
    }

    @Override
    public String toString() {
        return "Asociere{"+" H=" + H + ", R=" + R + '}'+"\n";
    }
}
