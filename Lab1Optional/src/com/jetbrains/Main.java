package com.jetbrains;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;


public class Main {

    static boolean suntAdiacente(String w1,String w2){
        for(int i=0;i<w1.length();++i){
            if(w2.indexOf(w1.charAt(i))>0){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        Scanner scanner = new Scanner(System.in);
        int n,k;
        //n=scanner.nextInt();//nr de cuvinte
        //k=scanner.nextInt();//lungimea cuvantului
        n=Integer.parseInt(args[0]);
        k=Integer.parseInt(args[1]);

        String alfabet ="";
        Vector<String> cuvinte = new Vector<String>();
        String cuv ="";
        boolean matriceDeAdiacenta[][]= new boolean[n][n];
        int nrVecini[] = new int[n];

        int q=2;
        while(q<args.length) {
            //String nextLine = scanner.nextLine();
            String nextLine = args[q];
            q++;
            if ( nextLine.equals(" ") || nextLine.equals("exit") ) {
                break;
            }
            if(nextLine.matches("[A-Z]")){
                alfabet += nextLine;
            }
        }

        for(int i=0;i<n;++i){
            for(int j=0;j<k;++j){
                Random r=new Random();
                int pozitie = r.nextInt(alfabet.length()-1);
                cuv+=alfabet.charAt(pozitie);
            }
            System.out.println(cuv);
            cuvinte.add(cuv);
            cuv=" ";
        }
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                if(suntAdiacente(cuvinte.elementAt(i),cuvinte.elementAt(j))){
                    matriceDeAdiacenta[i][j]=true;
                }
                else matriceDeAdiacenta[i][j]=false;
            }
        }

//        for(int i=0;i<n;++i) {
//            for (int j = 0; j <n; ++j) {
//                //System.out.printf(String.valueOf(matriceDeAdiacenta[i][j])+" ");
//            }
//            //System.out.println();
//        }
        
        int i=0,j=0,p=0;
        for(String w1 : cuvinte){
            for(String w2: cuvinte){
               // System.out.print(suntAdiacente(w1,w2)?"1 ":"0 ");
                if(suntAdiacente(w1,w2)==true){

                    nrVecini[i]+=1;
                }
            }
            i++;
            //System.out.println(" ");
        }

        int max=0,min=1000000;

        for(int l=0;l<n;l++){
            if(max<nrVecini[l]){
                max=nrVecini[l];
            }
            if(min>nrVecini[l]){
                min=nrVecini[l];
            }
        }
        Vector<String> maxStrings=new Vector<String>();
        Vector<String> minStrings=new Vector<String>();

        for(int l = 0 ; l<n;l++){
            if(nrVecini[l]==max){
                maxStrings.add(cuvinte.elementAt(l));
            }
            if(nrVecini[l]==min){
                minStrings.add(cuvinte.elementAt(l));
            }
        }

        System.out.println("Cuvinte adiacente maxim:"+max);
        for(int l = 0 ; l<maxStrings.size();l++){
            System.out.println(maxStrings.elementAt(l));
        }
        System.out.println("Cuvinte adiacente minim:"+min);
        for(int l = 0 ; l<minStrings.size();l++) {
            System.out.println(minStrings.elementAt(l));
        }

        long endTime = System.nanoTime();
        System.out.println("A durat "+ ((endTime - startTime)/60000000.0)/1000.0 + " min");

    }
}
