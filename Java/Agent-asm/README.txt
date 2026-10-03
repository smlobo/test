 1098  rm *class Agent.jar 
 1099  javac -cp .:asm-6.2.1.jar Agent.java 
 1100  jar cfm Agent.jar Manifest.txt Agent.class SampleTransformer.class ClassPrinter.class 
 1101  rm *class
 1102  javac HelloWorld.java 
 1103  java -javaagent:./Agent.jar HelloWorld

