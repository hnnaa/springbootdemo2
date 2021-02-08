package com.example.test;

import com.example.annotation.Report;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 注解
 *
 * @author hnn
 * @date 2021/01/26
 */
public class AnnotationTest {

    /**
     * 注解反射
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void testAnnotation() throws NoSuchMethodException {
        Method method = ThreadDead.class.getMethod("run", null);
        if (method.isAnnotationPresent(Report.class)) {
            Annotation[] annotations = method.getAnnotations();
            System.out.println(Arrays.toString(annotations));
        }
    }
}
