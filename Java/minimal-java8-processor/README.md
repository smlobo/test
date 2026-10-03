# Minimal Java 8 Annotation Processor Example

This project shows a tiny annotation processor that generates one class.

## Structure

- `com.example.ann.GenerateHello` - marker annotation
- `com.example.processor.GenerateHelloProcessor` - processor
- `com.example.demo.DemoInput` - annotated source
- `com.example.demo.Main` - uses generated class

## Build and run (javac only)

From this directory:

```bash
mkdir -p out/processor out/app generated

# 1) Compile annotation + processor
javac -source 8 -target 8 -d out/processor \
  src/com/example/ann/GenerateHello.java \
  src/com/example/processor/GenerateHelloProcessor.java

# 2) Register processor service
cp -R src/META-INF out/processor/

# 3) Compile app sources with processor on processorpath
javac -source 8 -target 8 \
  -cp out/processor \
  -processorpath out/processor \
  -d out/app \
  -s generated \
  src/com/example/demo/DemoInput.java \
  src/com/example/demo/Main.java

# 4) Run
java -cp out/app com.example.demo.Main
```

Expected output:

```text
Hello from generated code for DemoInput!
```
