windows:compileClasses build/Manifest.txt build/out
#build jar file
	cd build && jar cfm uncercal.jar Manifest.txt *.class
	
#move finished file to out folder
	
	cd build && cp uncercal.jar ./out
	
	cd build && rm uncercal.jar
	@echo done.
	
compileClasses:uncercal.java
#compile the java classes
	javac -d ./build *.java
	
build/out:
#create the output folder if it does not already exist
	cd build && mkdir out
	
build/Manifest.txt:
#move the manifest file into the build folder
	cp Manifest.txt ./build