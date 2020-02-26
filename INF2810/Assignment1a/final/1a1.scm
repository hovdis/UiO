; ; Assignment 1a
; ; Exercise 1a
(* (+ 4 2) 5)
; ; Output: 30
; ; Reason (4 + 2) * 5 ) = 6 * 5 = 30

; ; Exercise 1b
(* (+ 4 2) (5))
; ; Output: Error!
; ; Reason: (5) is not a procedure

; ; Exercise 1c
(* (4 + 2) 5)
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