CC=gcc
CFLAGS=-Wall -Wextra -Wpedantic -std=c99 -g
BINARIES= oppg3

all: $(BINARIES)

run: oppg3
	./oppg3

%:%.c
	$(CC) $(CFLAGS) $^ -o $@

clean:
	rm -f $(BINARIES)
