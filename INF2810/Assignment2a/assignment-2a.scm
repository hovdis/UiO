
; ; Authors:
; ; nicolaei (Nicolas Harlem Eide)
; ; runehovd (Rune Hovde)
; ; magnubn (Magnus Biong Nordin)

(load "huffman.scm")

; ; Oppgave 1a)
(display "=== 1a ===")
(newline)

(define (p-cons x y)
  (lambda (proc) (proc x y)))

(define (p-car lst)
  (lst (lambda (x y) x)))

(define (p-cdr lst)
  (lst (lambda (x y) y)))

(display (p-cons "foo" "bar"))
(newline)
(display (p-car (p-cons "foo" "bar")))
(newline)
(display (p-cdr (p-cons "foo" "bar")))
(newline)
(display (p-car (p-cdr (p-cons "zoo" (p-cons "foo" "bar")))))
(newline)
(newline)


; ; Oppgave 1b)
(display "=== 1b ===")
(newline)

(define foo 42)

(display
 (let ((foo 5)
       (x foo))
   (if (= x foo)
       'same
       'different)))
(newline)
(display
 ((lambda (foo x)
    (if (= foo x)
        'same
        'different))
  5 foo))
(newline)
(newline)

(display
 (let ((bar foo)
       (baz 'towel))
   (let ((bar (list bar baz))
         (foo baz))
     (list foo bar))))
(newline)
(display
 ((lambda (bar baz)
    ((lambda (bar foo)
       (list foo bar))
     (list bar baz) baz))
  foo 'towel))
(newline)
(newline)


; ; Oppgave 1c)
(display "=== 1c ===")
(newline)

(define (infix-eval expression)
  ((cadr expression) (car expression) (caddr expression)))

(display (infix-eval (list 1 + 1)))
(newline)
(newline)

; ; Oppgave 1d)
(display "=== 1d ===")
(newline)

; ; (define bah '(84 / 2))
; ; (display (infix-eval bah))

; ; Utfallet her blir anneledes fordi i en liste som starter med ' blir alt innholdet interpretet literart.
; ; Dermed er ikke / en prosedyre lenger, men det faktiske tegnet "/"
(newline)
(newline)

; ; Oppgave 2a)
(display "=== 2a ===")
(newline)

(define (member? pred item lst)
  (cond ((null? lst) #f)
        ((pred item (car lst)) #t)
        (else (member? pred item (cdr lst)))))

(display
 (member? eq? 'zoo '(bar foo zap)))
(newline)
(display
 (member? eq? 'foo '(bar foo zap)))
(newline)
(newline)

; ; Oppgave 2b)
(display "=== 2b ===")
(newline)
(newline)

; : Grunnen til at den interne prosedyren (decode-1) er definert er fordi
; ; vi ønsker å fortsette å bruke argumentet "tree" mens vi rekurserer gjennom treet.
; ; Man kunne lagt dette ved som et ekstra argument, men det ville bare vært overføldig
; ; og gitt mindre lesbar kode.
; ; Vi trenger altså fortsatt å ha det orginale treet(til senere) mens vi går gjennom grenene.


; ; Oppgave 2c)
(display "=== 2c ===")
(newline)
(display "SPØR GODESTE OM DETTE GÅR FINT (list (symbol etc...))")
(newline)

(define (decode bits tree)
  (define (decode-iter lst bits current-branch)
    (define (get-next-branch curr)
      (choose-branch (car bits) curr))
    (cond ((null? bits) lst)
          ((null? current-branch) '())
          ((leaf? (get-next-branch current-branch))
           (decode-iter
            (append lst (list (symbol-leaf (get-next-branch current-branch))))
            (cdr bits) tree))
          (else
           (decode-iter lst (cdr bits) (get-next-branch current-branch)))))
  (decode-iter '() bits tree))

(display (decode sample-code sample-tree))
(newline)
           

(newline)
(display "=== 2d ===")
(newline)
; ; Resultatet vi får når vi kjører koden (decode sample-code sample-tree) er
; ; (ninjas fight ninjas by night)


(display "=== 2e ===")
(newline)
(define (encode symb-lst tree)
  (define (encode-1 symb-lst curr-tree)
    (if (null? symb-lst)
        '()
        (if (leaf? curr-tree)
            (encode-1 (cdr symb-lst) tree)
            (if (member? eq? (car symb-lst) (symbols (left-branch curr-tree)))
                (cons 0 (encode-1 symb-lst (left-branch curr-tree)))
                (cons 1 (encode-1 symb-lst (right-branch curr-tree)))))))
  (encode-1 symb-lst tree))

(display (decode (encode '(ninjas night fight) sample-tree) sample-tree))
(newline)
(display (encode '(ninjas fight night) sample-tree))
(newline)

(display "=== 2f ===")
(newline)

(define (grow-huffman-tree freqs)
  (define (create-internal-node freqs)
    (make-code-tree (car freqs)
                    (cadr freqs)))

  (define (create-tree freqs)
    (if (null? (cdr freqs))
        (car freqs)
        (create-tree (adjoin-set (create-internal-node freqs) (cddr freqs)))))

  (create-tree (make-leaf-set freqs)))


(display (grow-huffman-tree '((a 2) (b 5) (c 2) (d 2) (e 2))))
(newline)
(newline)

(display "\n=== 2g ===\n")

(define all_words_and_freqs '((samurais 57) (ninjas 20) (fight 45) (night 12)
                                            (hide 3) (in 2) (ambush 2) (defeat 1)
                                            (the 5) (sword 4) (by 12) (assassin 1)
                                            (river 2) (forest 1) (wait 1) (poison 1)))
(define 2g (grow-huffman-tree all_words_and_freqs))
(display "\nSpørsmål 1:")
(define length-codewords (length (encode '(ninjas fight ninjas fight ninjas ninjas fight samurais samurais fight
                                                  samurais fight ninjas ninjas fight by night) 2g)))
(display length-codewords)
(display "\nSpørsmål 2:")
(display (/ length-codewords 17)) ; ; Fordi det er 17 ord i meldingen
(display "\nSpørsmål 3:")

; ; man trenger 17 * 4 = 68 antall bits for å representere denne meldingen
; ; med en kode med fast lengde over samme alfabetet som i oppgaven.
; ; Grunnen til dette er fordi vi har 16 ord, og for å representere 16 forskjellige
; ; ord trenger vi 4 bit. Dette fordi 2^4 = 15, pluss 0 som blir 16 totalt.
; ; Hvert ord vil da trenge 4 bit, og siden det er 17 ord, vil det bruke 68 bit.

(display "\n=== 2h ===\n")
(define (huffman-leaves tree)
  (if (leaf? tree)
      (list (list (symbol-leaf tree) (weight-leaf tree)))
      (append
       (huffman-leaves (left-branch tree))
       (huffman-leaves (right-branch tree)))))
  
(display (huffman-leaves sample-tree))
(newline)

(display "\n=== 2i ===\n")

(define (expected-codeword-length tree)
  (define (iter current-branch code-tree)
    (if (leaf? current-branch)
        (* (/ (weight current-branch) (weight tree)) code-tree)
        (+
         (iter (left-branch current-branch) (+ 1 code-tree))
         (iter (right-branch current-branch) (+ 1 code-tree)))))
  (iter tree 0))

(display (expected-codeword-length sample-tree))