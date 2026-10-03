# Simplest Java 8 Annotation Processor (String)

This processor reads `@TextValue("...")` and generates a companion class with a constant string.

Note: annotation processors cannot modify existing source files; they generate new files.

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
  -processor example.TextValueProcessor \
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
