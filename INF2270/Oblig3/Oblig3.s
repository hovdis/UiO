.extern	fread, fwrite
.globl readbyte
.globl _readbyte

.globl	writebyte                                                                #oppgave 1
# Navn:	writebyte
# Synopsis:	Skriver en byte til en binÃ¦rfil.
# C-signatur: 	void writebyte (FILE *f, unsigned char b)
# Registre:

writebyte:
  pushl	 %ebp		                  # Standard funksjonsstart
  movl	 %esp,%ebp	#
  pushl  8(%ebp)
  pushl  $1
  pushl  $1
  leal   12(%ebp), %ebx

  pushl  %ebx                     # Setter paa stakken
  call   fwrite

  popl   %ebx                     # Fjerner variabler fra stakken. Kunne ogsaa gjort dette med addl $16, %ebp
  popl   %ebx
  popl   %ebx
  popl   %ebx
  popl	 %ebp		                  # Standard    #leal
  ret			                        # retur.

.globl	writeutf8char                                                            #oppgave 2
# Navn:	writeutf8char
# Synopsis:	Skriver et tegn kodet som UTF-8 til en binÃ¦rfil.
# C-signatur: 	void writeutf8char (FILE *f, unsigned long u)
# Registre:


writeutf8char:
  pushl	 %ebp		                  # Standard funksjonsstart
  movl	 %esp,%ebp

  movl	 12(%ebp), %edx           # Flytter unicode inn i edx
  roll	 $16, %edx		            # Ruller 16. Dette fordi det er 11 0'ere + 5 bits for aa sjeke om fjerde byte er tom
  cmpb	 $0, %dl 		              # Hvis ikke 0 saa er det bare 4 byte med unicode
  jne		 utf8_4byte

  roll	 $5, %edx		              # Ruller 5 for aa sjekke om tredje byte er tom
  cmpb	 $0, %dl 		              # Hvis !=0 er det 3 byte med unicode
  jne		 utf8_3byte

  roll	 $4, %edx		              # Ruller 4 for aa sjekke om andre byte er tom
  cmpb	 $0, %dl 		              # Hvis !=0 saa er det 2 byte med unicode
  jne		 utf8_2byte

  movl	 12(%ebp), %edx	          # Hvis den kommer saa langt maa det vaere ASCII tegn som skal skrives
  andb	 $0x7F, %dl 		          # Masker med 0b01111111 for aa nulle ut MSB
  movl	 $1, %ecx 		            # Signaliserer at bare 1 byte skar skrives.
  rorl	 $8, %edx 		            # Ruller 8 bit til hoyre for aa sette starten i overste posisjon

  jmp		 writeutf8 			          # Hopper til filskriving


utf8_4byte:
  movl	 $3, %ecx 		            # Den skal loope tre ganger
  movl	 12(%ebp), %eax 	        # Flytter inn i %eax
  movl	 $0, %edx 		            # Nuller ut edx


loop4byte:
  decl	 %ecx			                # -1 paa ecx (minus paa teller)
  movb	 %al, %dl 		            # Flytter LSB fra unicoden inn i dl
  orb		 $0x80, %dl 		          # or med 10000000 for aa sette overste til 1
  andb	 $0xBF, %dl 		          # and med 10111111 for aa sette nest overste til 0
  rorl	 $8, %edx 		            # roterer edx 8 til hoyre for aa gjore plass til ny byte i dl
  rorl	 $6, %eax 		            # roterer eax 6 til hoyre for aa fjerne de 6 brukte bitsene fra nederste byte
  cmpl	 $0, %ecx 		            # Sjekker om det er flere folgebytes

  jne		 loop4byte

  movb	 %al, %dl 		            # Flytter LSB fra unicoden inn i dl
  orb		 $0xF0, %dl 	   	        # or med 11110000 for aa sette de 4 overste til 1
  andb	 $0xF7, %dl 	   	        # and med 11110111 for aa sette den 5. overste til 0

  rorl	 $8, %edx 		            # Ruller 8 bit til hoyre for aa sette starten i overste posisjon
  movl	 $4, %ecx 	    	        # Setter 4 i ecx for aa signalisere at 4 bytes skal skrives

  jmp		 writeutf8 	  	          # Hopper til filskriving


utf8_3byte:
  movl	 $2, %ecx 		            # Signaliserer at den skal loope to ganger
  movl	 12(%ebp), %eax	          # Flytter unicoden inn i %eax
  movl	 $0, %edx		              # Nuller %edx


loop3byte:
  decl	 %ecx			                # Gjor det samme som loop2 over
  movb	 %al, %dl
  orb		 $0x80, %dl
  andb	 $0xBF, %dl
  rorl	 $8, %edx
  rorl	 $6, %eax
  cmpl	 $0, %ecx
  jne		 loop3byte

  movb	 %al, %dl 		            # Flytter nederst byte fra unicoden inn i dl
  orb		 $0xE0, %dl 		          # or med 11100000 for aa sette de 3 overste til 1
  andb	 $0xEF, %dl 		          # and med 11101111 fir aa sette den 4. overste til 0

  rorl   $8, %edx 		            # Ruller 8 bit til hoyre for aa sette starten i overste posisjon
  movl	 $3, %ecx 		            # Setter 3 i ecx for signalisere at 3 bytes skal skrives

  jmp		 writeutf8 			          # Hopper til filskriving


utf8_2byte:
  movl	 12(%ebp), %eax 	        # Flytter unicoden inn i %eax
  movl	 $0, %edx 		            # Nuller %edx

  movb	 %al, %dl 		            #samme logikk som i loop 1 og 2 (bare uten loopingen)
  orb		 $0x80, %dl
  andb	 $0xBF, %dl
  rorl	 $8, %edx
  rorl	 $6, %eax

  movb	 %al, %dl 		            # Flytter nederst byte fra unicoden inn i dl
  orb		 $0xC0, %dl 		          # or med 11000000 for aa sette de 2 overste til 1
  andb	 $0xDF, %dl 		          # and med 11011111 fir aa sette den 3. overste til 0

  rorl	 $8, %edx
  movl	 $2, %ecx


writeutf8:
  decl	 %ecx			                 # trekker fra byteteller
  roll	 $8, %edx		               # Roterer for aa faa overste byte i %dl

  movl	 $0, %eax		               # Nuller %eax
  movb	 %dl, %al 		             # Legger byte inn i %al

  pushl	 %edx			                 # Legger edx paa stakken for aa beholde verdien
  pushl	 %ecx			                 # Legger ecx paa stakken for aa beholde verdien
  pushl	 %eax			                 # Byte paa stakken
  pushl  8(%ebp)			             # Fil paa stakken
  call 	 writebyte 		             # Kaller writebyte
  popl	 %eax			                 # Rydder opp
  popl	 %eax
  popl	 %ecx
  popl	 %edx

  cmpl	 $0, %ecx		               # Sjekker om fler byte skal skrives
  jne		 writeutf8

  popl	 %ebp		                   # Standard
  ret			                         # retur.


.globl	readbyte                                                                 #oppgave 3
# Navn:	readbyte
# Synopsis:	Leser en byte fra en binÃ¦rfil.
# C-signatur: 	int readbyte (FILE *f)
# Registre:

readbyte:
  pushl	 %ebp		                   # Standard funksjonsstart
  movl	 %esp, %ebp
  subl   $4, %esp                  # gjor plass til variabel

  pushl  8(%ebp)                   # Filpekeren til stakk
  pushl  $1
  pushl  $1
  leal   -4(%ebp), %edx            # Henter
  pushl  %edx                      # Adressen til lokalvariabelen

  call   fread

  popl   %edx                      #rydder opp
  popl   %edx
  popl   %edx
  popl   %edx

  cmpl   $0, %eax
  movl   $-1, %eax                 #hvis den er mindre eller lik 0, hopp og returner -1
  jle    slutt_rb

  movl   -4(%ebp), %eax


slutt_rb:
  addl   $4, %esp                  # Rydde opp
  popl	 %ebp		                   # Standard
  ret			                         # returnerer (int)c

.globl	readutf8char                                                             #oppgave 4
# Navn:	readutf8char
# Synopsis:	Leser et Unicode-tegn fra en binÃ¦rfil.
# C-signatur: 	long readutf8char (FILE *f)
# Registre:

readutf8char:
  pushl	 %ebp              		     # Standard funksjonsstart
  movl	 %esp,%ebp	               #

  pushl  8(%ebp)                   # Leser det forste bytet
  call   readbyte
  popl   %edx

  cmpl   $-1, %eax                 #sjekker for end of file (EOF)
  je     slutt_r8

  andl   $0xFF, %eax               # Nuller ut alt untenom siste byte i %eax

  cmpl   $0xF0, %eax              #sjekker om det er 4bytes unicode
  jge    readutf4b

  cmpl   $0xE0, %eax              #sjekker om det er 3bytes unicode
  jge    readutf3b

  cmpl   $0xC0, %eax              #sjekker om det er 2bytes unicode
  jge    readutf2b

  jmp    slutt_r8

readutf4b:
  movl   $0, %edx                  #nuller ut
  movl   %eax, %edx                #forste byte til arbeidsregister
  andb   $0x7, %dl                 #fjerner utf8-koden fra resten av unicoden

  movl   $3, %ecx                  #flytter tre til teller for aa signalisere antall folgebytes

loop_r4b:
  roll   $6, %edx                  #ruller 6 for aa faa plass til unicoden
  pushl  %ecx                      #legger ecx paa stakken for aa beholde verdien under Kaller
  pushl  %edx                      # Samme som over
  pushl  8(%ebp)                   #filpekeren blir lagt paa stakken som paramenter

  call   readbyte

  popl   %edx                      #rydder opp
  popl   %edx
  popl   %ecx

  andb   $0x3F, %al                # Fjerner utf8-koden fra folgebyten
  orb    %al, %dl                  # legger til de 6 bytene med unicode inn i arbeidsregister

  decl   %ecx                      #trekker fra en fra telleren
  cmpl   $0, %ecx                  # sjekker om det finnes flere folgebytes
  jne    loop_r4b                  # om den ikke er ferdig hopper den

  movl   %edx, %eax                #Flytter verdi fra arbeidsregister til returen
  jmp    slutt_r8                  # Avslutter


readutf3b:
  movl   $0, %edx                  # Nuller ut edx
  movl   %eax, %edx                # forste byte til arbeidsregister
  andb   $0x0F, %dl                # Gjerner utf8-koden fra resten av unicoden

  movl   $2, %ecx                  #for aa si ifra om antall folgebytes

loop_r3b:
  roll   $6, %edx                  #Denne metoden her gjor akkurat det samme som i readutf4b, bare med 2 i stedet for 3
  pushl  %ecx
  pushl  %edx
  pushl  8(%ebp)

  call   readbyte

  popl   %edx
  popl   %edx
  popl   %ecx

  andb   $0x3F, %al
  orb    %al, %dl

  decl   %ecx
  cmpl   $0, %ecx
  jne    loop_r3b

  movl   %edx, %eax
  jmp    slutt_r8

readutf2b:
  movl   $0, %edx                   #samme som i koden over, men uten loop, siden det bare er en folgebyte
  movl   %eax, %edx
  andb   $0x1F, %dl
  roll   $6, %edx

  pushl  %edx
  pushl  8(%ebp)

  call   readbyte

  popl   %edx
  popl   %edx

  andb   $0x3F, %al
  orb    %al, %dl
  movl   %edx, %eax

slutt_r8:
  popl	%ebp		                    # Standard
  ret					                      # retur.
