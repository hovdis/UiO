(load "prekode2b.scm")

; ; Grunnen til at vi skriver (display ...) over alt er fordi en på gruppa bruker vim, og
; ; interpereteren til vim skriver ingenting ut med mindre vi skriver display.

(display "=== 1a ===\n")
(define (make-counter)
  (let ((count 0))
    (lambda ()
      (set! count (+ 1 count))
      count)))

; ; Tester metoden:
(define count 42)
(define c1 (make-counter))
(define c2 (make-counter))
(c1)
(c1)
(c1)
count
(c2)

(display "=== 1b ===\n")
; ; Dette er nok lettest å tegne for hånd, eller på pc, og legge det ved i innleveringen.

(display "=== 2a ===\n")
; ; skal lage stacker som kan pushes eller poppes
; ; LIFO-stakk

; ; tre beskjeder vi kan gi:
; ; 'push! - vilkårlig antall elementer/argumenter
; ; 'pop! - ingen argumenter
; ; 'stack - returner lista

(define (make-stack items)
  (define (choose-action msg . args)
    (cond ((equal? 'stack msg) (stack))
          ((equal? 'pop! msg) (pop!))
          ((equal? 'push! msg) (push! args))
          (else (display "Invalid message!"))))
  
  (define (stack) items)
  
  (define (pop!)
    (if (not (null? items))
        (set! items (cdr items))))

  (define (push! args)
    (if (not (null? args))
        (begin
          (set! items (cons (car args) items))
          (push! (cdr args)))))
  
  choose-action)

; ; Tester metoden:
(define s1 (make-stack (list 'foo 'bar)))
(define s2 (make-stack '()))
(s1 'pop!)
(s1 'stack) ; ; (bar)
(s2 'pop!)  ; ; skal ikke få feilmelding
(s2 'push! 1 2 3 4)
(s2 'stack) ; ; (4 3 2 1)
(s1 'push! 'bah)
(s1 'push! 'zap 'zip 'baz)
(s1 'stack) ; ; (baz zip zap bah bar)



(display "=== 2b ===\n")
(define (pop! stack)
  (stack 'pop!))

(define (stack stack)
  (stack 'stack))

(define (push! stack . args)
  (apply stack 'push! args))

(pop! s1)
(stack s1) ; ; (zip zap bah bar)
(push! s1 'foo 'faa)
(stack s1) ; ; ((foo faa) zip zap bah bar)

(display "=== 3a ===\n")
; ; Etter set-cdr! vil bar være en syklisk liste hvor elementet etter 'd peker på 'b.
; ; Dette vil da si at vi får listen '('a 'b 'c 'd 'b 'c 'd ...) hvor '('b 'c 'd) er syklen.
; ; Grunnen til at vi får verdiene vi får ved kallene på list-ref er fordi tallet man sender med
; ; er antall ganger cdr kjøres rekursivt før man kaller på car.


(display "=== 3b ===\n")
; ; Grunnen til at bah evaluerer til verdien den gjør etter det siste kallet på set-car! er fordi
; ; (caar bah) peker på samme sted som (cdar bah). Hvis man endrer på den ene, endres den andre også.
; ; (Teknisk sett så endres bare en, siden de peker på det samme, men man kan si det på den måten)


(display "=== 3c ===\n")

; ; For at en liste skal være en liste må den ha et endepunkt. Det må gå an å gå gjennom listen og
; ; ende et sted. Bar er egentlig en sti, ikke en liste.
; ; Data skal kunne bli lagret og prosesseres i en bestem rekkefølge for at en liste skal være en liste.



(display "=== 4a ===\n")


(define org-table (make-table))

(define (mem msg proc)
  (cond ((eq? msg 'memoize)
         (let ((key (memo proc))
               (value proc))
           (insert! key value org-table)
           value))
        ((eq? msg 'unmemoize)
         (or (lookup proc org-table)
             proc))
        (else (display "Error!"))))

(define (memo proc)
  (let ((cache (make-table)))
    (lambda args
      (let ((if-found-value (lookup args cache))) ; ; Denne sjekker om args finnes i cache
        ; ; Hvis args finnes i cache, så vil valuen bli returnert, dette er da #t. Hvis det ikke
        ; ; finnes vil if-found-value være satt til #f
        (or (if-found-values)
            (let ((result (apply proc args)))
              (insert! args result cache)
              result))))))


(newline)
(display "=== 4b ===\n")

(set! fib (mem 'memoize fib))
(fib 3)
(fib 3)

(fib 2)

(fib 4)
(set! fib (mem 'unmemoize fib))
"her"
(fib 3)
(fib 3)




(set! test-proc (mem 'memoize test-proc))
(test-proc)
(test-proc)
(test-proc 40 41 42 43 44)
(test-proc 40 41 42 43 44)
(test-proc 42 43 44)
(set! test-proc (mem 'unmemoize test-proc))
(test-proc)
(test-proc)
(test-proc 40 41 42 43 44)
(test-proc 40 41 42 43 44)
(test-proc 42 43 44)

(display "=== 4c ===")
; ; Versjonen hvor vi setter fib til å være "mem 'memoize fib" vil gjøre at ALLE kall på fib,
; ; inkuldert de rekursive kallene inne i selve funksjonen, vil cache resultatet.
; ; I versjonen hvor vi bare definerer "mem-fib" derimot, vil bare cache sluttresultatet til
; ; prosedyren.
; ; Dette er fordi man overskriver binden til fib.


