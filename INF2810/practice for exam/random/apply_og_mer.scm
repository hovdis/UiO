#|

Vi skal se på apply senere

Her kan det være noen nyttige ting til oblig 2b

|#




(load "prekode2b.scm")
;define fag (make-table))
;fag
;(insert! 'INF2810 "funksjonell programmering" fag)
;; fag
; ; (lookup 'inf2810 fag)


(define arg-table (make-table))
(define mem-square
  (let ((arg-table (make-table)))
    (lambda (x)
      (or (lookup x arg-table)
          (let ((result (* x x)))
            (insert! x result arg-table)
            (display "calculating...")
            (display x)
            (newline)
            result)))))

(mem-square 2)



; apply
; Det siste elementet må være en liste. Den pakker ut lista og anvender prosedyren på alt.
(apply + '(1 2 3 4 5))
(apply + 1 2 3 '(4 5))

(define (do-f f . args)
  (apply f args))
(do-f (lambda (x y) (+ x y)) 1 2)

