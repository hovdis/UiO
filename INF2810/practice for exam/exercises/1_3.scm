(define (sum a b c)
  (if (and (> a b) (> c b))
      (square a c)
      (if (and (> b a) (> c a))
          (square b c)
          (if (and (> a c) (> b c))
              (square a b)))))

(define (square x y)
  (+ (* x x) (* y y)))


(define (sum2 a b c)
  (cond ((and (> a b) (> c b)) (square a c))
        ((and (> b a) (> c a)) (square b c))
        (else (square a b))))
