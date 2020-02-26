; ; Assignment 1a
; ; Exercise 1 
(* (+ 2 2) 5)
; ; Output: 20
; ; Reason: 5 * (2 + 2) = 5 * 4 = 20
(* (+ 2 2) (5))
; ; Error
; ; Reason: (5) is not a procedure
(* (2 + 2) 5)
; ; Error
; ; Reason: (2 + 2) should be (+ 2 2). The first '2' is not a procedure
(define bar (/ 4 2))
bar
; ; Output: 2
; ; Reason: bar is defined as 4/2 = 2
(- bar 2)
; ; Output: 0
; ; Reason: bar - 2 = 2 - 2 = 0
(/ (* bar 3 4 1) bar)
; ; Output: 12
; ; Reason: (bar * 3 * 4 * 1) / bar = (2 * 3 * 4 * 1) / 2 = 3 * 4 = 12

