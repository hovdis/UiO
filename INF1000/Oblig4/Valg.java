/*Detti programmet teller opp stemmene fra en txt-fil og regner ut aakke som vinner.
Den regner ogsaa ut aa mange prosent som stemte paa dom gitte partiene.*/
import java.util.*;
import java.io.*;
class Valg
{
  //Leser inn fra en fil, saa maa ha med throws Exception
  public static void main(String[]args)throws Exception
  {
    String[] stemmer = new String[456]; //string-array med alle stemmene.
    File minFil = new File("stemmer.txt"); //Hvilken vil je skar lese inn/gjøre no med.
    Scanner denniScanner = new Scanner(minFil);//Leser in aa som staar i teksten
    String nesteLinje; //Brukes for aa sette inn teksten.
    //Oppretter variablene til tellerene for stemmene
    double antallAP = 0, double antallKRF = 0, double antallSP = 0, double antallH = 0;
    String vinneren = "Ingen"; //Variabelen for aakke som vinn.
    double vinnerenantall=0; //Variabelen for aa mange stemmer vinneren vant med.

    for(int i = 0; denniScanner.hasNextLine(); i++) //mens fila har fler linjer
    {
      nesteLinje = denniScanner.nextLine(); //Det som staar blir lagt i nestelinje
      stemmer[i] = nesteLinje; //det som staar blir lagt i stemmer[i]

      if(nesteLinje.equals ("Ap")) //om det staar AP
      {
        antallAP++; //legg til en i antallAP
      }
      if(nesteLinje.equals ("KrF")) //om det staar KrF
      {
        antallKRF++;//Legg til en i antallKRF
      }
      if(nesteLinje.equals ("Sp")) //om det staar SP
      {
        antallSP++; //legg til en i antallSP
      }
      if(nesteLinje.equals ("H")) //om det staar H
      {
        antallH++; //legg til en i antallH
      }
    }

    //Finner ut hvilket parti som vinner.
    if (antallAP>antallKRF) //om AP har fler stemmer enn KrF.
    {
      vinneren = "Ap"; //blir vinneren saa langt Ap
      vinnerenantall = antallAP; //antall Ap blir ogsaa vinnerantallet
    }
    if(antallAP<antallKRF) //skriver itte det samma alle gonga. samma som over.
    {
      vinneren = "KrF";
      vinnerenantall = antallKRF;
    }
    if (antallSP>vinnerenantall)
    {
      vinneren = "SP";
      vinnerenantall = antallSP;
    }
    if (antallH>vinnerenantall)
    {
      vinneren = "Høyre";
      vinnerenantall = antallH;
    }

      System.out.println(""); //Lager mellomrom

      System.out.println("Antall Ap: " + antallAP); //sier aa mange stemmer dom fikk.
      System.out.println("Antall KrF: " + antallKRF); //sier aa mange stemmer dom fikk.
      System.out.println("Antall Sp: " + antallSP); //sier aa mange stemmer dom fikk.
      System.out.println("Antall H: " + antallH); //sier aa mange stemmer dom fikk.

      System.out.println(""); //Lager mellomrom


      double prosentAp = antallAP/stemmer.length*100; //regner ut prosenten stemmer av totalen
      double prosentKrF = antallKRF/stemmer.length*100; //regner ut prosenten stemmer av totalen
      double prosentSp = antallSP/stemmer.length*100; //regner ut prosenten stemmer av totalen
      double prosentH = antallH/stemmer.length*100; //regner ut prosenten stemmer av totalen

      System.out.println("Prosent Ap: "+prosentAp+" %"); //Printer ut prosent stemmer dom fikk
      System.out.println("Prosent KrF: "+prosentKrF+" %"); //Printer ut prosent stemmer dom fikk
      System.out.println("Prosent Sp: "+prosentSp+" %"); //Printer ut prosent stemmer dom fikk
      System.out.println("Prosent H: "+prosentH+" %"); //Printer ut prosent stemmer dom fikk
      System.out.println(""); //Lage mellomrom
      //Printer ut aakke som vant og aa mange stemmer dom fikk
      System.out.println("Vinneren ble: " + vinneren + " med " +vinnerenantall + "Stemmer.");
  }
}
