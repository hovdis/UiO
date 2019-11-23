(define (my-reduce proc init lst)
  (if (null? lst)
      init
      (proc (car lst)
            (my-reduce proc init (cdr lst)))))

(define (my-lnt list)
  (my-reduce (lambda (x y) (+ 1 y)) 0 list))

(define (change-to-one lst)
  1)

(define (my-length list)
  (my-reduce + 0 (map change-to-one list)))


(define (filter pred lst)
  (if (null? lst)
      '()
      (if (pred (car lst))
          (cons (car lst) (filter pred (cdr lst)))
          (filter pred (cdr lst)))))


