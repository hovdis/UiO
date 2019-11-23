# encoding: utf-8

import nltk
from nltk.corpus import brown
from nltk.corpus import conll2000

print("=== Oppgave 1a ===")
# Oppgave 1.1:
cfd = nltk.ConditionalFreqDist((tag, word.lower()) for (word, tag) in brown.tagged_words())


print("=== Oppgave 1.2a ===")
# 10 mest frekvente substantivet:
print(cfd['NN'].most_common(10))
'''
[('time', 1568), ('man', 1160), ('af', 991), ('way', 882), ('world', 686), ('life', 678), ('year', 641), ('day', 627), ('work', 578), ('state', 528)]
'''

print("=== Oppgave 1.2b ===")
# Antall ganger  substantivet linguist forekommer: 9
print(cfd['NN']['linguist'])

print("=== Oppgave 1.2c ===")
# Mest frekvente adjektivet:
print(cfd['JJ'].most_common(1))
'''
[('new', 1056)]
'''

print("=== Oppgave 1.3 ===")
# Jeg vet at jeg skulle gjøre om variabelen cfd, men jeg lager en ny variabel (cdf)
cdf = nltk.ConditionalProbDist(cfd, nltk.MLEProbDist)
# print(cdf['JJ'].max())
# print(cdf['JJ'].prob("new"))

print("=== Oppgave 1.4 ===")
liste = []

for sents in brown.tagged_sents():
    liste.append(None) # Dette er starten av setningen
    for w, t in sents:
        liste.append(t)
    liste.append(None) # Dette er slutten av setningen

liste = nltk.bigrams(liste)

print("=== Oppgave 1.5a ===")
cfd_tags = nltk.ConditionalFreqDist(liste)
# Hvilken tagg er det som oftest etterfølger et substantiv i Brown-korpuset?
print(cfd_tags['NN'].most_common(1))

print("=== Oppgave 1.5b ===")
# hvor ofte forekommer bigrammet ’DT NN’?
print(cfd_tags['DT']['NN'])


print("=== Oppgave 1.6 ===")
cpd = nltk.ConditionalProbDist(cfd_tags, nltk.MLEProbDist)

print(cpd['DT'].prob('NN'))
'''
Svar: 0.4875516355922742
Altså nesten 50% sannsynlighet
'''

print()
print()
print("=== Oppgave 2a ===")

#J&M kapittel 5.5.1, side 176-178
PPSgivenVBD = cpd['VBD'].prob('PP$')
NNgivenPPS = cpd['PP$'].prob('NN')
herGivenPPS = cfd['PP$']['her']
totalSent1 = PPSgivenVBD * NNgivenPPS * herGivenPPS
print("Setning a: ", totalSent1)
print("=== Oppgave 2b ===")
PPOgivenVBD = cpd['VBD'].prob('PPO')
VBgivenPPO = cpd['PPO'].prob('VB')
herGivenPPO = cfd['PPO']['her']
totalSent2 = PPOgivenVBD * VBgivenPPO * herGivenPPO
print("Setning b: ", totalSent2)

# Setning a:  46.08974275689886
# Setning b:  3.2970051835946013
# Som man ser her er den foerste setningen mye mer sannsynlig.


print()
print()
print("=== Oppgave 3 ===")
'''
Under er modellene jeg laget for NP chunkingen.
Den øverste og tredje er påbygninger av den som står i boken.

Den første aksepterer først en 'determiner' eller 'possessive pronoun', så enten null eller ett tall,
deretter null eller fler 'adjective or numeral', for så et substantiv.

modell nummer to tar inn en eller null 'determiner', for så et substantiv

modell nummer tre tar inn enten en(eller null) 'determiner'/et tall, så null eller fler 'adjective or numeral'
for så å ta et substantiv.

modell nummer fire tar inn alle tagger som starter på NN. Det vil si NN, NNP og NNS. (minst en)

modell nummer fem tar inn en eller flere pronomen. Både personlig og possesive.
'''
grammar = r"""
    NP: {<DT|PRP$><CD>?<JJ>*<NN>}
    {<DT>?<NN>}
    {<DT|CD>?<JJ>*<NN.>}
    {<NN.>+}
    {<PRP.>+}
"""
test_set = conll2000.chunked_sents("train.txt", chunk_types=["NP"])
cp = nltk.RegexpParser(grammar)
res_test = nltk.chunk.util.accuracy(cp, test_set)
                                                                                                                                                                          
print(cp.evaluate(test_set))
 
print("Treffsikkerheten til test-set er: [ {0:.0%}".format(res_test), "]")
'''
Resultat:
ChunkParse score:
        IOB Accuracy:  72.5%%
        Precision:     36.7%%
        RRecall:       43.7%%
        F-Measure:     39.9%%
Treffsikkerheten til test-set er: [ 72% ]
'''




