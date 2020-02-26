; ; Author: Rune Hovde, Magnus Biong Nordin og Nicolas Harlem Eide
; ; Oppgave 1

; ; a)
(load "prekode3a.scm")
(define (list-to-stream list)
  (if (null? list) the-empty-stream
      (cons-stream (car list) (list-to-stream (cdr list)))))


(define (stream-to-list stream . args)
  (cond ((eq? the-empty-stream stream) '())
        ((null? args)
         (cons (stream-car stream) (stream-to-list (stream-cdr stream))))
        (else
         (if (> (car args) 0)
             (cons (stream-car stream)
                   (stream-to-list (stream-cdr stream) (- (car args) 1)))
             '()))))

; ; Tester ut
(list-to-stream '(1 2 3 4 5))
(stream-to-list (stream-interval 10 20))
(show-stream nats 15)
(stream-to-list nats 10)


; ; b)
(define (stream-map proc . argstreams)
  
  (define (stream-iter argstreams)
    (cond ((null? argstreams) #f)
          ((stream-null? (car argstreams)) #t)
          (else (stream-iter (cdr argstreams)))))

  (if (stream-iter argstreams)
      the-empty-stream
      (cons-stream
       (apply proc (map stream-car argstreams))
       (apply stream-map (cons proc (map stream-cdr argstreams))))))

; ; Tester ut
(define bla1 (list-to-stream '(1 2 3)))
(define bla2 (list-to-stream '(1 2 3 4 5 6)))
(define bla3 (list-to-stream '(1 2 2)))
(display "Tester 1b \n")
(stream-to-list (stream-map + bla1 bla2 bla3))

; ; c)

; ; Et potensielt problem med Petter Smarts forslag, om å tilpasse
; ; remove-duplicates til å fungere med strømmer, kan være om strømmene
; ; er uendelig lange. Prosedyren memq stopper ikke å itere gjennom listen
; ; før et gitt symbol forekommer, og om det ikke finnes i en uendelig liste
; ; vil den ikke stoppe (før den går tom for minne).
; ; En mulig quick-fix ville vært å gi med enda et argument til memq, en teller,
; ; som begrenser hvor mange plasser memq skal gå gjennom.

; ; d)
(define (show x)
  (display x)
  (newline)
  x)

(define x
  (stream-map show
              (stream-interval 0 10)))
; ; Output: 0
; ; Reason: Grunnen til at outputten blir 0 er fordi cons-stream (som blir kalt i stream-map) setter en delay på cdr av strømmen.
; ;         Show blir da altså bare kalt på en gang, med det første elementet i strømmen (når den defineres).


(stream-ref x 5)
; ; Output: 1-5 (med newline for hvert tall) (display-kall), så returneres 5
; ; Reason: For å finne det 5. elementet i strømmen (tellende fra 0) må det bli evaluert først.
; ;         Dette er grunnen til at 0, 1, 2, 3, 4 og 5 blir skrevet ut. 0 er allerede evaluert (i definisjonen av x).



(stream-ref x 7)
; ; Output: 6-7 (med newline for hvert tall) (display-kall), så returneres 7.
; ; Reason: Samme grunn som over, bare med 6 og 7 i stedet for 0, 1, 2, 3, 4, 5
; ;         Grunnen til at 0, 1, 2, 3, 4 og 5 ikke blir skrevet ut er fordi vi allerede har disse verdiene prossesert/forcet.



; ; Oppgave 2

; ; a)

(define count 0)

(define (make-lm)
  (set! count 0)
  (list '*model*))

(define (lm-lookup-bigram lm st1 st2)
  (if (null? (cdr lm)) 0
      (let ((key (assoc st1 (cadr lm)))
            (val (assoc st2 (cdadr lm))))
        (if (and key val)
            (cdr val)
            (lm-lookup-bigram (cdr lm) st1 st2)))))



(define (lm-record-bigram! lm s1 s2)
  (cond ((null? (cdr lm))
         (set! count (+ 1 count))
         (set-cdr! lm (cons (list (cons s1 1) (cons s2 1)) (cdr lm))))
        ((equal? s1 (caaadr lm))
         (let ((key (caadr lm))
               (value (assoc s2 (cdadr lm))))
           (begin (set! count (+ 1 count))
                  (set-cdr! key (+ (cdr key) 1)))
           (if value
               (set-cdr! value (+ 1 (cdr value)))
               (set-cdr! (cadr lm) (cons (cons s2 1) (cdr (cadr lm)))))))
        (else
         (lm-record-bigram! (cdr lm) s1 s2))))


; ; Tester
(define mod (make-lm))
mod
; ; Svar jeg forventer (*model*)

(lm-record-bigram! mod "the" "jury")
(lm-record-bigram! mod "the" "jury")
(lm-record-bigram! mod "the" "way")
(lm-record-bigram! mod "a" "way")

mod
; ; Forventet svar: (*model* (("the" . 3) ("way" . 1) ("jury" . 2)) (("a" . 1) ("way" . 1)))

(lm-lookup-bigram mod "the" "jury")
; ; Forventet svar: 2

(lm-lookup-bigram mod "hey" "ho")
; ; Forventet svar: 0

; ; b)

(define (lm-train! lm corpus)
  (define (rec-sents sents)
    (if (or (null? sents) (null? (cdr sents))) #t
        (and (lm-record-bigram! lm (car sents) (cadr sents))
             (rec-sents (cdr sents)))))
  
  (if (null? corpus) #t
      (let ((sents (car corpus)))
        (rec-sents sents)
        (lm-train! lm (cdr corpus)))))

(define brown (read-corpus "brown.txt"))
(define model (make-lm))

(lm-train! model brown)
; ; Forventet svar: #t

(lm-lookup-bigram model "jury" "said")
; ; Forventet svar: 3
  

; ; c)
; ; Det er estimate og score som itte funker tror je...

(define (lm-estimate! lm)
  (define (prop-iter word-list freq)
    (if (null? word-list) #t
        (let* ((word (caar word-list))
               (word-freq (cdar word-list))
               (pair (assoc word (cdadr lm))))
          (set-cdr! pair
                    (/ word-freq freq))
          (prop-iter (cdr word-list) freq))))
  
  (if (null? (cdr lm)) #t
      (let ((freq (cdaadr lm)))
        (prop-iter (cdadr lm) freq)
        (lm-estimate! (cdr lm)))))


; ; d)

(define test (read-corpus "test.txt"))

(define (lm-score lm sentence)
  (if (string=? (car sentence) "</s>") 1
      (let ((bigram (lm-lookup-bigram lm (car sentence) (cadr sentence))))
        (* (if (= bigram 0)
               (/ 1 count)
               bigram)
           (lm-score lm (cdr sentence))))))

(lm-train! model brown)
(lm-estimate! model)


; ; Under er hvordan vi fant ut hvilken setning som var minst og
; ; mest sannsynlig. Jeg kunne gjort dette rekursivt, men gjorde ikke dette.

(define setning1 (car (read-corpus "test.txt")))
(define setning2 (car (cdr (read-corpus "test.txt"))))
(define setning3 (caddr (read-corpus "test.txt")))
(define setning4 (cadddr (read-corpus "test.txt")))
(define setning5 (cadddr (cdr (read-corpus "test.txt"))))
(define setning6 (cadddr (cdddr (read-corpus "test.txt"))))
(define setning7 (cadddr (cdddr (cdr (read-corpus "test.txt")))))
(define setning8 (cadddr (cdddr (cddr (read-corpus "test.txt")))))
(define setning9 (cadddr (cdddr (cdddr (read-corpus "test.txt")))))
(define setning10 (cadddr (cddddr (cdddr (read-corpus "test.txt")))))
(define setning11 (cadddr (cddddr (cddddr (read-corpus "test.txt")))))
(define setning12 (cadddr (cddddr (cddddr (cdr (read-corpus "test.txt"))))))
(define setning13 (cadddr (cddddr (cddddr (cddr (read-corpus "test.txt"))))))
(define setning14 (cadddr (cddddr (cddddr (cdddr (read-corpus "test.txt"))))))
(define setning15 (cadddr (cddddr (cddddr (cddddr (read-corpus "test.txt"))))))
(define setning16 (cadddr (cddddr (cddddr (cddddr (cdr (read-corpus "test.txt")))))))
(define setning17 (cadddr (cddddr (cddddr (cddddr (cddr (read-corpus "test.txt")))))))
(define setning18 (cadddr (cddddr (cddddr (cddddr (cdddr (read-corpus "test.txt")))))))
(define setning19 (cadddr (cddddr (cddddr (cddddr (cddddr (read-corpus "test.txt")))))))
(define setning20 (cadddr (cddddr (cddddr (cddddr (cddddr (cdr (read-corpus "test.txt"))))))))
(define setning21 (cadddr (cddddr (cddddr (cddddr (cddddr (cddr (read-corpus "test.txt"))))))))
(define setning22 (cadddr (cddddr (cddddr (cddddr (cddddr (cdddr (read-corpus "test.txt"))))))))
(define x
  (list 
   (lm-score model setning1)
   (lm-score model setning2)
   (lm-score model setning3)
   (lm-score model setning4)
   (lm-score model setning5)
   (lm-score model setning6)
   (lm-score model setning7)
   (lm-score model setning8)
   (lm-score model setning9)
   (lm-score model setning10)
   (lm-score model setning11)
   (lm-score model setning12)
   (lm-score model setning13)
   (lm-score model setning14)
   (lm-score model setning15)
   (lm-score model setning16)
   (lm-score model setning17)
   (lm-score model setning18)
   (lm-score model setning19)
   (lm-score model setning20)
   (lm-score model setning21)
   (lm-score model setning22)))
; ; Her er en metode som finner stoeste nummeret. Dette vil bli den setningen
; ; som har størst sannsynlighet. Jeg byttet ut > med < for den samme metoden for aa
; ; finne den med minst sannsynlighet.

; ; Setningen med mest sannsynlighet er:
; ; Fundamentally illegal, unfair evidence was dismissed by it, as the case was approached by the court.

; ; Setningen med minst sannsynlighet er:
; ; As the case was approached by the court, unfair, fundamentally illegal evidence was dismissed by it.
(define (getlargest a_list)
  (if (null? a_list) 
      #f 
      (let loop ((a_list (cdr a_list))
                 (maxval (car a_list)))
        (cond ((null? a_list) maxval)
              ((> (car a_list) maxval)
               (loop (cdr a_list) (car a_list)))
              (else
               (loop (cdr a_list) maxval))))))

(display (getlargest x))
