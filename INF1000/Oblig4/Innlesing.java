/*Detti programmet spoer brukeren aassen ord som hen vil leite etter i txt-fila
winniethepooh.txt, og printer ut aa mange ganger det ordet staar i den fila.
Den er itte case sensitive, saa om du skriv inn whinnie-the-pooh, kommer det
5 gonger, itte 4, som det hadde vaert om den var case sensitive.*/

import java.util.Scanner; //importerer scanner
import java.io.*; //importerer alt fra io-pakka
class Innlesing //class som heter innlesing
{
  //throws exception fordi je skar lese inn fra txt-fil
  public static void main(String[]args)throws Exception
  {
    Scanner ait = new Scanner(System.in); //oppretter scanneren ait
    File minFail = new File("winniethepooh.txt"); // Hvilken fil je skar lese fra/skar gjoere no med
    Scanner denniScanneren = new Scanner(minFail); // Leser inn aa som staar i teksten.
    String filla = ""; //Brukes for aa sette inn teksten.
    int matchIndex = 0; //teller for ordet brukeren vil leite etter
    System.out.println("Hvilket ord skar vi leite etter?");
    String orlet = ait.nextLine(); // Lagrer ordet hen ser etter i ei String.
    while(denniScanneren.hasNextLine()) // Mens fila har en linje til, kjoerer while-loekka.
    {
      filla = denniScanneren.nextLine(); // variebelen filla er en plassholder
      // for aa som staar paa den setningen.
      if(filla.equalsIgnoreCase(orlet)) //Om filla har ordet som brukeren skreiv inn
      {
        matchIndex++; //legg til en i metchIndex (telleren)
      }
    }
      //Printer ut aa mange gonger det skrives ut.
    System.out.println(orlet + " skrives antall gang(er): " +matchIndex);
  }

}
