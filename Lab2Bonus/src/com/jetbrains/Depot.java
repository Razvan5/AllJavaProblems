package com.jetbrains;

import java.util.Arrays;
import java.util.Objects;

public class Depot {

    private String name;
    private Vehicle[] vehicles;

    Depot(String name){
         this.name = name;
        this.vehicles=null;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void setVehicles(Vehicle...vehiclesList){
        this.vehicles=vehiclesList;
        for(Vehicle v : vehicles){
            v.setDepot(this);
        }
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    @Override
    public String toString() {
        return "Depot{Name: "+name+", Vehicles: "+ Arrays.toString(vehicles) +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depot depot = (Depot) o;
        return name.equals(depot.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
