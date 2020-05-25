package com.jetbrains;

import java.awt.*;
import java.io.*;

public class CatalogUtil {

    public static void save(Catalog catalog) throws IOException{
        try(var oos = new ObjectOutputStream(new FileOutputStream(catalog.getPath()))){
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path) throws InvalidCatalogException, IOException, ClassNotFoundException {
        InputStream inputStream = CatalogUtil.class.getResourceAsStream(path);

        FileInputStream fi = new FileInputStream(new File(path));
        ObjectInputStream oi = new ObjectInputStream(fi);

        Catalog catalogObjectFromFile = (Catalog) oi.readObject();
        System.out.println(catalogObjectFromFile.toString());
        return catalogObjectFromFile;

    }

    public static void view(Document doc) throws IOException {

        if(doc.getLocation().contains("http")){
            String url_open = doc.getLocation();
            Desktop.getDesktop().browse(java.net.URI.create(url_open));
        }
        else{
            Desktop desktop = Desktop.getDesktop();
            File fileToView = new File(doc.getLocation());
            Desktop.getDesktop().open(fileToView);
        }

    }

}
