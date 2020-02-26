; ; oblig 3b
; ; Authors: Rune Hovde, Nicolas Harlem Eide and Magnus Biong Nordin

(load "evaluator.scm")

(display "=== 1a ===\n")

; ; input: (foo 2 square)
; ; output: 0
; ; reason:

; ; input: (foo 4 square)
; ; output: 16
; ; reason:

; ; input: (cond ((= cond 2) 0)
; ;              (else (else 4)))
; ; output: 2
; ; reason:


(display "=== 2a ===\n")
(define primitive-procedures
  (list (list 'car car)
        (list 'cdr cdr)
        (list 'cons cons)
        (list 'null? null?)
        (list 'not not)
        (list '+ +)
        (list '- -)
        (list '* *)
        (list '/ /)
        (list '= =)
        (list 'eq? eq?)
        (list 'equal? equal?)
        (list 'display 
              (lambda (x) (display x) 'ok))
        (list 'newline 
              (lambda () (newline) 'ok))
;;      her kan vi legge til flere primitiver.
        (list '1+ (lambda (x) (+ 1 x)))
        (list '1- (lambda (x) (- x 1)))
        ))
; ; Hvis man ikke vil at dette skal være en primitiv prosedyre kan man også skrive:
; ; (define (1+ x)
; ;         (+ x 1))
; ; (define (1- x)
; ;         (- x 1))
; ; (mc-eval 1+ mc-global)
; ; (mc-eval 1- mc-global)

(display "=== 2b ===\n")
