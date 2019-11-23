import nltk
from lm import *
m = LM()

'''
Denne koden er jo langt fra ferdig, men jeg har rett og slett ikke tid til aa gjøre
hele obligen.
'''

brown_news = nltk.corpus.brown.sents(categories="news")
brown_adventure = nltk.corpus.brown.sents(categories="adventure")

print("News: ", m.perplexity(brown_news))
print("Adventure: ", m.perplexity(brown_adventure))

'''
Returverdien jeg får av dette er:
News: 72.69258522577947
Adventure:  117.41223031076323
'''
