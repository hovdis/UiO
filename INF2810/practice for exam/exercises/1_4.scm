(define (a-plus-abs-b a b)
  ((if (> b 0) + -) a b))
#|
The function adds a to the absolute-value of b.
If the value of b is negative; the b is subtracted from a.
Since b is then negative, it will be (a - - b).
If the value of b is positive; the b is added to a.
Since b is then positive, it will be (a + b).
|#

