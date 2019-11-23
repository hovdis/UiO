
; ; b
(define (replace x y seq)
  (cond ((null? seq) '())
        ((eq? (car seq) x)
         (cons y (replace x y (cdr seq))))
        (else (cons (car seq) (replace x y (cdr seq))))))

(define foo '(a b c d a))
(replace 'a 'c foo)

; ; e
(define (stream-replace x y stream)
  (cond ((stream-null? stream) the-empty-stream)
        ((eq? (stream-car stream) x)
         (cons-stream (y (stream-replace x y (stream-cdr stream)))))
        (else
         (cons-stream (stream-car stream) (stream-replace x y (stream-cdr stream))))))