(define (reverse2 list)
  (if ((null? list)
       '())
      (cons (reverse2 (cdr list)) (car list))))

(reverse (list 1 4 9 16 25))
; ; (25 16 9 4 1)