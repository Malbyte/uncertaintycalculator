windows:uncercal.java
	javac -d ./build *.java
	cd build || jar cf uncercal.jar *.class