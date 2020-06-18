import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JavaLambda {
    List<String> names;

    @Test
    public void test1() {
        // write your code here
        names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        System.out.print(names);
    }

    /**
     * lambda表达式省略了return语句
     */
    @Test
    public void test2() {
        names = Arrays.asList("abc", "abcde", "abcdef", "abcdefg");
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        Collections.sort(names, (a, b) -> b.compareTo(a));
        System.out.print(names);
    }

    /**
     * 每一个lambda都能够通过一个特定的接口，与一个给定的类型进行匹配。
     * 一个所谓的函数式接口必须要有且仅有一个抽象方法声明。
     * 每个与之对应的lambda表达式必须要与抽象方法的声明相匹配。
     * 由于默认方法不是抽象的，因此你可以在你的函数式接口里任意添加默认方法。
     *
     * 任意只包含一个抽象方法的接口，我们都可以用来做成lambda表达式。
     * 为了让你定义的接口满足要求，你应当在接口前加上@FunctionalInterface 标注。
     * 编译器会注意到这个标注，如果你的接口中定义了第二个抽象方法的话，编译器会抛出异常。
     *
     * 总之，lambda表达式的基础 是 有且仅有一个抽象方法声明的接口
     */
    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    @Test
    public void test3() {
        /**
         * 这一步，创建了一个实现Converter接口的实例，
         * lambda表达式省略了方法的结构；
         * 只保留了方法最关键的两部分（参数）和（处理过程）；
         * 对于lambda,方法名称不是重点，要关注于方法的实现过程.
         *
         * 通过::进行类的静态方法的访问，（valueOf为静态方法），
         * 除了静态方法，还可以通过::关键字获取普通方法或者构造函数的的引用
         */
//        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123
    }

    class Person {
        String firstName;
        String lastName;

        Person() {}

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    @Test
    public void test4(){
        /**
         * 通过::实现对接口的实现，这里只关注实现的方法，
         * 没有表示出具体的实现类
         */
        PersonFactory<Person> personFactory = Person::new;

        /**
         * 通过Person::new来创建一个Person类构造函数的引用。
         * Java编译器会自动地选择合适的构造函数来匹配PersonFactory.create函数的签名，
         * 并选择正确的构造函数形式
         */
        Person person = personFactory.create("Peter", "Parker");
    }

    /**
     * 访问局部变量
     */
    @Test
    public void test5(){
        final int num = 1;
        int num2 = 2;
        /**
         * lambda表达式可以访问外部的局部变量
         * （无论是否有final修饰，都可以进行访问，但是num2在编译的时候被隐式地当做final变量来处理）
         */
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num2);
        /**
         * 这里不能再次操作num2,
         * 虽然这里num2没有被修饰为final，但是如果操作num2，
         * 那么lambda表达式会提示 variable used in lambda expression should be final or effectively final;
         *
         * 另外在lambda表达式内部企图改变num的值也是不允许的。
         */
//        !num2 = 3;
        String result = stringConverter.convert(2);     // 3
        System.out.println(result);
    }

    static int outerStaticNum;
    int outerNum;

    /**
     * 与局部变量不同，我们在lambda表达式的内部能获取到对成员变量或静态变量的读写权。
     *
     * 另外，带有方法体标识的中括号，如果实现的方法需要返回值，此时不能省略return关键字
     */
    @Test
    public void test6(){
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }

    interface Formula {
        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

    @Test
    public void test7(){
        /**
         * lambda表达式不能访问访问formula所有的对象实例，包括匿名对象
         */
        // ! Formula formula = (a) -> sqrt( a * 100);
    }
}
