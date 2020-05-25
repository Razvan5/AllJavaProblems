package com.jetbrains;

import javax.print.Doc;
import java.io.IOException;

public class SaveCommand {

    Catalog Object;

    public SaveCommand(Catalog object) {
        Object = object;
    }

    public Catalog getObject() {
        return Object;
    }

    public void setObject(Catalog object) {
        Object = object;
    }

    public void save(Catalog catalog) throws IOException {
        CatalogUtil.save(catalog);
    }

    public void createAndSave(String name,String path,Document...documents) throws IOException {
        Catalog catalog = new Catalog(name,path);
        for(Document doc :documents){
            catalog.add(doc);
        }
        CatalogUtil.save(catalog);
    }
    
}
