(define (square-tree list)
  (cond ((null? list) '())
        ((number? list) (* list list))
        ((list? (car list))
         (cons (square-tree (car list)) (square-tree (cdr list))))
        (else (cons (square-tree (car list)) (square-tree (cdr list))))))

; ; Two ways to do it:

(define (square x)
  (* x x))

(define (square-tree2 tree)
  (cond ((null? tree) '())
        ((number? (car tree))
         (cons (square (car tree))
               (square-tree2 (cdr tree))))
        (else (cons (square-tree2 (car tree))
                    (square-tree2 (cdr tree))))))



(square-tree (list 1 (list 2 (list 3 4) 5) (list 6 7)))
; ; (1 (4 (9 16) 25) (36 49))
(square-tree2 (list 1 (list 2 (list 3 4) 5) (list 6 7)))