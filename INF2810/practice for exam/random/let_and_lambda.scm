(let ((x 1)
      (y 2)
      (z 3))
  (+ x y z))

#|
Dette funker ikke.
(let ((x 1)
      (y 2)
      (z (+ x y)))
  (+ x y z))
|#
; ; Dette funker.
(let* ((x 1)
       (y 2)
       (z (+ x y)))
  (+ x y z))


(let ((x 1))
  (let ((y 2))
    (let ((z 3))
      (+ x y z))))

((lambda (x y z)
   (+ x y z))
 1 2 3 )

((lambda (x)
   (let ((y 2))
     (let ((z 3))
       (+ x y z))))
 1)

((lambda (x)
   ((lambda (y)
      (let ((z 3))
        (+ x y z)))
    2)) 1)

((lambda (x)
   ((lambda (y)
      ((lambda (z)
         (+ x y z))
       3))
    2))
 1)


     