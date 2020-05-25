package com.jetbrains;

import java.util.*;

public class Problem {


    Map<Resident,List<Hospital>>residentPref = new LinkedHashMap<>();
    Map<Hospital,List<Resident>>hospitalPref = new LinkedHashMap<>();

    List<Resident> residentList = new ArrayList<>();
    List<Hospital> hospitalList = new ArrayList<>();

    Matching matchingFinal;

    Problem( Map<Resident,List<Hospital>>resPref,Map<Hospital,List<Resident>> hosPref,List<Resident> rList,List<Hospital> hList){
        this.residentPref=resPref;
        this.hospitalPref=hosPref;
        this.residentList=rList;
        this.hospitalList=hList;
    }

    public Matching findMatch(){

        List<Element> asocResidentHospital= new ArrayList<>();

        for(Resident resident : residentList){
            for(Hospital hospital : this.residentPref.get(resident)){
                if(hospital.capacity>0){
                    if(!hospitalPref.get(hospital).isEmpty() && resident.getResidentName().equals(hospitalPref.get(hospital).get(0).getResidentName())) {
                        System.out.println(resident + ":" + hospital);
                        Element oneAssociation = new Element(hospital,resident);
                        asocResidentHospital.add(oneAssociation);
                        hospital.capacity--;
                        for(Hospital hospitalFromList : hospitalList) {
                            List<Resident> temporaryList = hospitalPref.get(hospitalFromList);
                            //System.out.println(hospitalFromList.getHospitalName() + temporaryList);
                            if(!temporaryList.isEmpty()){
                                temporaryList.remove(resident);
                                hospitalPref.put(hospitalFromList,temporaryList);
                            }
                            //System.out.println(hospitalFromList.getHospitalName() + temporaryList);
                        }

                    }

                }
            }

        }

        Matching matchingFinal = new Matching(asocResidentHospital);

        return this.matchingFinal;
    }


}
