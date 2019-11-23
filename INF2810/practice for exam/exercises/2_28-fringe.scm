; ; can be named "flatten"

(define x (list (list 1 2) (list 3 4)))

(define (fringe list)
  (cond ((null? list) '())
        ((list? (car list))
         (append (fringe (car list)) (fringe (cdr list))))
        (else (cons (car list) (fringe (cdr list))))))

(fringe x)

(fringe (list x x))
