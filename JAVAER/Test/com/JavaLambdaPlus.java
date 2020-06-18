import org.junit.jupiter.api.Test;

import java.lang.annotation.Repeatable;
import java.time.Clock;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class JavaLambdaPlus {

    class Person {
        String firstName;
        String lastName;

        Person() {
        }

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    /**
     * Predicate是一个布尔类型的函数，该函数只有一个输入参数。
     * Predicate接口包含了多种默认方法，用于处理复杂的逻辑动词（and, or，negate）
     */
    @Test
    public void test1() {
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    /**
     * Function接口接收一个参数，并返回单一的结果。默认方法可以将多个函数串在一起（compse, andThen）
     */
    @Test
    public void test2() {
        Function<String, Integer> toInteger = Integer::valueOf;
        //还有这么随心所欲的写法，跪了
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");     // "123"
    }

    @Test
    public void test3() {

        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person


        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");

        comparator.compare(p1, p2);             // > 0
        comparator.reversed().compare(p1, p2);  // < 0
    }

    /**
     * Optional不是一个函数式接口，而是一个精巧的工具接口，用来防止NullPointerEception产生。
     * 这个概念在下一节会显得很重要，所以我们在这里快速地浏览一下Optional的工作原理。
     * <p>
     * Optional是一个简单的值容器，这个值可以是null，也可以是non-null。
     * 考虑到一个方法可能会返回一个non-null的值，也可能返回一个空值。
     * 为了不直接返回null，我们在Java 8中就返回一个Optional.
     */
    @Test
    public void test4() {
        Optional<String> optional = Optional.of("bam");
        optional.isPresent();           //  true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    }

    /**
     * Stream操作可以是中间操作，也可以是完结操作。
     * 完结操作会返回一个某种类型的值，而中间操作会返回流对象本身，
     * 并且你可以通过多次调用同一个流操作方法来将操作结果串起来
     */
    @Test
    public void test5() {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection
                .stream()
                .map(String::toUpperCase)
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
    }

    @Test
    public void test6() {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    /**
     * 通过Person::new来创建一个Person类构造函数的引用。<br/>
     * Java编译器会自动地选择合适的构造函数来匹配PersonFactory.create函数的签名，<br/>
     * 并选择正确的构造函数形式。
     */
    @Test
    public void test7() {
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
    }

    @Test
    public void test8() {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        if (true) {
            long t0 = System.nanoTime();
            long count = values.parallelStream().sorted().count();
            System.out.println(count);
            long t1 = System.nanoTime();
            long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
            System.out.println(String.format("parallel sort took: %d ms", millis));
        } else {
            long t0 = System.nanoTime();
            long count = values.stream().sorted().count();
            System.out.println(count);
            long t1 = System.nanoTime();
            long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
            System.out.println(String.format("sequential sort took: %d ms", millis));
        }
    }

    @Test
    public void test9(){
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
//        map.forEach((id, val) -> System.out.println(val));


        map.computeIfPresent(3, (num, val) -> val + num);
        map.get(3);             // val33

        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false

        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true

        map.computeIfAbsent(3, num -> "bam");
        map.get(3);             // val33
    }

    @Test
    public void test10(){
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);

        System.out.println("millis:"+millis);
    }

    @interface Hints {
        Hint[] value();
    }

    @Repeatable(Hints.class)
    @interface Hint {
        String value();
    }
}
