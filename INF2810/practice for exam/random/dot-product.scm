#|
Gitt to vektorer:
a = <2, 1, 3>
b = <1, 3, 0>
Beregn prikkprodukt
|#

(define (dot-product x y)
  (if (null? x)
      0
      (+ (* (car x) (car y))
         (dot-product (cdr x) (cdr y)))))


(define (dot-product2 x y)
  (reduce + 0 (map * x y)))