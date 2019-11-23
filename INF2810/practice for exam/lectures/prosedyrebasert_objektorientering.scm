(define (make-account balance)
  (define (deposit amount)
    (set! balance (+ balance amount))
    balance)
  (define (withdraw amount)
    (set! balance (- balance amount))
    balance)
  (define (dispatch message)
    (cond ((eq? message 'deposit) deposit)
          ((eq? message 'withdraw) withdraw)
          ((eq? message 'balance) balance)))
  dispatch)
; ; lager objekter
(define peter (make-account 0))
(define john (make-account 0))

; ; legger inn 50 kroner i balansen til peter
((peter 'deposit) 50)
; ; Sjekker hvor mye penger peter har.
(peter 'balance)

; ; Syntaktisk sukker
(define (deposit amount account)
  ((account 'deposit) amount))
(define (withdraw amount account)
  ((account 'withdraw) amount))
(define (balance account)
  (account 'balance))

; ; legger inn 50 kroner i balansen til peter med nye metoden som ikke bruker prosedyren
(deposit 50 peter)
; ; Sjekker hvor mye penger peter har med den nye metoden som ikke bruker prosedyren
(balance peter)

(deposit 1010 john)

(define rune (make-account (+ (withdraw 10 john) (withdraw 663 john))))
(balance rune)

