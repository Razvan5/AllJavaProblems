package com.jetbrains;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Vehicle {

    private String name;
    private Depot depot;
    private float fuel;
    private List<Integer> vertexVisited=new ArrayList<>();
    private int[][] costMatrix;

    public Vehicle(){
        name=null;
        depot=null;
        costMatrix=null;
        fuel=0;
    }

    public Vehicle(String name,int fuel,int[][] costMatrix){
        this.name = name;
        this.fuel = fuel;
        this.costMatrix=costMatrix;
    }

    public void addVertexToList(Integer vertex){
        vertexVisited.add(vertex);
    }

    public List<Integer> getVertexVisited() {
        return vertexVisited;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Depot getDepot() {
        return depot;
    }

    protected void setDepot(Depot depot) {
        this.depot = depot;
    }

    @Override
    public String toString() {
        return "Vehicle{Name: "+name+"}";
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vehicle)){
            return false;
        }
        Vehicle other = (Vehicle) obj;

        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, depot);
    }
}
