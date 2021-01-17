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
public class PersonTest {
    Person person;

    @Test
    public void testGet(){
        person = new Person();
        person.setCity("Lko");
        person.setName("Nishant");
        assertEquals("Lko", person.getCity());
        assertEquals("Nishant", person.getName());

    }
}
