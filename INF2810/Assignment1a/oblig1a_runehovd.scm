; ; Assignment 1a
; ; Exercise 1a
(display "Exercise 1")(newline)
(* (+ 4 2) 5)
; ; Output: 30
; ; Reason (4 + 2) * 5 ) = 6 * 5 = 30

; ; Exercise 1b
; ; (* (+ 4 2) (5))    I have commented it out for the program to run.
; ; Output: Error!
; ; Reason: (5) is not a procedure

; ; Exercise 1c
; ; (* (4 + 2) 5)      I have commented it out for the program to run.
; ; Output: Error!
; ; Reason: (4 + 2) should be (+ 4 2)

; ; Exercise 1d
(define bar (/ 42 2))
bar
; ; Output: 21
; ; Reason bar is defined as 42/2 = 21

; ; Exercise 1e
(- bar 11)
; ; Output: 10
; ; Reason: bar - 11 = 21 - 11 = 10

; ; Exercise 1f
(/ (* bar 3 4 1) bar)
; ; Output: 12
; ; Reason: (bar * 4 * 3 * 1)/bar = 4 * 3 * 1 = 4 * 3 = 12

(display "Exercise 2")(newline)
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

(display "Exercise 3")(newline)
; ; Exercise 3a
(define (addl n)
  (+ n 1))
(define (subt n)
  (- n 1))
; ; Testing the procedure
(addl 3)
(subt 2)
(addl (subt 0))
; ; I changed the name add1 and sub1 to addl and subt

; ; Exercise 3b
(define (plus x y)
  (if (zero? y) x
      (plus (addl x) (subt y))))
; ; Testing the procedure
(plus 10 1000)

; ; Exercise 3c
; ; In the task above (Exercise 3b) i defined a recursive procedure with an iterative process.
; ; It is iterative because the previous result is brought up to the next step. It keeps a
; ; running count. The information needed for each step is stored in the "x" and "y" variables.
; ; The "y"-variable, the number of steps remaining is stored, and the "x" will be the total.

(define (plus2 x y)
  (if (zero? y) x
      (addl (plus2 x (subt y)))))
; ; This is a recursive procedure with a recursive process.
; ; This is because each of the steps are waiting for the next step to be finished.
; ; Then the function is at "the bottom" (y = 0 (zero? y is true)), the function will start adding
; ; the value 1, y times to the x-value. 

; ; Exercise 3d
(define (power-close-to b n)
  (define (power-iter e)
    (if (> (expt b e) n)
        e
        (power-iter (+ 1 e))))
(power-iter 1))
; ; Since power-iter is defined inside power-close-to, the variables b and n can be used
; ; freely inside the scope of power-iter.(global variables)
; ; Therefore there is no need to bind 'b' and 'n' in power-iter.

; ; Exercise 3e
; ; This definition of 'fib-iter' can not be shortened because all
; ; operands/values/parameters have to be there, and none are static.