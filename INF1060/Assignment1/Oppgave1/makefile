CC = gcc
CFLAGS =-Wall -Wextra -Wpendantic -std=c99 -g
BINS=hello

.PHONY: all clean

all: $(BINS)

make: hello.c
	gcc hello.c -o hello -Wall -Wextra

run: test
	./hello hello

clean:
	rm -f hello
