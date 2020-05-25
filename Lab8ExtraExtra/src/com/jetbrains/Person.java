package com.jetbrains;
import javafx.beans.property.SimpleStringProperty;

public class Person {
    private final SimpleStringProperty nume;
    private final SimpleStringProperty numarMatricol;
    private final SimpleStringProperty grupa;
    private final SimpleStringProperty an;
    private final SimpleStringProperty bursa;

    public Person(String nume, String nrMatricol, String grupa,String an,String bursa) {
        this.nume = new SimpleStringProperty(nume);
        this.numarMatricol = new SimpleStringProperty(nrMatricol);
        this.grupa = new SimpleStringProperty(grupa);
        this.an = new SimpleStringProperty(an);
        this.bursa = new SimpleStringProperty(bursa);
    }

    public SimpleStringProperty anProperty() {
        return an;
    }

    public SimpleStringProperty bursaProperty() {
        return bursa;
    }

    public SimpleStringProperty grupaProperty() {
        return grupa;
    }

    public SimpleStringProperty numarMatricolProperty() {
        return numarMatricol;
    }

    public SimpleStringProperty numeProperty() {
        return nume;
    }
}
