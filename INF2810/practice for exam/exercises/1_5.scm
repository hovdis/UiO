(define (p) (p))

(define (test x y)
  (if (= x 0)
      0
      y))

(test 0 (p))

#|
Applicative order takes the whole parameter, and evaluates it before sending
it to the function, while normal order does not evaluate each of the parameters
before it needs to be used.
In this case the code would run if it used normal order evaluation.
This is because x would be defined as 0, and the if-statement would be true,
and the output would be 0.
Since DrRacket (and scheme) uses applicative order, the code does not run,
since (p) is not a valid value.

Applicative-order vil si at alle argumenter vil evalueres før de sendes til prosedyren.
Dermed vil kallet på (test 0 (p)) forårsake at evalueringen ikke vil terminere,
siden p vil kalle seg selv rekursivt.
Normal-order vil si at argumentene først evalueres når vi trenger de.
Vi behøver ikke å vite hva y er for å avgjøre om vi skal returnere 0 eller y,
så da vil heller aldri y (som representerer et kall på (p)) evalueres. Testen vil evalueres til 0.
|#