(define some-tree
  '((1 (2 3) 3 4)))

(define (sum-tree tree)
  (cond ((null? tree) 0)
        ((number? tree) tree)
        (else
         (+ (sum-tree (car tree))
            (sum-tree (cdr tree))))))

(define (sum list)
  (if (null? list)
      0
      (+ (car list)
         (sum (cdr list)))))

