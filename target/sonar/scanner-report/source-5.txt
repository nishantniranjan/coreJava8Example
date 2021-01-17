import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Person {
    int id;
    String name;
    String city;
    double salary;
    public static String getCity1(Person person){
        return person.getCity();
    }

}
