; ; Omgivelse: en sekvens av rammer.
; ; Ramme: En tabell med (variabelnavn, verdi)-par
; ; Eks: (x 4) (y 10)
; ; Variabel har verdi relativ til omgivelse.
; ; Hvis man har en definert x i den globale omgivelsen, kan man ha en annen x inni skopet til noe annet.

; ; Regel 1: define _legger til_ bindinger
; ;          set! _endrer_ bindinger

; ; Regel 2: Lambda evalueres -> prosedyrepar opprettes.
; ;          med peker til sin omgivelse.

; ; Regel 3: Når vi anvender en prosedyre, utvider vi omgivelsen til prosedyren med en ny ramme
; ;          der paramentre bindes til argumentene.

; ; obgivelsesdiagram:
#|
1. define legger til bindinger
   set! _endrer_ bindinger
2. Hvis vi evaluerer et lambdauttrykk i en gitt omgivelse;
      Da oppretter vi et prosedyrepar, der den ene delen peker på prosedyredefinisjonen
      og den andre peker på den omgivelsen som den hører til.
3. Hvis vi anvender en prosedyre -> da utvider vi omgivelsen til prosedyren med en ny ramme,
   og i den rammen så har vi de parameterene bindes til argumentene.
|#

(define (square x)
  (* x x))

(define (sum-of-squares x y)
  (+ (square x) (square y)))

(sum-of-squares 6 10)
(define (make-withdraw balance)
  (lambda (amound)
    (if (>= balance amount)
        (begin
          (set! balance (-balance amount))
          balance)
        "Insufficient funds!")))





; ; Her skal vi jobbe med omgivelsesmodellen for evaluering. Tegn et omgivelsesdiagram som viser alle relevante
; ; rammer og bindinger etter at alle uttrykkene i følgende sekvens har blitt evaluert.


(define items '(a b))
(define (keeper x)
  (set! items (cons x items))
  items)
(define (make-keeper items)
  (lambda (x)
    (set! items (cons x items))
    items))
(define k1 (make-keeper '(c d)))
(k1 'e)
(keeper 'f)



