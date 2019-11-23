import java.util.*;

class Regneklynge {
  int antallNoder;
  Regneklynge(int antallNoder){
    this.antallNoder = antallNoder;
  }

  ArrayList<Rack> rackList = new ArrayList<Rack>();



  boolean settInnNode(Node node){
    for(Rack i : rackList){
      if(i.ledigPlass()){
        i.leggTilNode(node);
        return true;
      }
    }
    Rack tmp = new Rack(antallNoder);
    tmp.leggTilNode(node);
    rackList.add(tmp);
    return true;
  }
  double flops(){
      double utregnetFlops = 0.0;

      for(Rack it : rackList){
        for(Node noder : it.hentNoder()){
          if (noder != null){
            for(Prosessor pros : noder.getPros()){
              if(pros != null){
                  utregnetFlops += (double)(pros.getKjerner() * pros.getHz() * 8);
              }
            }
          }
        }
      }
      System.out.println("Antall flops: " + utregnetFlops);
      return utregnetFlops;
  }
  int noderMedNokMinne(int paakrevdMinne){
    int tmp = 0;
    for (Rack i : rackList){
      tmp+= i.antallMedNokMinne(paakrevdMinne);
    }
    System.out.println("Noder med minst "+ paakrevdMinne + " GB: " + tmp);
    return tmp;
  }
  void antallRack(){
    System.out.println("Antall rack: " + rackList.size());
  }
}
