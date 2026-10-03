package example;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.NullType;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("example.CompileMessage")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class CompileMessageProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(
                Diagnostic.Kind.NOTE,
                "CompileMessageProcessor ran. Found @CompileMessage count = "
                        + roundEnv.getElementsAnnotatedWith(CompileMessage.class).size()
        );

        for (Element root : roundEnv.getRootElements()) {
            processingEnv.getMessager().printMessage(
                    Diagnostic.Kind.NOTE,
                    "Root element: " + root.toString(),
                    root
            );
        }

        for (Element e : roundEnv.getElementsAnnotatedWith(CompileMessage.class)) {
            if (!(e instanceof TypeElement type)) {
                continue;
            }

            String className;
            if (e.getKind().isClass() || e.getKind().isInterface()) {
                className = ((TypeElement) e).getQualifiedName().toString();
            } else {
                className = ((TypeElement) e.getEnclosingElement()).getQualifiedName().toString();
            }
            String value;
            try {
                value = type.getAnnotation(CompileMessage.class).value();
            } catch (Exception ex) {
                value = "<empty>";
            }

            processingEnv.getMessager().printMessage(
                    Diagnostic.Kind.NOTE,
                    "@MyAnnotation seen in: " + className + " : " + value,
                    e // points message at exact element location
            );
        }

        processingEnv.getMessager().printMessage(
                Diagnostic.Kind.WARNING,
                "Done!!!!",
                null
        );
        return true; // claim handled
    }
}
