package com.jetbrains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {

    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    Catalog(String name,String path){
        this.name=name;
        this.path=path;
    }

    public String getName() {
        return name;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public String getPath() {
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void add(Document doc){
        documents.add(doc);
    }

    public Document findById(String id){
        return documents.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", documents=" + documents +
                '}';
    }
}
