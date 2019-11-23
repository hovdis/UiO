/*Detti programmet regner ut gjennomsnittstemperaturen i loepet av et aar.
temperaturene staar i txt-fila temperatur.txt.*/
import java.util.Scanner;
import java.io.*;
class Temperatur
{
  //skar ha med throws exception fordi je skar lese fra en fil
  public static void main(String[]args)throws Exception
  {
    File minFil = new File("temperatur.txt"); // Hvilken fil je skar lese fra/skar gjoere no med
    Scanner minScanner = new Scanner(minFil); // Leser inn aa som staar i teksten.
    String fila; //Brukes for aa sette inn teksten.
    int[] temperaturer = new int[12]; //oppretter array for aa lagre alle temperaturene.
    int i=0; //teller.
    while(minScanner.hasNextLine()) // Mens fila har en linje til, kjoerer while-loekka.
    {
      fila = minScanner.nextLine(); // variebelen filla er en plassholder
      int tall = Integer.parseInt(fila);
      temperaturer[i] = tall; //holder for tallet i arrayen
      System.out.println(temperaturer[i]); //printer ut tempen.
      i++; //legger til en i telleren.
    }
    //det som gjoeres i gjennomsnitt-metoden blir lagra i inten gjennosnittet.
    int gjennomsnittet = gjennomsnitt(temperaturer, i);
    System.out.println("Gjennomsnittet er: "+gjennomsnittet); //skriver ut gjennomsnittet
  }

  static int gjennomsnitt(int []temperaturer, int i) //metoden for aa regne ut gjennomsnittet
  {
    i = 0; //lager en teller for for-lokka.
    int totalTemp = 0; //Maa ha totalen for aa regne ut gjennomsnittet
    int gjennomsnittet; //variablen skar bli gjennomsnittstemperaturen.
    for(i=0;i<temperaturer.length;i++) //saa lang temperaturen-arrayen er. (legger til 1 i teller til slutt)
    {
      totalTemp = totalTemp + temperaturer[i]; //legg til tallet i inten titalTemp
    }
    gjennomsnittet = totalTemp/temperaturer.length; //regner ut gjennomsnittet
    return gjennomsnittet; //sender tilbake gjennomsnittet
  }
}
