package com.jetbrains;

import freemarker.template.*;

import javax.print.Doc;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class ReportCommand {

    Catalog Object;

    public ReportCommand(Catalog object) {
        Object = object;
    }



    public void generateReport(Catalog catalog,Configuration cfg) throws IOException, TemplateException {

        Map<String, Object> input = new HashMap<String, Object>();

        input.put("title", "Catalog");

        input.put("exampleObject", new ValueExampleObject("Documents", "Razvan"));

        List<Document> systems = new ArrayList<Document>();

        for(Document d:catalog.getDocuments()){
            systems.add(d);
        }

        input.put("systems", systems);

        Template template = cfg.getTemplate("helloworld.ftl");

        //Writer consoleWriter = new OutputStreamWriter(System.out);
        //template.process(input, consoleWriter);
        try (Writer fileWriter = new FileWriter(new File("Report.html"))) {
            template.process(input, fileWriter);
        }
    }
}
