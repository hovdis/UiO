(define minus
  (lambda (total)
    (lambda (minuss)
      (set! total (- total minuss)) total )))

(define m1 (minus 50))
(define m2 (minus 10))
m1
m2
(m1 10)
(m2 10)
(m1 10)
(m2 10)
(m2 -100)

(define balance 100)

(define withdraw
  (lambda (amount)
    (if (>= balance amount)
        (begin
          (set! balance (- balance amount))
          balance)
        "insufficient funds")))
(withdraw 25) ; ; -> 75
(withdraw 25) ; ; -> 50
(withdraw 60) ; ; -> “insufficient funds”
(withdraw 15) ; ; -> 35