(define (fac1 n)
  (if (= n 1)
      1
      (* n (fac1 (- n 1)))))



(define (fac2 k)
  (define (iter prod count)
    (if (> count k)
        prod
        (iter (* count prod) (+ count 1))))
  (iter 1 1))

(fac1 7)
(fac2 7)

((lambda (proc n)
   (proc proc n))
 (lambda (fac n)
   (if (= n 1)
       1
       (* n (fac fac (- n 1)))))
 5) ; ; Her skriver du inn hvilken fakultetverdi du vil ha.