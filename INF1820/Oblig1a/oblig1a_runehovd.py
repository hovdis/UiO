# encoding: utf-8
#1a
f = open("dev.txt")
filinnhold = f.read()
f.close()
#1b
print("Antall ganger tekststrengen 'er' forekommer: ", filinnhold.count('er'))

splittet = filinnhold.split()

tall = 0
for x in splittet:
    if x.endswith('er'):
        tall += 1
print("Antall ord som slutter på 'er':", tall)

#1c
tmp = "tom"
listeMedEndelser = []
for i in splittet:
    tmp = i[-2:]
    listeMedEndelser.append(tmp)
#print listeMedEndelser

endelserString = ""
for i in splittet:
    tmp = i[-2:]
    endelserString = endelserString + " " + tmp # legger til alle stengene etter hverandre
# jeg printer ikke ut endelsene, men de er i variabelen endelserString


#2 a og b
liste = []
linjer = []
f = open("dev.txt")
# Deler opp teksten i linjer (variabel linjer) og ord (variabelen liste)
for line in f:
    linjer.append(line) # til 2a
    liste.extend(line.split()) # til 2b
f.close()
'''
I denne oppgaven valgte jeg å gjøre både a og b sammen.
Jeg lagde to lister, en med alle ordene i filen, og en med alle
linjene. Under teller jeg alle ordene og alle linjene.
'''
antallOrd = 0
for k in liste:
    antallOrd += 1
antallLinjer =0;
for l in linjer:
    antallLinjer +=1
print("Antall ord i teksten: ", antallOrd)
print("antall linjer: ", antallLinjer)


'''
Jeg kunne gjort det akkurat som oppgaven sa,
altså ved å først lese inn alle linjene, så dele dem opp
og bruke denne listen til å lage en liste med alle ordene.
Jeg synes bare at denne måten var bedre.

Koden ville da sett slik ut:

linjene = []
f = open("dev.txt")
for line in f:
    linjene.append(line)
f.close()

alleOrd = []
for i in linjene:
    alleOrd.extend(linjene.split())

antLinjer = 0
antOrd = 0
for l in linjer:
    antLinjer+=1
for o in alleOrd:
    antOrd+=1
print("antall linjer: ", antLinjer)
print("antall ord: ", antOrd)
'''
