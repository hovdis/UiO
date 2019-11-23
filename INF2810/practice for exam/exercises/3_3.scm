(define (make-account balance pass)
  (let ((balance balance)
        (pass pass))
    (lambda (try-pass try-proc)
      (if (equal? pass try-pass)
          (if (equal? try-proc 'withdraw)
              (lambda (try-withdraw)
                (if (>= balance try-withdraw)
                    (begin
                      (set! balance (- balance try-withdraw))
                      balance)
                    "No balance"))
              (if (equal? try-proc 'deposit)
                  (lambda (deposit-amount)
                    (begin
                      (set! balance (+ balance deposit-amount))
                      balance))))
          "Incorrect password"))))

(define acc (make-account 9001 'cash))
((acc 'cash 'deposit) 0)
((acc 'cash 'withdraw) 8985)

             
((acc 'akmwl 'withdraw) 10)



; ; Bør lage i mange forskjellige metoder, ikke i en stor bæsj