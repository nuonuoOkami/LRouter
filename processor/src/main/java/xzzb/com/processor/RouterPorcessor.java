package xzzb.com.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import xzzb.com.processor_lib.LRoute;

@AutoService(Processor.class)
public class RouterPorcessor extends AbstractProcessor {
    private Filer filer;
    private Messager messager;
    private Elements elementUtils;
    private String packageName;

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {


        HashSet<TypeElement> map = new HashSet<>();

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(LRoute.class);

        for (Element element : elements) {

            ElementKind elementKind = element.getKind();

            if (elementKind == ElementKind.CLASS) {

                TypeElement element4Class = (TypeElement) element;
                map.add(element4Class);
                packageName = elementUtils.getPackageOf(element4Class).toString();


            }
        }

        TypeSpec.Builder classBuilder = TypeSpec.classBuilder("LRouterMap")
                .addModifiers(Modifier.PUBLIC);

        ClassName hashMap = ClassName.get("java.util", "HashMap");
        ClassName key = ClassName.get("java.lang", "String");
        ClassName value = ClassName.get("java.lang", "String");

        MethodSpec.Builder build = MethodSpec.methodBuilder("getMaps").addModifiers(Modifier.PUBLIC);
        TypeName listOfHoverboards = ParameterizedTypeName.get(hashMap, key, value);
        build.addStatement("$T result = new $T<>()", listOfHoverboards, hashMap);
        build.returns(listOfHoverboards);
        for (TypeElement e : map) {


            String classname = e.getQualifiedName().toString();
            LRoute annotation = e.getAnnotation(LRoute.class);
            String path = annotation.path();
            build.addStatement("result.put($S,$S)", path, classname);

        }

        build.addStatement("return result");

        MethodSpec printNameMethodSpec = build.build();
        TypeSpec classTypeSpec = classBuilder.addMethod(printNameMethodSpec).build();

        try {

            JavaFile javaFile = JavaFile.builder(packageName, classTypeSpec)
                    .addFileComment(" Leo Compile time annotations !")
                    .build();
            javaFile.writeTo(filer);
        } catch (IOException exception) {
            exception.printStackTrace();
        }


        return true;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        elementUtils = processingEnvironment.getElementUtils();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> set = new HashSet<>();
        set.add(LRoute.class.getName());
        return set;
    }
}
