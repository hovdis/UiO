lisamjov & runehovd


1) Hvordan synes du innleveringen var? Hva var enkelt og hva var
vanskelig?
  
- Selve sorteringsalgoritmene var ganske greit, men det som var vanskelig var å finne ut hvordan vi skulle
    
oppretter trådene, få tilbake ferdige arrayer, og starte igjen.

2) Hvor lang tid (ca) brukte du på innleveringen?
    
-8-10 timer, men brukte mye tid ellers til å tenke over hvordan vi skulle gjøre det som er nevnt over

3) Samarbeidet du med noen under innleveringen? Hvis ja, skriv brukernavn
på den/de du samarbeidet med.
    
- lisamjov & runehovd

4) Var det noen oppgaver du ikke fikk til? Hvis ja:
    
-nei


    

1. Parallellitet: Hvilke operasjoner kan gå i parallell og hvilke kan ikke
    
 gå i parallell i programmet ditt (Amdahls lov)?
      
- Alt utenom den siste sorteringen og lese fra/skrive til fil går i parallell.
   
      
2. Kjøretiden programmet ditt bruker på sorteringen i forhold til antOrd[]
og antTraader, f.eks. ca. hvor mye øker sorterings-kjøretiden (eller
antall operasjoner som utføres) når antOrd[] dobler seg. Stor O-notasjon
er ikke pensum og trenger derfor ikke tas med (for de som er kjent
med det); et lite anslag ved dobling av dataene (og dobling av antall
prosessorer) er nok
- Dobling av antall ord vil doble kjøretiden av den andre delen av sorteringen (merge sorteringen),
      
men mer enn doble av den første delen av programmet (bubblesorteringen).
