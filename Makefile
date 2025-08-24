SRC_DIR := src
BUILD_DIR := build
MAIN_CLASS := src.app.Main

SOURCES := $(shell find $(SRC_DIR) -name "*.java")

build: clean
	@mkdir -p $(BUILD_DIR)
	@javac -d $(BUILD_DIR) $(SOURCES)

run: build
	@java -cp $(BUILD_DIR) $(MAIN_CLASS)

clean:
	@rm -rf $(BUILD_DIR)
