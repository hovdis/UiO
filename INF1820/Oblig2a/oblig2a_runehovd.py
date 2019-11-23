import nltk
brown = nltk.corpus.brown.tagged_words(categories="news")

word_occurance = {  }
# Hvilket ord er det mest frekvente?
for tupple in brown:
    if(tupple[0] not in word_occurance):
        word_occurance[tupple[0]] = 0
    word_occurance[tupple[0]] += 1
   # print(tupple)

# print(word_occurance)

word = "tmp"
frequency = 0

for x in word_occurance:
    if(word_occurance[x] > frequency):
        word = x
        frequency = word_occurance[x]
print("-----------1.1-----------")
print("Mest frekvente ordet:", word)
print("Antall ganger ordet forekommer: ", frequency)

word_class ={ }
# klassifiserer hvilke orklasser, og antall ganger de forekommer
for i in brown:
    if(i[1] not in word_class):
        word_class[i[1]] = 0
    word_class[i[1]] += 1


most_freq_wordclass = "tmp"
wordclass_freq = 0
for x in word_class:
    if(word_class[x] > wordclass_freq):
        most_freq_wordclass = x
        wordclass_freq = word_class[x]
print("-----------1.2-----------")
print("Mest frekvente ordklassen: ", most_freq_wordclass)
print("Antall ganger ordklassen forekommer: ", wordclass_freq)


least_freq_wordclass = "tmp"
least_freq = 2
for i in word_class:
    if(word_class[i] < least_freq):
        least_freq_wordclass = i
        least_freq = word_class[i]

print("-----------1.3-----------")
print("Minst frekvente ordklassen: ", least_freq_wordclass)
print("Antall ganger ordklassen forekommer: ", least_freq)
# Dette er bare en av ordklassene som forekommer en gang. Det er noen ordklasser som ikke forekommer
# i det hele tatt. Som for eksempel !!!!!!!!!!!!!!!
# I oppgaven står det spesifikt at vi skal finne DEN ordklassen som forekommer minst, men
# det er jo fler ordklasser som kun forekommer en eller null ganger, så jeg fant bare den første
# ordklassen som forekommer en gang


# Oppgave 2.1 og 2.2:
words_and_tags={}

for word in brown:
    the_word = word[0].lower()
    word_class = word[1]
    if the_word not in words_and_tags:
        words_and_tags[the_word] = set()
        words_and_tags[the_word].add(word_class)
    else:
        words_and_tags[the_word].add(word_class)

ambiguous_words = 0
amount_of_tags = 0
word_with_most_tags = []
for key in words_and_tags:
    if len(words_and_tags[key]) > 1:
        ambiguous_words += 1
    if len(words_and_tags[key]) > amount_of_tags:
        word_with_most_tags.clear()
        word_with_most_tags.append(key)
        amount_of_tags = len(words_and_tags[key])
    elif len(words_and_tags[key]) == amount_of_tags:
        word_with_most_tags.append(key)

print("-----------2.1 og 2.2-----------")
print("flertydige ord: ", ambiguous_words)
print("Ord med flest ordtagger: ", word_with_most_tags)
print("antall tagger: ", amount_of_tags)
# Oppgave 2.3:
def freqs (word):
    tags_and_occurances = {} # Jeg burde sikker laget en slik dictionary som jeg kunne bruke til flere oppgaver, naa har jeg laget en for hver oppgave
    woord = word.lower() # Jeg trenger sikkert ikke aa ha med denne, men det er i tilfellet noen proever med store bokstaver
    for tupple in brown: # Gaar gjennom hele brown-korpuset og legger inn taggene til ordet du soeaker etter i en dictionary med antall forekomster som value.
        tmp_word = tupple[0].lower()
        tmp_tag = tupple[1]
        if tmp_word == woord:
            if tupple[1] not in tags_and_occurances:
                tags_and_occurances[tupple[1]] = 1
            else:
                tags_and_occurances[tupple[1]] += 1
    print("Ordet ", word, " har disse taggene:")
    print(tags_and_occurances)

print("-----------2.3-----------")
for i in range (0, len(word_with_most_tags)):
    freqs(word_with_most_tags[i])
# Oppgave 2.4:
# Det jeg observerer fra aa se paa hvilke tagger de mest flertydige ordene har er at det er som regel bare
# to tagger ordet blir tolket som og et faatall tolkninger til de andre taggene.
# Hvis vi ser paa for eksempel ordet 'to', som er en av de mest flertydige ordene ser vi at taggen 'TO' og 'IN' forekommer mye oftere enn for eksempel taggene 'IN-HL', 'TO-HL' etc.
