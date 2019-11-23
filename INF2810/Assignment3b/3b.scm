;; oblig 3b
;; forfattere: Magnus, Nicolas og Rune

(load "evaluator.scm")

;; opppgave 1a
(display "Oppgave 1a: \n")

(define global the-global-environment)
(set! global (setup-environment))

; (mc-eval '(define (foo cond ((= cond 2) 0)
;                  (else (else cond)))) global)

(mc-eval '(define cond 3) global)
(mc-eval '(define (else x) (/ x 2)) global)
(mc-eval '(define (square x) (* x x)) global)

(mc-eval '(foo 2 square) global) ;; --> 0

;; Evalueres til 0.
;; mc-eval vil evaluere cond som en special-form, da blir utrykket expandert til en rekke med IFs, men i dette
;; tilfelle blir det bare en IF. Den indre conden blir ikke evaluert som en vanlig cond, fordi inni expand-clauses,
;; har den et kall på make-if, som returnerer en vanlig IF, og denne IFen blir så sendt til mc-eval. 


(mc-eval '(foo 4 square) global) ;; --> 16
;; Evalueres til 16. Siden cond ikke er lik 2, så går foo videre til else-
;; elsen er en del av condens expansion, og alt under elsen vil da expanderes til et expression, derfor blir
;; den andre elsen evaluert til "square".

; (mc-eval '(cond ((= cond 2) 0)
;                (else (else 4))) global) ;; --> 2

;; Evalueres til 2.
;; Samme som over, cond expanderes til flere IFs, og første test failer, så den går til elsen.
;; Her utfører "else"-prosedyren med 4, og else er definert til å returnere
;; (/ x 2), som blir til (/ 4 2) som igjen returnerer 2.


;; oppgave 2a
(display "\nOppgave 2a\n")

;; la til 1+ og 1- som primitive prosedyrer i primitive-procedures i evaluator.scm
;; de ser sånn her ut:
;;    (list '1+
;;        (lambda (x) (+ x 1)))
;;    (list '1-
;;        (lambda (x) (- x 1)))
;; tester:
(display "Tester 1+ og 1-: \n")

(mc-eval '(1+ 2) global)
(mc-eval '(1- 10) global)

;; oppgave 2b
(display "\nOppgave 2b\n")

(define (install-primitive! val body)
  (let ((var (list 'primitive body)))
    (set! primitive-procedures (append primitive-procedures (list (list val body))))
  (define-variable! val var global)))

(install-primitive! 'square1 (lambda (x) (* x 4)))
(install-primitive! '10000+ (lambda (x) (+ x 10000)))

(mc-eval '(10000+ 1) global)
(mc-eval '(square1 2) global)

;; oppgave 3a
(display "\nOppgave 3a\n")

(display "Testing and:\n")
(mc-eval '(and #t #t #t) global)
(mc-eval '(and #t #t) global)
(mc-eval '(and #t #f) global)
(mc-eval '(and #f #t) global)

(display "Testing or:\n")
(mc-eval '(or #f #f #f) global)
(mc-eval '(or #t #t) global)
(mc-eval '(or #t #f) global)
(mc-eval '(or #f #t) global)

;; oppgave 3b
(display "\nOppgave 3b\n")
; Execute if
(mc-eval '(if (null? '()) then (+ 1 1)
            else (+ 2 2))
         global)
; Execute else
(mc-eval '(if (null? '(1)) then (+ 1 1)
            else (+ 2 2))
         global)
; Execute elsif
(mc-eval '(if (null? '(1)) then (+ 1 1)
            elsif (null? '()) then (+ 3 3)
            else (+ 2 2))
         global)
; Execute else
(mc-eval '(if (null? '(1)) then (+ 1 1)
            elsif (null? '(1)) then (+ 3 3)
            else (+ 2 2))
         global)

; Check multiple else-ifs
(mc-eval '(if (null? '(1)) then (+ 1 1)
            elsif (null? '(1)) then (+ 3 3)
            elsif (null? '()) then (+ 4 4)
            else (+ 2 2))
         global)

;; oppgave 3c
(display "\nOppgave 3c\n")

(mc-eval '(let ((test 1)
                (lol 2))
            (display (cons test lol))) global)

(display "\nOppgave 3d\n")

(mc-eval '(lett x = 2 and
                y = 3 in
            (display (cons x y))
            (+ x y)) global)

;; oppgave 3e
(display "\nOppgave 3e\n")

(define (while predicate body)
  (if predicate
    (begin
      body
      (while predicate body))
    #f))

(install-primitive! 'while while)

;(mc-eval '(define i 0) global)
;(mc-eval '(while (not (eq? i 4))
;                 (begin
;                   (display "\nnum: ")
;                   (display i)
;                   (set! i (+ i 1)))) global)

