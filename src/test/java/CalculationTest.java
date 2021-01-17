import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculationTest {


    private Integer[] numbers;
    private int max =-999;
    private int min = 999;


    @BeforeAll
    void populate(){
        numbers = new Integer[]{1,4,2,8,5,3};
    }

    @Test
    void findMax(){
        Calculation<Integer> maxCalculation = (numbers, predicate)->{


            Arrays.asList(numbers).stream().forEach(no->{
                if(predicate.test(no)){
                    max = no;
                }
            });
            return max;
        };
        assertEquals(8,maxCalculation.execute(numbers,(no)->{
            return max < Integer.valueOf(no.toString());
        }));

    }

    @Test
    public void findMin(){
        Calculation<Integer> maxCalculation = (numbers, predicate)->{

            for(int num:numbers){
                if(predicate.test(num) ){
                    min = num;
                }
            }
            return min;
        };
        assertEquals(1,maxCalculation.execute(numbers, (num)->{return min > Integer.parseInt(num.toString());}));

    }

    @Test
    public void findMaxNum() {
        CalculationStream<Integer> maxCal = (numbers) -> {
            Optional<Integer> res = Arrays.stream(numbers).max((a, b) -> {
                if (a < b) {
                    return -1;
                } else if (a == b) {
                    return 0;
                } else return 1;
            });


            return res.isPresent()?res.get():-999;
        };
        assertEquals(8, maxCal.execute(numbers), ()->"Max should be 8");
    }

    @Test
    public void findMinNum() {
        CalculationStream<Integer> maxCal = (numbers) -> {
            Optional<Integer> res = Arrays.stream(numbers).min((a, b) -> {
                if (a < b) {
                    return -1;
                } else if (a == b) {
                    return 0;
                } else return 1;
            });


            return res.isPresent()?res.get():999;
        };
        assertEquals(1, maxCal.execute(numbers), ()->"Max should be 1");
    }

    @Test
    public void findEvenNumbers() {
         List<Integer> nums = Arrays.stream(numbers).filter(no -> {
            if (no % 2 == 0) {
                return true;
            }
            return false;
        }).sorted().collect(Collectors.toList());
         assertEquals(Arrays.asList(2, 4, 8),nums );

    }

    @Test
    public void findAddedNumbers() {
        int num = Arrays.stream(numbers).reduce(0, (a,b)->a+b);
        assertEquals(23, num);
    }

    @Test
    public void findAvgNumbers() {
        int num = Arrays.stream(numbers).reduce(1, (a,b)->a*b);
        assertEquals(960, num);
    }

    @Test
     void getterTest(){
        Person employee = new Person(1, "Nishant Niranjan", "Pune",1000000);
        Supplier<String> consumer = employee::getCity;
        assertEquals("Pune",consumer.get());
        Function<Person, String> getterCity = Person::getCity1;
        assertEquals("Pune",getterCity.apply(employee));
        Comparator<Person> comparator = Comparator.comparing(Person::getName);
        List<Person> persons = Arrays.asList(new Person (1, "Nishant", "Pune", 1200000),
                new Person(2, "Niranjan", "Pune", 1300000),
                new Person(3, "Tonu", "Lucknow", 6000000));
        persons.sort(
                comparator
        );
        System.out.println("________________results begins_______________________");
        persons.stream().map(Person::getCity).distinct().forEach(System.out::println);
        System.out.println("________________results ends");
        Consumer personPrinter = System.out::println;;
        personPrinter.accept(persons);
        persons.stream().filter(e->e.getSalary()>1200000).map(Person::getName).sorted().forEach(
                System.out::println
        );
        Stream<Person> personStream =Stream.of(new Person (1, "Nishant", "Pune", 1200000),
                new Person(2, "Niranjan", "Pune", 1300000),
                new Person(3, "Tonu", "Lucknow", 6000000));
        Person person = new Person(1, "a", "ab",1000);
        Stream<Person> personStream1 = Stream.iterate(person, person1->new Person(person1.getId()+1,
                person1.getName()+"a", person1.getCity(),person1.getSalary()+1000));
        personStream1.limit(5).forEach(System.out::println);
        Random random = new Random();
        Stream <Integer> integerStream = Stream.generate(()-> {
            int num = random.nextInt(100);
            System.out.println("num::"+num);
            return num;
        });
        integerStream.filter(n->n>50).limit(5).forEach(System.out::println);
        //integerStream.limit(5).sorted().forEach(System.out::println);
        System.out.println("--------------result-------------");
        Stream.generate(()->random.nextInt(100)).limit(100).sorted().dropWhile(n->n>30).forEach(System.out::println);

        Stream<Person> personStream2 = Stream.of(new Person (1, "Nishant", "Pune", 1200000),
                new Person(2, "Niranjan", "Pune", 1300000),
                new Person(3, "Tonu", "Lucknow", 6000000),
                new Person(4, "T1", "Lucknow1", 4000000),
                new Person(5, "T2", "Lucknow2", 3000000));
        System.out.println("----------------4th Result Begins-----------------------------------------");
        String[] names = personStream2.sorted(Comparator.comparingDouble(Person::getSalary).reversed()).limit(3).map(Person::getName).peek(System.out::println).toArray(String[]::new);
        System.out.println("----------------4th Result Ends-----------------------------------------");



    }
    @Test
    public void testFlatMap(){
        String[][] data = new String[][]{{"a","b"},{"c"},{"d","e"},{"f","g"}};
        Stream<String[]> dataSteam = Arrays.stream(data);
        dataSteam.flatMap(x->Arrays.stream(x)).forEach(System.out::print);
        //dataSteam.forEach(System.out::println);
        int[] nums = new int[]{1,3,2,5,6,4,0,9,8,8,7};
        Stream.of(nums).flatMapToInt(s->Arrays.stream(s)).sorted().distinct().forEach(System.out::println);
    }
}



