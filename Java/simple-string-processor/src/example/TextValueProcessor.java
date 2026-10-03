package example;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes("example.TextValue")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TextValueProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Filer filer = processingEnv.getFiler();

        for (Element e : roundEnv.getElementsAnnotatedWith(TextValue.class)) {
            if (!(e instanceof TypeElement)) {
                continue;
            }

            TypeElement type = (TypeElement) e;
            String pkg = processingEnv.getElementUtils().getPackageOf(type).getQualifiedName().toString();
            String original = type.getSimpleName().toString();
            String generated = original + "Text";
            String value = type.getAnnotation(TextValue.class).value();

            try {
                JavaFileObject file = filer.createSourceFile(pkg + "." + generated, type);
                try (Writer w = file.openWriter()) {
                    w.write("package " + pkg + ";\n\n");
                    w.write("public class " + generated + " {\n");
                    w.write("    public static final String VALUE = \"" + value.replace("\\", "\\\\").replace("\"", "\\\"") + "\";\n");
                    w.write("}\n");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        return true;
    }
}
