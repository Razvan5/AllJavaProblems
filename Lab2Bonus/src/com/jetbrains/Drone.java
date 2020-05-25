package com.jetbrains;

public class Drone extends Vehicle {
    public Drone(String name,int fuel,int[][] costMatrix){
        super(name,fuel,costMatrix);
    }

    @Override
    public String toString() {
        return "Drone{Name: "+this.getName()+"}";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vehicle)){
            return false;
        }
        Vehicle other = (Vehicle) obj;

        return this.getName().equals(other.getName());
    }
}
