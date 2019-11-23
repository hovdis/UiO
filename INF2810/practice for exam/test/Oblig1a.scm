(* (+ 2 2) 5)
;; output: 20
;; reason: (2 + 2) * 5 = 20

(* (+ 2 2) (5))
;; error!
;; reason: (5) should be 5. Parentheses implicate a procedure which '5' is not

(* (2 + 2) 5)
;; error!
;; reason: (2 + 2) should be (+ 2 2). The first '2' is not a procedure

(define bar (/ 4 2))
;; bar defined to 2
;; reason: bar is defined as: 4 / 2 = 2

bar
;; output: 2
;; reason: bar = 2

(- bar 2)
;; output: 0
;; reason: bar - 2 = 2 - 2 = 0

(/ (* bar 3 4 1) bar)
;; output: 12
;; reason: (bar * 3 * 4 * 1) / bar = (2 * 3 * 4 * 1) / 2 = 12
