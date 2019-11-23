# encoding: utf-8
import re

#1a
liste = []
f = open("dev.txt")
ordene = f.read()

for line in f:
    liste.extend(line.split())
f.close()

with open('ordene.txt', 'w') as ordPerLinje:
    for line in liste:
        ordPerLinje.write(line + '\n')

#1b
'''
    Tegnsetting blir med i ordene de staar inntil istedetfor aa staa separat.
    Parenteser som foelger med ordene de staar rundt.

    En feil i diff-filen er at den ikke tar en ny linje for hvert tegn som ikke
    er en bokstav eller nummer som staar intill bokstavene/numrene uten mellomrom.
    eks:
    9c11,12
    < «sette
    ---
    > «
    > sette
    11c14,15
    < på»
    ---
    > på
    > »

    En annen feil er hvis det er bindestrek mellom to tall uten mellomrom. Disse skal splittes opp.

    1329c1503,1505
    < 70-80
    ---
    > 70
    > -
    > 80

    Den siste feilen jeg ser er at når det er fler tegn etterhverandre så skal disse bli splittet opp.
    De blir ikke det i filen jeg splittet opp.
    Begge disse feilene er egentlig feil som følges av det over.
    
    328c374,378
    < offer(!).
    ---
    > offer
    > (
    > !
    > )
    > .

'''


#1c
norskeOrd = r'\d+-[^\d]\w+|\d+\.?|\w+-?\w+|[A-Z]\.\S|\.{3}|(?=\S).' #regexen
lista = re.findall(norskeOrd, ordene)
ordFil = open("regEx.txt", "w")
for words in lista:
    ordFil.write(words + "\n")
ordFil.close()
