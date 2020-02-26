(load "huffman.scm")
; ; 1 a)
(display "1a") (newline)
(define (p-cons x y)
  (lambda (proc) (proc x y)))

(define (p-car lst)
  (lst (lambda (x y) x)))

(define (p-cdr lst)
  (lst (lambda (x y) y)))

(p-cons "foo" "bar")
(p-car (p-cons "foo" "bar"))
(p-cdr (p-cons "foo" "bar"))
(p-car (p-cdr (p-cons "zoo" (p-cons "foo" "bar"))))

; ; 1 b)
(display "1b")(newline)
(define foo 42)

#|
(let ((foo 5)
      (x foo))
  (if (= x foo)
      'same
      'different))
|#

((lambda (foo x)
   (if (= x foo)
       'same
       'different))
 5 foo)

#|
(let ((bar foo)
      (baz 'towel))
  (let ((bar (list bar baz))
        (foo baz))
    (list foo bar)))
|#

((lambda (bar baz)
   ((lambda (bar foo)
            (list foo bar))
    (list bar baz) baz))
 foo 'towel)

(display "1c")(newline)
; ; 1c)
(define (infix-eval lst)
  ((car (cdr lst)) (car lst) (caddr lst)))
(define foo (infix-eval (list 21 + 21)))
foo

; ; 1d)
(display "1d")(newline)

;(define bah '(84 / 2))
;(infix-eval bah)
