# encoding: utf-8

import nltk
from math import pow
from math import log
from nltk import bigrams
from nltk.probability import ConditionalFreqDist, FreqDist, ConditionalProbDist, LaplaceProbDist

class LM:
    def __init__(self):
        self.bigrams = ConditionalFreqDist()
        self.unigrams = FreqDist()
        sentences = nltk.corpus.brown.sents(categories=nltk.corpus.brown.categories()[1:])

        for sent in sentences:
            # Vi utvider setningen med None foran, for å angi start av
            # setningen, og en None etter, for å markere setningsslutt.
            sent = [None] + sent + [None]
            for prev, word in bigrams(sent):
                self.bigrams[prev][word] += 1
                self.unigrams[word] += 1

        self.bigrams = ConditionalProbDist(self.bigrams, LaplaceProbDist)
        self.unigrams = LaplaceProbDist(self.unigrams)

    def p(self, w, prev):
        p = 0.5*self.unigrams.prob(w)
        if prev in self.bigrams:
            p += self.bigrams[prev].prob(w)
        return p

    def logprob(self, s):
        total = 0
        s = [None] + s + [None]
        for w in list(bigrams(s)):
            total += log((self.p(w[1], w[0])), 2)

        return total

    def perplexity(self, sents):
        sum = 0
        sum_words = 0

        for s in sents:
            sum_words += len(s) # ?
            sum += self.logprob(s)

        return pow(2, (-1/sum_words)*sum)


    def zipfity(self, lst):
        # lst er en liste med elementer
        # Skal telle hvor mange ganger de distinkte elementene forekommer

        # Jeg vet at denne ikke fungerer optimalt, men har desverre ikke tid til aa
        # gjoere resten av obligen. Jeg kommer til aa gjoere dette senere.
        all_elems = {}
        for elm in lst:
            if elm not in all_elems:
                all_elems[elm] = 1
            else:
                all_elems[elm] += 1

        sorted_all = sorted(all_elems.items(), key=operator.itemgetter(1)) # Legger alle inn i en liste som er sortert, da blir de ti storste forst
        ten_most_freq = {}
        storst = True # Denne trenger jeg for aa finne den teoretiske frekvensen.
        most_freq = 0
        upToTen = 0
        for tupple in sorted_all:
            if storst:
                most_freq = tupple[1]
                upToTen += 1
                storst = False
            elif upToTen < 10:
                ten_most_freq[tupple[0]] = tupple[1]
                upToTen += 1  # Dette trenger jeg for aa bare lenge inn 10 elementer.

        for elms in ten_most_freq:
            print("Element: ", elms)
            print("Freq: ", ten_most_freq[elms])
            teoretisk = most_freq/ten_most_freq[elms]
            print("Teoretiske frekvensen: ", teoretisk)
