run: DiffEq
	java DiffEq

DiffEq: DiffEq.java Panel.java
	javac $^

.PHONY: clean
clean:
	rm ./*.class


