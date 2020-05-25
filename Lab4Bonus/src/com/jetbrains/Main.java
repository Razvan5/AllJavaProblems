package com.jetbrains;

//import com.github.javafaker.Faker;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        //Faker fakeResident = new Faker(),fakeHospital = new Faker();

//        Resident[] r = IntStream.rangeClosed(0,3).mapToObj(i -> new Resident(fakeResident.name().firstName())).toArray(Resident[]::new);
//        Hospital[] h = IntStream.rangeClosed(0,2).mapToObj(i -> new Hospital("Hospital " + fakeHospital.company().name())).toArray(Hospital[]::new);
        Resident[] r = IntStream.rangeClosed(0,3).mapToObj(i -> new Resident("R"+i)).toArray(Resident[]::new);
        Hospital[] h = IntStream.rangeClosed(0,2).mapToObj(i -> new Hospital("H" + i,"1")).toArray(Hospital[]::new);

        h[0].setCapacity(1);
        h[1].setCapacity(2);
        h[2].setCapacity(2);

        List<Resident> residentList = new ArrayList<>(Arrays.asList(r));
        List<Hospital> hospitalList = new ArrayList<>(Arrays.asList(h));

        //Collections.addAll(hospitalList, h);
        //System.out.println(hospitalList);

        Collections.sort(residentList,Comparator.comparing(Resident::getResidentName));

        Map<Resident,List<Hospital>>resPrefMap = new LinkedHashMap<>();
        Map<Hospital,List<Resident>>mapHospital = new LinkedHashMap<>();

        resPrefMap.put(r[0], Arrays.asList(h[0],h[1],h[2]));
        resPrefMap.put(r[1], Arrays.asList(h[0],h[1],h[2]));
        resPrefMap.put(r[2], Arrays.asList(h[0],h[1]));
        resPrefMap.put(r[3], Arrays.asList(h[0],h[2]));

        List<Resident> listForMap1 = new ArrayList<>();
        listForMap1.add(r[3]);
        listForMap1.add(r[0]);
        listForMap1.add(r[1]);
        listForMap1.add(r[2]);
        mapHospital.put(h[0], listForMap1);
        List<Resident> listForMap2 = new ArrayList<>();
        listForMap2.add(r[0]);
        listForMap2.add(r[2]);
        listForMap2.add(r[1]);
        mapHospital.put(h[1],listForMap2);//spitale cu r0 ca principal
        List<Resident> listForMap3 = new ArrayList<>();
        listForMap3.add(r[0]);
        listForMap3.add(r[1]);
        listForMap3.add(r[3]);
        mapHospital.put(h[2], listForMap3);//spitale cu r0 ca principal


        /*
        for(int i=0;i<hospitalList.size();i++){
            System.out.println("Hospital"+i+":"+mapHospital.get(hospitalList.get(i)));
        }

         */

        //Problem p = new Problem(resPrefMap,mapHospital,residentList,hospitalList);
        //System.out.println(p.findMatch());

        GaleShapley galeShapley = new GaleShapley(hospitalList,residentList,resPrefMap,mapHospital);
        //System.out.println(residentList);
        System.out.println("Preferinte Residenti:"+resPrefMap);
        System.out.println("Preferinte Spitale:"+mapHospital);
        Map<Resident, Hospital> matches=new HashMap<Resident, Hospital>();
                matches=galeShapley.match(hospitalList,mapHospital,resPrefMap);
        System.out.println("Match:"+matches);
        System.out.println("Este match stable?: "+ galeShapley.checkMatches(hospitalList,residentList,matches,mapHospital,resPrefMap));
        matches.clear();
        matches.put(residentList.get(0),hospitalList.get(0));
        matches.put(residentList.get(1),hospitalList.get(1));
        matches.put(residentList.get(2),hospitalList.get(1));
        matches.put(residentList.get(3),hospitalList.get(2));
        System.out.println("Match2:"+matches);
        System.out.println("Este match stable considerand ca toti residentii si toate spitalele au aceasi valoare de preferinta?: "+ galeShapley.checkMatchesTrivial(hospitalList,residentList,matches,mapHospital,resPrefMap));


        Resident[] residentArray = IntStream.rangeClosed(0,1).mapToObj(i -> new Resident("R"+i)).toArray(Resident[]::new);
        Hospital[] hospitalArray = IntStream.rangeClosed(0,1).mapToObj(i -> new Hospital("H" + i,"1")).toArray(Hospital[]::new);

        Map<Resident,List<Hospital>>prefResident = new LinkedHashMap<>();
        Map<Hospital,List<Resident>>prefHospital = new LinkedHashMap<>();

        prefResident.put(residentArray[0], Arrays.asList(hospitalArray[0],hospitalArray[1]));
        prefResident.put(residentArray[1], Arrays.asList(hospitalArray[1],hospitalArray[0]));
        prefHospital.put(hospitalArray[0], Arrays.asList(residentArray[0],residentArray[1]));
        prefHospital.put(hospitalArray[1], Arrays.asList(residentArray[1],residentArray[0]));

        hospitalArray[0].setCapacity(1);
        hospitalArray[1].setCapacity(1);

        List<Resident> residentListSecond = new ArrayList<>(Arrays.asList(residentArray));
        List<Hospital> hospitalListSecond = new ArrayList<>(Arrays.asList(hospitalArray));

        System.out.println("Preferinte Spitale:"+prefHospital);
        System.out.println("Preferinte Residenti"+prefResident);
        matches=galeShapley.match(hospitalListSecond,prefHospital,prefResident);
        System.out.println("Matching1:"+matches);
        System.out.println("Este match stable?: "+ galeShapley.checkMatches(hospitalListSecond,residentListSecond,matches,prefHospital,prefResident));
        matches.clear();
        matches.put(residentArray[0],hospitalArray[1]);
        matches.put(residentArray[1],hospitalArray[0]);
        System.out.println("Matching2:"+matches);
        //System.out.println(hospitalArray[0].getOrder());
        //System.out.println(hospitalArray[1].getOrder());
        System.out.println("Este match stable?: "+ galeShapley.checkMatches(hospitalListSecond,residentListSecond,matches,prefHospital,prefResident));


    }
}
