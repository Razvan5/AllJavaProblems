package com.jetbrains;

import java.io.*;
import java.util.*;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import javafx.scene.web.HTMLEditorSkin;

public class Main {
    private  void testCreateSave() throws IOException {

        Catalog catalogOfDocuments = new Catalog("Java Resources", "Catalog.json");

        Document documentText = new Document("document", "Fisier Personal",   "document.txt");
        Document cursJavaLaURL = new Document("java2", "Java Course 2",   "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        documentText.addTag("type","fisier");
        cursJavaLaURL.addTag("type","curs");

        catalogOfDocuments.add(documentText);
        catalogOfDocuments.add(cursJavaLaURL);

        //Salvarea catalogului cu documente

        CatalogUtil.save(catalogOfDocuments);

    }

    public Catalog testLoadView(String path) throws InvalidCatalogException, IOException, ClassNotFoundException {
        System.out.println(path);
        ObjectInputStream ios = new ObjectInputStream(new FileInputStream(path));
        Catalog temp = null;
        try {
            while ((temp =(Catalog) ios.readObject()) != null) {
                System.out.println(temp);
            }
        } catch (EOFException e) {

        } finally {
            ios.close();
            return temp;
        }
    }

    private void function() throws Exception{
        //Configuring FreeMarker ONCE
        Configuration cfg = new Configuration();
        // Where do we load the templates from:
        cfg.setClassForTemplateLoading(Main.class, "templates");

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2.1. Prepare the template input:

        Map<String, Object> input = new HashMap<String, Object>();

        input.put("title", "Catalog");

        input.put("exampleObject", new ValueExampleObject("Java object", "me"));

        List<ValueExampleObject> systems = new ArrayList<ValueExampleObject>();

        systems.add(new ValueExampleObject("Android", "Google"));
        systems.add(new ValueExampleObject("iOS States", "Apple"));
        systems.add(new ValueExampleObject("Ubuntu", "Canonical"));
        systems.add(new ValueExampleObject("Windows7", "Microsoft"));

        input.put("systems", systems);
        Template template = cfg.getTemplate("helloworld.ftl");

        //Writer consoleWriter = new OutputStreamWriter(System.out);
        //template.process(input, consoleWriter);
        Writer fileWriter = new FileWriter(new File("output.html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }
    }

    public static void main(String[] args) throws Exception {

        //Configuring FreeMarker ONCE
        Configuration cfg = new Configuration();
        // Where do we load the templates from:
        cfg.setClassForTemplateLoading(Main.class, "templates");

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Scanner shellInput = new Scanner(System.in);
        String tempLine = shellInput.nextLine();
        InfoCommand infoCommand = new InfoCommand();

        Catalog catalogOfDocuments = new Catalog("Java Resources", "Catalog.json");

        Document documentText = new Document("document", "Fisier Personal",   "document.txt");
        Document cursJavaLaURL = new Document("java2", "Java Course 2",   "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        documentText.addTag("type","fisier");
        cursJavaLaURL.addTag("type","curs");

        catalogOfDocuments.add(documentText);
        catalogOfDocuments.add(cursJavaLaURL);





        while(!tempLine.equals("exit")){
                 System.out.println("Enter command: ");
                 tempLine = shellInput.nextLine();


                 if(tempLine.matches("info [a-zA-Z0-9in]+.[a-zA-Z]+")){
                    String sTemp1 = tempLine.substring(5);
                    infoCommand.info(sTemp1);
                 }
                 else if(tempLine.matches("save [a-zA-Z0-9in]+.[a-zA-Z]+")){
                     String sTemp2 = tempLine.substring(5);
                 }
                 else if(tempLine.matches("view [a-zA-Z0-9in]+.[a-zA-Z]+")){
                     String sTemp4 = tempLine.substring(5);
                     Main mainApplication = new Main();
                     Catalog catalog = mainApplication.testLoadView(sTemp4);
                     for(Document doc: catalog.getDocuments()){
                         CatalogUtil.view(doc);
                     }
                 }
                 else if(tempLine.matches("report [a-zA-Z0-9in]+.[a-zA-Z]+")){
                     String sTemp5 = tempLine.substring(7);
                     Main mainApplication = new Main();
                     Catalog catalog = mainApplication.testLoadView(sTemp5);
                     ReportCommand reportCommand = new ReportCommand(catalog);
                     reportCommand.generateReport(catalog,cfg);
                 }
                 else if(!tempLine.contains("exit")){
                     System.out.println("Invalid command!");
                 }
            }

    }
}
