(define (reduce proc init lst)
  (if (null? lst)
      init
      (proc (car lst)
            (reduce proc init (cdr lst)))))

(define (percentages items)
  (map (lambda (x) (/ x (/ (reduce + 0 items) 100)))
       items))

(percentages '(10 90 50 50))

(define (new-percentages items)
  (let ((sumcent (/ (reduce + 0 items) 100)))
    (map (lambda (x) (/ x sumcent))
         items)))

(define (new-new-percentages items)
  ((lambda (sumcent)
     (map (lambda (x) (/ x sumcent))
          items))
   (/ (reduce + 0 items) 100)))
; ; Disse to er helt like (new og new-new
; ;


; ; Metode for å telle antall noder i lista:
(define (count-leaves tree)
  (cond ((null? tree) 0)
        ((pair? tree)
         (+ (count-leaves (car tree))
            (count-leaves (cdr tree))))
        (else 1)))

(count-leaves '((1 2) 3 4))



; ; samle treet til en flat liste
; ; samler opp løvnodene
(define (fringe tree)
  (cond ((null? tree) '())
        ((pair? tree)
         (append (fringe (car tree))
                 (fringe (cdr tree))))
        (else (list tree))))
(fringe '((1 2) ((3) 4)))


(fringe '((((((1) 2) 3) (2 3 (3) 4 2) 4 ))5))
(count-leaves '((((((1) 2) 3) (2 3 (3) 4 2) 4 ))5))

; ; forskjell mellom append og cons:
(append '(1 2) '(3)) ; ; her blir det (1 2 3)
(cons '(1 2) '(3)) ; ; her blir det ((1 2) 3)

; ; vi har map. Men nå skal vi lage en høyereordensprosedyre som itererer over alle nodene i et tre. Og da kan vi sette inn et "template".

(define (tree-map proc tree)
  (cond ((null? tree) '())
        ((pair? tree)
         (cons (tree-map proc (car tree))
               (tree-map proc (cdr tree))))
        (else (proc tree))))
; ; Den lager en ny liste med samme struktur, men utfører proc på hver løvnode :)
; ; Alternativt kan map brukes i kombinasjon med rekursjon:

(define (new-tree-map proc tree)
  (map (lambda (subtree)
         (if (pair? subtree)
             (new-tree-map proc subtree) (proc subtree)))
       tree))

; ; count-leaves, fringe osv. vil gi en trerekursiv prosess.
; ; Fikk dårlig rykte da vi så på fib-eksempelet tudeligere:
; ;         - eksponentiell vekst og masse redundante beregninger.
; ; prosesstreet gjenspeilet trinnene i en naiv algoritme.
; ; her gjenspeiler det bare strukturen til inputdata.
; ; Kompleksiteten lineær i antall noder i input. Ingen redundans.


; ; Mengdelære

; ; Sjekke om et element er med i et set
(define (element-of-set? x set)
  (cond ((null? set) #f)
        ((= x (car set)) #t)
        (else (element-of-set? x (cdr set)))))


; ; sette inn x i set
(define (adjoin-set x set)
  (if (element-of-set? x set)
      set
      (cons x set)))

(display "hei")(newline)
; ; Det som er med i både set1 og set2. MÅ SE MER PÅ DENNE
(define (intersection-set set1 set2)
  (cond ((or (null? set1) (null? set2)) '())
        ((element-of-set? (car set1) set2)
         (cons (car set1)
               (intersection-set (cdr set1) set2)))
        (else (intersection-set (cdr set1) set2))))

(intersection-set '(1 2 3) '(3 4 5))


; ; En ny representasjon av element-of-set?
; ; Denne krever at settet er en ordnet liste/set.
; ; Fordel: trenger ikke nødvendigvis sjekke hvert element. Hvis elementet x er mindre enn første element i set så avbrytes det med en gang.
; ; Hvis x er større enn det største elementet i settet vil det være "worst-case". Da må den kjøre gjennom hele lista.
; ; I praksis kan vi regne med at antall trikk i snitt vil være n/2
(define (new-element-of-set? x set)
  (cond ((null? set) #f)
        ((= x (car set)) #t)
        ((< x (car set)) #f)
        (else (new-element-of-set? x (cdr set)))))

; ; Enda mer å spare på implementasjonen av snitt
; ; Sammenlikner første element i hver liste:
; ; -Hvis de er like inkluderer vi det, og ser videre på cdr av listene.
; ; -Hvis det ene er mindre enn det andre vet vi at det ikke kan væe medlem
; ; og vi ser på cdr av lista med det minste elementet.
(define (new-intersection-set set1 set2)
  (if (or (null? set1) (null? set2))
      '()
      (let ((x1 (car set1))
            (x2 (car set2)))
        (cond ((= x1 x2) (cons x1
                               (new-intersection-set (cdr set1)
                                                     (cdr set2))))
              ((< x1 x2) (new-intersection-set (cdr set1) set2))
              ((< x2 x1) (new-intersection-set set1 (cdr set2)))))))

; ; mengde som binærtre med ordnede noder.
; ; Hver node lagrer:
; ;     -Et element
; ;     -En venstregren med MINDRE elementer
; ;     -En høyregren med STØRRE elementer

(define (make-tree entry left right)
  (list entry left right))

(define (entry tree) (car tree))

(define (left-branch tree) (cadr tree)) ; ; car of cdr

(define (right-branch tree) (caddr tree)) ; ; car og cdr of cdr ???????

(define (new-new-element-of-set? x set) ;; log(n) vekst
  (cond ((null? set) #f)
        ((= x (entry set)) #t)
        ((< x (entry set))
         (new-new-element-of-set? x (left-branch set)))
        ((> x (entry set))
         (new-new-element-of-set? x (right-branch set)))))

(define (adjoin-set x set) ;; samme strategi
  (cond ((null? set) (make-tree x '() '()))
        ((= x (entry set)) set)
        ((< x (entry set))
         (make-tree (entry set)
                    (adjoin-set x (left-branch set))
                    (right-branch set)))
        ((> x (entry set))
         (make-tree (entry set)
                    (left-branch set)
                    (adjoin-set x (right-branch set))))))


(define (new-adjoin-set x set)
  (cond ((null? set) (make-tree x '() '()))
        ((= x (entry set)) set)
        ((< x (entry set))
         (make-tree (entry set)
                    (new-adjoin-set x (left-branch set))
                    (right-branch set)))
        ((> x (entry set))
         (make-tree (entry set)
                    (left-branch set)
                    (new-adjoin-set x (right-branch set))))))

; ; Burde hatt et predikat empty-tree?
; ; og kanskje en konstant empty-tree
; ; Eller (make-leaf x) i stedet for (make-tree x '() '())







; ; Digresjon: aritet




; ; legge sammen alle elementer i en vilkårlig lang liste.
(define (sum . args)
  (define (recurse list)
    (if (null? list)
        0
        (+ (car list)
           (recurse (cdr list)))))
  (recurse args))
(sum 1 2 3 4 5)


#|
(define (new-sum . args)
  (if (null? args)
      0
      (+ (car args)
         (new-sum (cdr args)))))
(new-sum 1 2 3)

Denne vil gå uendelig....
|#

