(define balance 100)

(define withdraw
  (lambda (amount)
    (cond ((>= balance amount)
             (set! balance (- balance amount))
             balance)
          (else "insufficient funds!"))))


(define make-withdraw
  (lambda (balance)
    (lambda (amount)
      (cond ((>= balance amount)
             (set! balance (- balance amount))
             balance)
            (else "Insufficient funds!")))))

(define sønn 100)
(define mamma 10000)
mamma
((make-withdraw mamma) 10)
mamma
(make-withdraw 3)