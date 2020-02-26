; ; Assignment 1a
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
; ; freely inside the scope of power-iter.
; ; Therefore there is no need to bind 'b' and 'n' in power-iter.

; ; Exercise 3e
; ; This definition of 'fib-iter' can not be shortened because all
; ; operands/values/parameters have to be there.





; ; Hei!
