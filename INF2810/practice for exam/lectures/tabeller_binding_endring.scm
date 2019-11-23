#|
(define (cons x y)
   (lambda (m)
     (cond ((eq? m 'car) x)
           ((eq? m 'car) y))))

(define (car p)
   (p 'car))

(define (cdr p)
   (p 'cdr))

(define foo (cons 1 (cons 2 '())))
foo -> #procedure



Memoisering:
Dette er et eksempel på dynamisk programmering
- Unngår å utføre gjentatte beregninger
- Lar prosedyrer "huske" sine tidligere returverdier for samme input-argumenter.
- Dersom en memorisert prosedyre kalles med samme argument flere ganger vil beregningen kun bli utført én gang,
  ved første kall, mens senere kall bare gjenbruker returverdien som er lagret fra tidligere.
- Kan gi store besparelser ved ressurskrevende beregninger.

Oblig2b
- skal implementere støtte for å lage memoiserte bersjoner av vilkårlige prosedyrer
- Lagrer resultater i en lokal _tabell_ (cache), indeksert på argumentene.
- For gjentatte kall med samme argumenter holder det å slå opp i tabellen.
 Skal i dag se på prekode som implementerer tabeller.
- Nok et eksempel på en muterbar listebasert




Testprosedyre (fibonacci):
|#

(define (fib n)
  (display "compute fib of ")
  (display n) (newline)
  (cond ((= n 0) 0)
        ((= n 1) 1)
        (else (+ (fib (- n 1))
                 (fib (- n 2))))))
(fib 3)

; ; (set! fib (mem 'memoize fob))     <--- Dette skal vi lage

; ; (set! fib (mem 'unmemoize fib))   <--- Dette skal vi lage

; ; Tabeller:
#|
En datastruktur for å assosiere _nøkler_ med _verdier_
Kan omplementeres med cons-celler i bunnen.
Kan bygges på en datatype som heter assosiasjonsliste, eller bare _alist_:
      - En liste av par: hver car er en nøkkel, "assosiert" med cdr-verdien.

(define table '((a . 1) (b . 2) (c . 3)))
(assoc 'b table) -> (b . 2)
(assoc 'e table) -> #f

(define (assoc key records)
   (cond ((null? records) #f)
         ((equal? key (caar records)).........


- Noen operasjoner kan forenkles ved å sette én ekstra cons-celle foran:
 - Gir oss et fast sted å modifisere ved innsetting av nye oppslag.
 - En slik _headed list_ kan også bruke første car som typemerkelapp.

|#

(define (make-table) (list '*table*))
(define (insert! ket value table)
  (let ((record (assoc key (cdr table))))
    (if record
        (set-cdr! record value)
        (cet-cdr! table (cons (cons key value) (cdr table))))))

(define (lookup key table)
  (let ((record (assoc key (cdr table))))
    (and tecord (cdr record))))
