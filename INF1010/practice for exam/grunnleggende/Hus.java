class Hus{
   Person eier = null;

   boolean setEier(Person eier){
       if(eier != null){
           System.out.println("Dette huset har allerede en eier");
           return false;
       } else{
           this.eier = eier;
           return true;
       }
   }
   public void skrivInfoOmEier(){
       System.out.println("Person som eier dette huset:");
       eier.skrivNavn();
   }
}
