package com.jetbrains;

import java.util.Objects;

public class Client {

    private String name;
    private int order;

    public Client() {
    }
    public Client(String name,int order){
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    private void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return name.equals(client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Client{"+name+",Order: "+order+"}";
    }
}
