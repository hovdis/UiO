import math
from random import random


# Oppgave 1 a)

xforrige1 = 1 #x_1
xforrige2 = 1 #x_0

for i in range(2, 100):
    x = 2*xforrige1 + xforrige2
    print("x_%d = %d" % (i, x))
    xforrige2 = xforrige1
    xforrige1 = x


# Oppgave 1 b)

yforrige1 = 1
yforrige2 = (1 - math.sqrt(2))

for i in range(2, 100):
    y = 2 * yforrige1 + yforrige2
    print("x_%d = %d" % (i, y))
    yforrige2 = yforrige1
    yforrige1 = y


# Oppgave 2 a.1)

a = 1
n = 9998
i = 4

for j in range(1, n-i):

    a *= ((float(i+j))/j)
print(float(a))


# Oppgave 2 a.2)
a = 1
n = 100000
i = 70

for j in range(1, n-i):

    a *= ((float(i+j))/j)
print(float(a))


# Oppgave 2 a.3)
a = 1
n = 1000
i = 500

for j in range(1, n-i):

    a *= ((float(i+j))/j)
print(float(a))


# Oppgave 3a)

antfeil = 0; N = 10000
x0 = y0 = z0 = 0.0
feil1 = feil2 = 0.0

for i in range(N):
    x = random(); y = random();
    res1 = (x + y)*(x - y)
    res2 = x**2 - y**2
    if res1 != res2:
        antfeil += 1
        x0 = x; y0 = y
        feil1 = res1
        feil2 = res2

print(100. * antfeil/N)
print(x0, y0, feil1 - feil2)

# Oppgave 3b)

antfeil = 0; N = 10000
x0 = y0 = z0 = 0.0
feil1 = feil2 = 0.0

for i in range(N):
    x = random(); y = random();
    res1 = (x + y)*(x - y)
    res2 = x**2 - y**2
    if res1 != res2:
        antfeil += 1
        x0 = x; y0 = y
        feil1 = res1
        feil2 = res2

print(100. * antfeil/N)
print(x0, y0, feil1 - feil2)
