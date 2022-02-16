package com.will;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
public class SpringBootApplicationPeople {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationPeople.class, args);
    }

    // All your code should be inside this one class
    // add @RestController to you expose methods as REST services to clients
    // create a static List of type person with few people inside
    // create a class called Person with following properties: id, name, age


    // test your api using chrome

    static List<Person> people = Arrays.asList(
            new Person(1, "Will", 26),
            new Person(2, "Darshil", 23),
            new Person(3, "Alex", 24),
            new Person(4, "Wendy", 22));

    // implement getPersonById
    /*
     @GetMapping("people/{id}")
     public Person getPersonById(@PathVariable("id") Integer id) {
         // filter list and return person that matches id otherwise return null
     }
  */
    @GetMapping(path = "peoples/{id}") // the curly braces mean that the value between is not fixed
    Person getPersonById(@PathVariable("id") int id){
//        for (Person person : people) {
//            if (person.getId() == id){
//                return person;
//            }
//        }
//        return null;
        return  people.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);


    }
    // create another method that returns all people using @GetMapping("people")
    @GetMapping(path = "peoples")
    List<Person> getAllPeople(){
        return people;
    }

    static class Person{
        private int id;
        private String name;
        private int age;

        public Person() {
        }

        public Person(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return id == person.id && age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, age);
        }
    }
}
