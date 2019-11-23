CC = gcc
CFLAGS =-Wall -Wextra -Wpedantic -std=c99 -g
BINS = ruterdrift

ruterdrift: ruterdrift.c ruterdrift.h
	$(CC) $(CFLAGS) ruterdrift.c -o ruterdrift

run: ruterdrift
	./ruterdrift 10router.dat

check: ruterdrift
	valgrind ./ruterdrift 10router.dat

clean:
	rm -f $(BINS)
