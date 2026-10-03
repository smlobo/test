package com.example.processor;

import com.example.ann.GenerateHello;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes("com.example.ann.GenerateHello")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class GenerateHelloProcessor extends AbstractProcessor {

    private Messager messager;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(GenerateHello.class)) {
            if (!(element instanceof TypeElement)) {
                continue;
            }

            TypeElement typeElement = (TypeElement) element;
            String packageName = processingEnv.getElementUtils()
                    .getPackageOf(typeElement)
                    .getQualifiedName()
                    .toString();
            String originalName = typeElement.getSimpleName().toString();
            String generatedName = originalName + "Generated";
            String fqcn = packageName + "." + generatedName;

            try {
                JavaFileObject file = filer.createSourceFile(fqcn, typeElement);
                try (Writer writer = file.openWriter()) {
                    writer.write("package " + packageName + ";\n\n");
                    writer.write("public class " + generatedName + " {\n");
                    writer.write("    public static String message() {\n");
                    writer.write("        return \"Hello from generated code for " + originalName + "!\";\n");
                    writer.write("    }\n");
                    writer.write("}\n");
                }
                messager.printMessage(Diagnostic.Kind.NOTE, "Generated " + fqcn);
            } catch (IOException e) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Generation failed: " + e.getMessage());
            }
        }

        return true;
    }
}
