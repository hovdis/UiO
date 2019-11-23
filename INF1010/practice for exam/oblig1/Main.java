class Main{
  public static void main(String[] args) {
    Node tmp1 = new Node(64, 2.6E9, 8, 8);
    Node tmp2 = new Node(1024, 2.3E9, 8, 8);
    Regneklynge abel = new Regneklynge(12);
    for(int i = 0; i<650;i++){
      abel.settInnNode(tmp1);
    }
    for(int k = 0; k<16;k++){
      abel.settInnNode(tmp2);
    }

    abel.flops();
    abel.noderMedNokMinne(32);
    abel.noderMedNokMinne(64);
    abel.noderMedNokMinne(128);
    abel.antallRack();
  }
}
