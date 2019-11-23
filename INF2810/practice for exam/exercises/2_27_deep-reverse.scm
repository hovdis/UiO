(define (reverse2 lst)
  (if (null? lst)
      lst
      (append (reverse2 (cdr lst)) (list (car lst)))))

(reverse (list 1 4 9 16 25))
; ; (25 16 9 4 1)

(define (deep-reverse lst)
  (define (reverse2 lst res)
    (cond ((null? lst) res)
          ((list? (car lst))
           (reverse2 (cdr lst) (cons (deep-reverse (car lst)) res)))
          (else (reverse2 (cdr lst) (cons (car lst) res)))))
  (reverse2 lst '()))

(define x (list (list 1 2) (list 3 4)))
x
; ; ((1 2) (3 4))

(reverse2 x)
; ; ((3 4) (1 2))

(deep-reverse x)
; ; ((4 3) (2 1))
