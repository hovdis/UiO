(display "Assignment 1b")(newline)
(display "Exercise 1")(newline)
; ; a)
(cons 47 11)
; ;    _______   
; ;__\|   | __|__\ 11
; ;  /|_|_|___|  /
; ;     |           
; ;    47

; ; b)
(cons 47 '())
; ;    _______  
; ;__\|   | _/|
; ;  /|_|_|/__|
; ;     |        
; ;    47

; ; c)
(list 47 11)
; ;    _______     _______
; ;__\|   | __|__\|   | _/|
; ;  /|_|_|___|  /|_|_|/__|
; ;     |           |
; ;    47          11

; ; d)
'(47 (11 12))
; ;    _______     _______
; ;__\|   | __|__\|   | _/|
; ;  /|_|_|___|  /|_|_|/__|
; ;     |          _|_____     _______
; ;    47         |   | __|__\|   | _/|
; ;               |_|_|___|  /|_|_|/__|
; ;                 |           | 
; ;                11          12

; ; e)
(define foo '(1 2 3))
(cons foo foo)
; ;    _______                           _______     _______     _______
; ;__\|   | __|________________________\|   | __|__\|   | __|__\|   | _/|
; ;  /|_|_|___|                        /|_|_|___|  /|_|_|___|  /|_|_|/__|
; ;    _|_____     _______     _______    |           |           |
; ;   |   | __|__\|   | __|__\|   | _/|   1           2           3
; ;   |_|_|___|  /|_|_|___|  /|_|_|/__|
; ;     |           |           |
; ;     1           2           3

; ; f)
(define flist '(0 42 #t bar))
(car (cdr flist))

; ; g)
(define glist '((0 42) (#t bar)))
(car (cdr (car glist)))

; ; h)
(define hlist '((0) (42 #t) (bar)))
(car (car (cdr hlist)))

; ; i)
(list (cons 0 (cons 42 '())) (cons #t (cons 'bar '())))

(display "Exercise 2")(newline)
; ; a)
(define (length2 items)
  (define (length-iter items number)
    (if (null? items)
        number
        (length-iter (cdr items) (+ 1 number))))
  (length-iter items 0))

; ; b)
(define (reduce-reverse proc init list)
  (if (null? list)
      init
      (reduce-reverse proc
                      (proc (car list) init)
                      (cdr list))))
; ; This is a tail-recursive procedure, witch creates a iterative process.
; ; It is tail-recursive because the recursive call is at the end.
; ; It combines all the elements with a given procedure from left to right.

; ; c)
(define (all? pred list)
  (if (null? list)
      #t
      (and (pred (car list))
           (all? pred (cdr list)))))

(all? (lambda (x) (< x 11)) '(5 6 7 8 9 1))

; ; d)
(define (nth index list)
  (define (nth-iter list cnt)
    (if (= index cnt)
        (car list)
        (nth-iter (cdr list) (+ 1 cnt))))
  (nth-iter list 0))

; ; This is another way to do it. I like this one much better, since it
; ; also checks if the index is out of range whilst being shorter than the first.
(define (nth2 index items)
  (cond 
    ((>= index (length items)) (display "Index out of range!")(newline))
    ((= index 0) (car items))
    (else (nth2 (- index 1) (cdr items)))))

; ; Testing both nth and nth2
(nth 4 '(1 2 3 4 5 6 7 8))
(nth2 4 '(1 2 3 4 5 6 7 8))

; ; e)
(define (where num list)
  (define (where-iter list cnt)
    (if (null? list)
        #f
        (if (= (car list) num)
            cnt
            (where-iter (cdr list) (+ 1 cnt)))))
  (where-iter list 0))

; ; f)
(define (map2 proc list1 list2)
  (cond ((null? list1) '())
        ((null? list2) '())
        (else (cons (proc (car list1) (car list2))
                    (map2 proc (cdr list1) (cdr list2))))))

; ; g)
(map2 (lambda (x y) (/ (+ x y) 2)) '(1 2 3 4) '(3 4 5))

; ; h)
(define (both? pred)
  (lambda (a b) (if (and (pred a) (pred b)) #t #f)))

; testing:
(map2 (both? even?) '(1 2 3) '(3 4 5))
((both? even?) 2 4)
((both? even?) 2 5) 

; ; i)
(define (self proc)
  (lambda (x) (proc x x)))
