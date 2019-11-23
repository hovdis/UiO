import nltk
#oblig 3b runehovd

print("=== Oggave 1a ===")

grammar2 = nltk.CFG.fromstring('''
    S -> NP VP | NP VP PP
    VP -> V | V NP | V NP NP | V NP N
    PP -> P NP
    NP -> 'Per' | 'Kari' | 'Ola' | 'middag' | 'boka' | D N
    V -> 'gir' | 'sover' | 'spiser' | 'finner'
    D -> 'en'
    N -> 'bok'
    P -> 'til'
    ''')
rd_parser = nltk.RecursiveDescentParser(grammar2)
sents1 = "Per gir en bok til Kari".split()
sents2 = "Kari gir Per boka".split()
sents3 = 'Ola sover'.split()
sents4 = "Kari spiser".split()
sents5 = "Kari spiser middag".split()
sents6 = "Per finner boka".split()


for x in rd_parser.parse(sents1):
    print(x)
for x in rd_parser.parse(sents2):
    print(x)
for x in rd_parser.parse(sents3):
    print(x)
for x in rd_parser.parse(sents4):
    print(x)
for x in rd_parser.parse(sents5):
    print(x)
for x in rd_parser.parse(sents6):
    print(x)

print("=== 1b ===")
sents7 = 'Kari sover boka'.split()
sents8 = 'Ola finner'.split()

for x in rd_parser.parse(sents7):
    print(x)
for x in rd_parser.parse(sents8):
    print(x)

print("=== 1c ===")
grammar3 = nltk.CFG.fromstring('''
   S -> NP VP | NP VP PP
   VP -> V | V NP | V NP NP | V NP N | VBZ N 
   PP -> P NP
   NP -> 'Per' | 'Kari' | 'Ola' | 'middag' | D N
   V -> 'sover' | 'spiser' | 'gir'
   VBZ -> 'finner'
   D -> 'en'
   N -> 'bok' | 'boka'
   P -> 'til'
   ''')
parser = nltk.RecursiveDescentParser(grammar3)

for x in parser.parse(sents1):
    print(x)
for x in parser.parse(sents2): #(S (NP Kari) (VP (V gir) (NP Per) (NP boka)))
    print(x)                      #(S (NP Ola) (VP (V sover)))
for x in parser.parse(sents3): #(S (NP Kari) (VP (V spiser)))
    print(x)                      #(S (NP Kari) (VP (V spiser) (NP middag)))
for x in parser.parse(sents4): #(S (NP Per) (VP (V finner) (NP boka)))
    print(x)                      #=== 1b ===
for x in parser.parse(sents5): #(S (NP Kari) (VP (V sover) (NP boka)))
    print(x)                      #(S (NP Ola) (VP (V finner)))
for x in parser.parse(sents6):
    print(x)
for x in parser.parse(sents7):
    print(x)
for x in parser.parse(sents8):
    print(x)

# Oppgave 2
