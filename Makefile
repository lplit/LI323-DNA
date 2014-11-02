SRC = ./src/DNAReader/*.java
JAR = stat.DNA-LeGoff-Rudek.jar

run : all 
	@echo "Running..."
	@java -cp ./bin DNAReader.DNA

all : $(SRC)
	@echo "Compiling..."
	@javac -d ./bin $(SRC)

doc : $(SRC)
	javadoc -private -charset utf-8 -sourcepath src/ -d doc-private/ DNAReader

jar : $(SRC)
	jar cvf $(JAR) .

clean : 
	@echo "Cleaning..."
	@echo "Removing temporary (*~) files..."
	@rm -rf *~
	@rm -rf ./src/*~ 
	@echo "Removing .class files..."
	@rm -rf ./bin/*.class
	@rm -rf $(JAR)
	@echo "Removing JavaDoc..."
	@rm -rf ./doc-private
	@echo "Done!"

.PHONY : clean