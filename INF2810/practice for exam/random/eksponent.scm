; ; rekursiv prosess
; ; B opphøyd i n
(define (expt1 b n)
  (if (= n 0)
      1
      (* b (expt1 (- n 1)))))

; ; iterativ prosess
(define (expt2 b n)
  (define (expt-iter prod count)
    (if (= 0 count)
        prod
        (expt-iter (* b prod) (- count 1))))
  (iter 1 n))


; ; Enda mer effektiv. Gjerne se på denne senere
(define (expt3 b n)
  (define (iter prod count)
    (if (= count 0)
        prod
        (iter (* b prod)
              (- count 1))))
  (iter 1 n))


; ; Enda mer effektiv!
(define (fast-expt b n)
  (cond ((= n 0) 1)
        ((even n) (square (fast-expt b (/ n 2))))
        (else (* b (fast-expt b (- n 1))))))

(define (even n)
  (= (remainder n 2) 0))

(define (square x)
  (* x x))

