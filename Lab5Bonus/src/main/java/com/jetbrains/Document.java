package com.jetbrains;

import com.sun.source.tree.UsesTree;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Document implements Serializable {

    public String id;
    public String name;
    public String location;

    Document(String name,String location){
        this.location=location;
        this.name=name;
    }

    Document(String id,String name,String location){
        this.id = id;
        this.name=name;
        this.location=location;
    }

    private Map<String,Object> tags = new HashMap<>();

    public String getName() {
        return name;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }

    public void addTag(String key, Object obj){
        tags.put(key,obj);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", tags=" + tags +
                '}';
    }
}
