package com.jetbrains;

public class Car extends Vehicle {


    public Car(String name,int fuel,int[][] costMatrix){
       super(name,fuel,costMatrix);
    }

    @Override
    public String toString() {
        return "Car{Name: "+this.getName()+" Nodes Chosen:"+this.getVertexVisited()+"}";
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
