import java.util.*;

class Ordliste{
  HashMap<Character, Node> liste;
  MittSkip nene = new MittSkip();

  Ordliste(){
    liste = new HashMap<Character, Node>();
  }

  void spill(char start){
    int teller = 0;
    char forsteBokstav = start;
    while(liste.containsKey(forsteBokstav)){
      String ord = taUt(forsteBokstav);
      System.out.println(ord);
      forsteBokstav = nene.sisteBokstavI(ord);
    }
  }

  void leggTil(String ord){
    char c = ord.charAt(0);
    liste.put(c, new Node(ord));
  }

  String taUt(char forsteBokstav){
    String tmp = liste.get(forsteBokstav).denne;
    liste.remove(forsteBokstav);
    return tmp;
  }

  public Node forsteNode(char bokstav){
    return liste.get(bokstav);
  }

  private class Node{
    String denne;
    Node neste;

    Node(String denne){
      this.denne = denne;
    }

    String getDenne(){
      return denne;
    }

    Node getNeste(){
      return neste;
    }

    public void settNeste(Node neste){
      this.neste = neste;
    }
  }
}
