(define-syntax ++
  (syntax-rules ()
    ((++ a)
     (set! a (+ 1 a)))))

(define-syntax --
  (syntax-rules ()
    ((-- a)
     (set! a (- a 1)))))

; ; For-løkker
; ; (for element in list <do>)

(define-syntax for
  (syntax-rules ()
    ((for element in list
       body)
     (for-each (lambda (element)
                 body)
               list))))


; ; Lag when:
; ; Tar et pred og en kropp

(define-syntax when
  (syntax-rules ()
    ((_ pred . body)
     (if pred (begin . body)))))
