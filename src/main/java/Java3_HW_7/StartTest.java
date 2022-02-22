package Java3_HW_7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StartTest {
    public static void main(String[] args) {

        try {
            start(ForTest.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void start(Class testClass) throws IllegalAccessException {

        int afterSuitCount = 0;
        int beforeSuitCont = 0;

        Method[] ms = testClass.getDeclaredMethods();
        List<Method> sortedTest = new ArrayList<>();

        Method after = null;
        Method before = null;

        for (Method m : ms) {
            m.setAccessible(true);
            if (m.isAnnotationPresent(Test.class)) {
                if (m.getAnnotation(Test.class).priority() > 10 || m.getAnnotation(Test.class).priority() < 1) {
                    throw new RuntimeException("Приоритет метода с аннотацией Test должен быть в пределах от 1 до 10. Ошибка в методе: " + m.getName());
                }
                sortedTest.add(m);
            }
            if (m.isAnnotationPresent(BeforeSuit.class)) {
                beforeSuitCont++;
                if (beforeSuitCont > 1) {
                    throw new RuntimeException("В классе не должно быть более одного метода с аннотацией BeforeSuit");
                }
                before = m;
            }
            if (m.isAnnotationPresent(AfterSuit.class)) {
                afterSuitCount++;
                if (afterSuitCount > 1) {
                    throw new RuntimeException("В классе не должно быть более одного метода с аннотацией AfterSuit");
                }
                after = m;
            }
        }
        sortedTest.sort(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority();
            }
        });

        if (before != null) {
            sortedTest.add(0, before);
        }
        if (after != null) {
            sortedTest.add(after);
        }

        for (Method m : sortedTest) {
            try {
                m.invoke(null);
                System.out.println("Тест выполнен успешно!");
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}