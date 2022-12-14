import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_0; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }
        persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        List<Person> conscript = persons.stream().toList();
        persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
                .filter(person -> person.getSex() == Sex.MAN)
                .map(Person :: getFamily)
                .collect(Collectors.toList());

        Predicate<Person> selectionForMen = p-> p.getSex() == Sex.MAN && p.getAge() < 65;
        Predicate<Person> selectionForWoman = p-> p.getSex() == Sex.WOMAN && p.getAge() < 60;
        List<Person> workSheep = persons.stream().toList();
        persons.stream()
                .filter(p -> p.getAge() >=18)
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(selectionForMen.or(selectionForWoman))
                .sorted(Comparator.comparing(Person:: getFamily))
                .collect(Collectors.toList());
    }
}
