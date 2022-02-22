package Java3_HW_7;

public class ForTest {

    @BeforeSuit
    public static void Test1() {
        System.out.println("Тест 1");
    }

    @Test(priority = 10)
    public static void Test2() {
        System.out.println("Тест 2");
    }

    @Test(priority = 9)
    public static void Test3() {
        System.out.println("Тест 3");
    }

    @Test(priority = 8)
    private static void Test4() {
        System.out.println("Тест 4");
    }

    @Test(priority = 7)
    public static void Test5() {
        System.out.println("Тест 5");
    }

    @Test(priority = 6)
    public static void Test6() {
        System.out.println("Тест 6");
    }

    @Test(priority = 5)
    public static void Test7() {
        System.out.println("Тест 7");
    }

    @Test(priority = 4)
    public static void Test8() {
        System.out.println("Тест 8");
    }

    @Test(priority = 3)
    private static void Test9() {
        System.out.println("Тест 9");
    }

    @AfterSuit
    public static void Test10() {
        System.out.println("Тест 10");
    }
}