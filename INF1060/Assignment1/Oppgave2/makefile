CC=gcc
CFLAGS=-Wall -Wextra -Wpedantic -g
BINARIES= testfil

.PHONY: all clean

all: $(BINARIES)

run: testfil
	./testfil

check: testfil
	valgrind --leak-check=full ./testfil

%:%.c
	$(CC) $(CFLAGS) $^ -o $@

clean:
	rm -f $(BINARIES)
