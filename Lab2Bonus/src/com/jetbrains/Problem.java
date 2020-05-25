package com.jetbrains;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem {

    private Depot[] depots;
    private Client[] clients;

    public Problem() { }


    public Problem(Depot[] depots, Client[] clients) {
        this.depots = depots;
        this.clients = clients;
    }

    Vehicle[] removeNulls(Vehicle[] nullsArray) {
        int countNulls = 0;

        for (int i = 0; i < nullsArray.length; i++) { // count nulls in array
            if (nullsArray[i] == null) {
                countNulls++;
            }
        }
        // creating new array with new length (length of first array - counted nulls)
        Vehicle[] nullsRemoved = new Vehicle[nullsArray.length - countNulls];

        for (int i = 0, j = 0; i < nullsArray.length; i++) {

            if (nullsArray[i] != null) {
                nullsRemoved[j] = nullsArray[i];
                j++;
            }
        }
        return nullsRemoved;
    }

    public Vehicle[] getVehicles(){
        Vehicle[] vehicles = new Vehicle[200];
        int i =0;
        for(Depot d:depots){
            for(Vehicle v:d.getVehicles()){
                vehicles[i]=v;
                i++;
            }
        }

        return removeNulls(vehicles);
    }

    public void solveProblem(int[][] graphDAG){
        Map<Integer, ArrayList<Vehicle>> tours = new HashMap<>();
        int index=0;
        Dijsktra dijsktraInstance = new Dijsktra();
        dijsktraInstance.dijkstra(graphDAG,0);
        //System.out.println(Arrays.toString(this.getVehicles()));
        for(Vehicle vehicle:this.getVehicles()){
            System.out.println(vehicle+"Initial Fuel:"+vehicle.getFuel());
        }
        for(Vehicle vehicle : this.getVehicles()){
            int[] distances = dijsktraInstance.getDistancesMember();
            tours.put(index, new ArrayList<>());
            for(int i=1;i<distances.length;++i)
                if(vehicle.getFuel()>=distances[i] && distances[i]!=0){
                    vehicle.setFuel(vehicle.getFuel()-distances[i]);
                    System.out.println(vehicle+"Distances:"+distances[i]+" Fuel:"+vehicle.getFuel());
                    distances[i]=0;
                    tours.get(index).add(vehicle);
                    vehicle.addVertexToList(i);
                }
            index++;
        }
        System.out.println(tours);
    }

    @Override
    public String toString() {
        return "Problem{" +
                "depots=" + Arrays.toString(depots) +
                ", clients=" + Arrays.toString(clients) +
                '}';
    }
}
