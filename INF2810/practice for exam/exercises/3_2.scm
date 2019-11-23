(define (make-monitored f)
  (let ((times_called 0))
    (lambda (x)
      (if (eq? x 'how-many-calls?)
          times_called
          (begin
            (set! times_called (+ 1 times_called))
            (f x))))))
    
(define s (make-monitored sqrt))
(s 100)
(s 'how-many-calls?)
(s 100)
(s 100)
(s 'how-many-calls?)