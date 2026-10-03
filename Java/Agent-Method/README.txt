% javac -cp .:./javassist.jar Agent.java 
% jar cfm Agent.jar Manifest.txt Agent.class SampleTransformer.class Notifier.class
% java -cp .:./javassist.jar -javaagent:./Agent.jar HelloWorld

