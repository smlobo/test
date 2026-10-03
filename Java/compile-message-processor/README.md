# Just compile time message (from annotation processor)

This processor reads `@CompileMessage("...")` and prints string at compile time

## Files

- `TextValue` annotation
- `TextValueProcessor` processor
- `Input` annotated class
- `Main` reads generated `InputText.VALUE`

## Build + run

```bash
cd simple-string-processor
mkdir -p out/processor out/app generated

javac -source 8 -target 8 -d out/processor \
  src/example/TextValue.java \
  src/example/TextValueProcessor.java

cp -R src/META-INF out/processor/

javac -source 8 -target 8 \
  -cp out/processor \
  -processorpath out/processor \
  -processor example.CompileMessageProcessor \
  -d out/app \
  -s generated \
  src/example/Input.java \
  src/example/Main.java

java -cp out/app example.Main
```

Expected:

```text
hello from annotation
```
