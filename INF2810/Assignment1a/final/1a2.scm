; ; Assignment 1a
; ; Exercise 2a
(or (= 1 2)
    "piff!"
    "paff!"
    (zero? (1 - 1)))
; ; Output: "piff!"
; ; Reason: The "or" precivate evaluates to the first true(#t) value found.
; ; If no true(#t) value is found, it is evaluated to false(#f).
; ; Any value that is not explicitly false (#f) is evaluated as true (#t).
; ; Since "piff!" is evaluated as true(#t), this is the first true value,
; ; and "piff!" will be the output.
; ; The syntactic error in this call is at the last line. (1 - 1) is syntactically
; ; incorrect. It should be (- 1 1)

(and (= 1 2)
"piff!"
"paff!"
(zero? (1 - 1)))
; ; Output: #f
; ; Reason: and is true (#t) if all values are true.
; ; it will be evaluated as false (#f) if there is at least one false value.
; ; in this call the first value is false (= 1 2), therefore the output will be "#f".
; ; The syntactic error in this call is at the last line. (1 - 1) is syntactically
; ; incorrect. It should be (- 1 1)

(if (positive? 42)
"poff!"
(i-am-undefined))
; ; Output: "poff!"
; ; Reason:The "if" precivate evaluates the first expression. If it is evaluated as true("t)
; ; the output/expression that comes after it will be executed. If it is evaluated as false(#f)
; ; the second output/expression will be executed.
; ; In this case the output will be "poff!" because 42 is a positive number.
; ; The (i-am-undefined) is undefined, but it will bever be executed because 42 is a positive number

; ; Exercise 2b using if
(define (sign1 n)
  (if (> n 0)
      1
      (if (< n 0)
          -1
          0)))
; ; Testing the procedure
(sign1 10)
(sign1 -10)
(sign1 0)
; ; Exercise 2b using cond
(define (sign2 n)
  (cond ((> n 0) 1)
        ((< n 0) -1)
        (else 0)))
; ; Testing the procedure
(sign2 10)
(sign2 -10)
(sign2 0)
; ; Exercise 2c
(define (sign3 n)
  (or (and (> n 0) 1)
      (and (< n 0) -1)
      0))
; ; Testing the procedure
(sign3 10)
(sign3 -10)
(sign3 0)