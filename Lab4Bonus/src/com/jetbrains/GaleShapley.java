package com.jetbrains;

import java.util.*;

public class GaleShapley {

    public List<Hospital> Hospitals;
    public List<Resident> Residents;
    public Map<Resident,List<Hospital>> ResidentPreferences;
    public Map<Hospital,List<Resident>> HospitalsPreferences;

    public GaleShapley(List<Hospital> Hospitals, List<Resident> Residents, Map<Resident, List<Hospital>> ResidentPreferences, Map<Hospital, List<Resident>> HospitalsPreferences) {
        this.Hospitals = Hospitals;
        this.Residents = Residents;
        this.ResidentPreferences = ResidentPreferences;
        this.HospitalsPreferences = HospitalsPreferences;
    }

    public Map<Resident, Hospital> match(List<Hospital> hospitals, Map<Hospital, List<Resident>> hospitalPrefers, Map<Resident, List<Hospital>> residentPrefers){

        Map<Resident, Hospital> associatedTo = new TreeMap<Resident, Hospital>();
        List<Hospital> freeHospital = new LinkedList<Hospital>();
        freeHospital.addAll(hospitals);

        while(!freeHospital.isEmpty()){
            Hospital thisHospital;
            //aici tine cont ce capacitate are spitalul si doar daca e 1 primeste remove
            if(freeHospital.get(0).getCapacity()==1){
                 thisHospital = freeHospital.remove(0); 

            }
            else{
                int capacity = freeHospital.get(0).getCapacity()-1;
                thisHospital = freeHospital.get(0);
                freeHospital.get(0).setCapacity(capacity);
            }

            List<Resident> thisHospitalPrefers = hospitalPrefers.get(thisHospital);
            for(Resident resident:thisHospitalPrefers){
                if(associatedTo.get(resident) == null){
                    associatedTo.put(resident, thisHospital);
                    break;
                }else{
                    Hospital otherHospital = associatedTo.get(resident);
                    List<Hospital> thisResidentPrefers = residentPrefers.get(resident);
                    if(thisResidentPrefers.indexOf(thisHospital) <
                            thisResidentPrefers.indexOf(otherHospital)){
                        //this resident prefers this hospital to the hospital the resident is associated to
                        associatedTo.put(resident, thisHospital);
                        freeHospital.add(otherHospital);
                        break;
                    }//else no change...keep looking for this hospital
                }
            }
        }
        return associatedTo;
    }

        public boolean checkMatches(List<Hospital> hospitalList, List<Resident> residentList, Map<Resident, Hospital> matches, Map<Hospital, List<Resident>> hospitalPrefers, Map<Resident, List<Hospital>> residentPrefers) {
            if(!matches.keySet().containsAll(residentList)){
                return false;
            }

            if(!matches.values().containsAll(hospitalList)){
                return false;
            }

            Map<Hospital, Resident> invertedMatches = new HashMap<Hospital, Resident>();
            for(Map.Entry<Resident, Hospital> couple:matches.entrySet()){
                invertedMatches.put(couple.getValue(), couple.getKey());
            }

            for(Map.Entry<Resident, Hospital> couple:matches.entrySet()){
                List<Hospital> residentPrefersList = residentPrefers.get(couple.getKey());
                List<Hospital> residentLikesBetter = new LinkedList<>();
                residentLikesBetter.addAll(residentPrefersList.subList(0, residentPrefersList.indexOf(couple.getValue())));
                List<Resident> hospitalPrefersList = hospitalPrefers.get(couple.getValue());
                List<Resident> hospitalLikesBetter = new LinkedList<>();
                hospitalLikesBetter.addAll(hospitalPrefersList.subList(0, hospitalPrefersList.indexOf(couple.getKey())));

                for(Hospital hospital : residentLikesBetter){
                    Resident hospitalsMatch = invertedMatches.get(hospital);
                    List<Resident> thisHospitalPrefers = hospitalPrefers.get(hospital);
                    if(thisHospitalPrefers.indexOf(hospitalsMatch) > thisHospitalPrefers.indexOf(couple.getKey())){
                        System.out.printf("%s likes %s better than %s and %s"
                                        + " likes %s better than their current resident\n",
                                couple.getKey(), hospital, couple.getValue(),
                                hospital, couple.getKey());
                        return false;
                    }
                }

                for(Resident resident : hospitalLikesBetter){
                    Hospital residentsMatch = matches.get(resident);
                    List<Hospital> thisResidentPrefers = residentPrefers.get(resident);
                    if(thisResidentPrefers.indexOf(residentsMatch) > thisResidentPrefers.indexOf(couple.getValue())){
                        System.out.printf("%s likes %s better than %s and %s"
                                        + " likes %s better than their current hospital\n",
                                couple.getValue(), resident, couple.getKey(),
                                resident, couple.getValue());
                        return false;
                    }
                }
            }
            return true;
        }

    public boolean checkMatchesTrivial(List<Hospital> hospitalList, List<Resident> residentList, Map<Resident, Hospital> matches, Map<Hospital, List<Resident>> hospitalPrefers, Map<Resident, List<Hospital>> residentPrefers) {
        if(!matches.keySet().containsAll(residentList)){
            return false;
        }

        if(!matches.values().containsAll(hospitalList)){
            return false;
        }

        Map<Hospital, Resident> invertedMatches = new HashMap<Hospital, Resident>();
        for(Map.Entry<Resident, Hospital> couple:matches.entrySet()){
            invertedMatches.put(couple.getValue(), couple.getKey());
        }

        for(Map.Entry<Resident, Hospital> couple:matches.entrySet()){
            List<Hospital> residentPrefersList = residentPrefers.get(couple.getKey());
            List<Hospital> residentLikesBetter = new LinkedList<>();
            residentLikesBetter.addAll(residentPrefersList.subList(0, residentPrefersList.indexOf(couple.getValue())));
            List<Resident> hospitalPrefersList = hospitalPrefers.get(couple.getValue());
            List<Resident> hospitalLikesBetter = new LinkedList<>();
            hospitalLikesBetter.addAll(hospitalPrefersList.subList(0, hospitalPrefersList.indexOf(couple.getKey())));

            for(Hospital hospital : residentLikesBetter){
                Resident hospitalsMatch = invertedMatches.get(hospital);
                List<Resident> thisHospitalPrefers = hospitalPrefers.get(hospital);
                if(hospitalsMatch.getPreferenceLevel() > couple.getKey().getPreferenceLevel()){
                    System.out.printf("%s likes %s better than %s and %s"
                                    + " likes %s better than their current resident\n",
                            couple.getKey(), hospital, couple.getValue(),
                            hospital, couple.getKey());
                    return false;
                }
            }

            for(Resident resident : hospitalLikesBetter){
                Hospital residentsMatch = matches.get(resident);
                List<Hospital> thisResidentPrefers = residentPrefers.get(resident);
                if(residentsMatch.getPreferenceLevel() > couple.getValue().getPreferenceLevel()){
                    System.out.printf("%s likes %s better than %s and %s"
                                    + " likes %s better than their current hospital\n",
                            couple.getValue(), resident, couple.getKey(),
                            resident, couple.getValue());
                    return false;
                }
            }
        }
        return true;
    }
}

