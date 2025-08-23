build: clean
	@javac *.java -d build

run: build
	@java -cp build Main

clean:
	@rm -rf build