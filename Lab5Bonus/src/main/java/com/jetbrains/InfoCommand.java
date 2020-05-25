package com.jetbrains;

import java.io.File;
import java.util.Date;

public class InfoCommand {

    String Object;

    public InfoCommand() {
    }

    public void info(String doc){
        File f = new File(doc);
        if(f.exists()){
            System.out.println("Name: "+ f.getName());
            System.out.println("Path: "+ f.getAbsolutePath());
            System.out.println("Size: "+ f.length());
            System.out.println("Writeable: "+ f.canWrite());
            System.out.println("Readable: "+ f.canRead());
            Date date = new Date(f.lastModified());
            System.out.println("Last Modified: "+ date.toString());
        }else{
            System.out.println("The File does not exist");
        }
    }
}
