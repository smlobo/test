#!/usr/bin/python3

import sys
import os

javaVersions = ["/etc/alternatives/java_sdk_1.6.0",
	"/etc/alternatives/java_sdk_1.7.0",
	"/etc/alternatives/java_sdk_1.8.0",
	"/usr/java/jdk1.6.0_201",
	"/usr/java/jdk1.7.0_191-amd64",
	"/usr/java/jdk1.8.0_201-amd64",
	"/usr/java/jdk-11.0.3",
	"/usr/java/jdk-12.0.1"]

if len(sys.argv) != 2:
	print("Usage: {} <java-file>".format(__file__))
javaFile = sys.argv[1]

for javaPath in javaVersions:
	print(javaPath)
	javacCmd = "{}/bin/javac {}".format(javaPath, javaFile)
	print(javacCmd)
	os.system(javacCmd)
	javaCmd = "{}/bin/java {}".format(javaPath, javaFile.split('.')[0])
	print(javaCmd)
	os.system(javaCmd)
