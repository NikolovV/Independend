package _18_CombiningDataStructures;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class PersonCollectionImpl implements PersonCollection {

    private final Map<String, Person> personsByEmail;
    private final Map<String, Set<Person>> personsByDomain;
    private final Map<Pair<String, String>, Set<Person>> personsByNameAndTown;
    private final TreeMap<Integer, Set<Person>> personsByAge;
    private final TreeMap<Integer, TreeMap<String, Set<Person>>> personsByAgeAndTown;

    public PersonCollectionImpl() {
        this.personsByEmail = new HashMap<>();
        this.personsByDomain = new HashMap<>();
        this.personsByNameAndTown = new HashMap<>();
        this.personsByAge = new TreeMap<>();
        this.personsByAgeAndTown = new TreeMap<>();
    }

    @Override
    public boolean addPerson(String email, String name, int age, String town) {
        if (this.findPerson(email) != null) {
            return false;
        }

        Person person = new Person(email, name, age, town);
        this.personsByEmail.put(email, person);

        String domain = getDomain(email);
        this.personsByDomain.putIfAbsent(domain, new TreeSet<>());
        this.personsByDomain.get(domain).add(person);

        Pair<String, String> nameAndTown = new Pair<>(name, town);
        this.personsByNameAndTown.putIfAbsent(nameAndTown, new TreeSet<>());
        this.personsByNameAndTown.get(nameAndTown).add(person);

        this.personsByAge.putIfAbsent(age, new TreeSet<>());
        this.personsByAge.get(age).add(person);

        this.personsByAgeAndTown.putIfAbsent(age, new TreeMap<>());
        this.personsByAgeAndTown.get(age).putIfAbsent(town, new TreeSet<>());
        this.personsByAgeAndTown.get(age).get(town).add(person);

        return true;
    }

    @Override
    public int getCount() {
        return this.personsByEmail.size();
    }

    @Override
    public Person findPerson(String email) {
        return this.personsByEmail.get(email);
    }

    @Override
    public boolean deletePerson(String email) {
        Person person = this.findPerson(email);

        if (person == null) {
            return false;
        }

        this.personsByEmail.remove(email);

        String domain = getDomain(email);
        this.personsByDomain.get(domain).remove(person);

        Pair<String, String> nameAndTown = new Pair<>(person.getName(), person.getTown());
        this.personsByNameAndTown.get(nameAndTown).remove(person);

        int age = person.getAge();
        String town = person.getTown();
        this.personsByAge.get(age).remove(person);
        this.personsByAgeAndTown.get(age).get(town).remove(person);

        return true;
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {
        if (!this.personsByDomain.containsKey(emailDomain)) {
            return new TreeSet<>();
        }

        return this.personsByDomain.get(emailDomain);
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {
        Pair<String, String> nameAndTown = new Pair<>(name, town);

        if (!this.personsByNameAndTown.containsKey(nameAndTown)) {
            return new TreeSet<>();
        }

        return this.personsByNameAndTown.get(nameAndTown);
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {
        return this.personsByAge.entrySet()
                .stream()
                .filter(x -> x.getKey() >= startAge &&
                        x.getKey() <= endAge)
                .flatMap(x -> x.getValue().stream())
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {
        return this.personsByAgeAndTown.entrySet()
                .stream()
                .filter(x -> x.getKey() >= startAge &&
                        x.getKey() <= endAge &&
                        x.getValue().containsKey(town))
                .flatMap(x -> x.getValue().values().stream().flatMap(Collection::stream))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private static String getDomain(String email) {
        return email.split("@")[1];
    }
}
