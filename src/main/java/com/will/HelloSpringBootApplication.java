package com.will;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@SpringBootApplication // this annotation starts up the web server (VERY IMPORTANT)
// any methods we have, we can tell it to serve any of our verbs
public class HelloSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringBootApplication.class, args);
		// this is how you run it ('args' are command-line arguments
	}
	// localhost:8080


	//--------------------------------------
	//               GET

	// method we expose as a get request
	@GetMapping
	String greet(){
		return "Hello Will";
	}

	@GetMapping(params = "name") // this means it's an optional parameter
	String greetName(@RequestParam ("name") String name){ // this says give me the value of "name" and store it as name.
		return "Hello " + name + "! How are you today?";
	}
	// http://localhost:8080/?name=foo

	// localhost:8080/ping
	@GetMapping(path = "ping") // you cannot duplicate this "ping" path
	String ping(){
		return "pong";
	}

	@GetMapping(path = "people")
	List<Person> getPerson(){
		return List.of(
				new Person("Alex", 24, true, List.of("burgers", "wings")),
				new Person("Wendy", 22, true, List.of("pizza", "cake"))
		);
	}
	// http://localhost:8080/people
	//  return key/value pairs

    //--------------------------------------
	//              POST

	// JSON is a collection of key value pairs. {"name" : "will", "age": 26}
	@PostMapping(path = "people") // we can use this path as it's a different HTTP method (POST instead of GET)
	void addPerson(@Valid @RequestBody Person person){ // most important data to send is in the body - the 'payload'
		// @RequestBody is telling our method to grab the body from the HTTP request
		// @Valid means it must be a valid Person (the data types must match up)
		System.out.println(person);
	}
	//--------------------------------------





	static class Person{
		@NotNull
		private String name;
		private int age;
		private Boolean isAdult;
		private List<String> favouriteFood;

		public Person(String name, int age, Boolean isAdult, List<String> favouriteFood) {
			this.name = name;
			this.age = age;
			this.isAdult = isAdult;
			this.favouriteFood = favouriteFood;
		}

		public Person() {
		}

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
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

		public Boolean getAdult() {
			return isAdult;
		}

		public void setAdult(Boolean adult) {
			isAdult = adult;
		}

		public List<String> getFavouriteFood() {
			return favouriteFood;
		}

		public void setFavouriteFood(List<String> favouriteFood) {
			this.favouriteFood = favouriteFood;
		}

		@Override
		public String toString() {
			return "Person{" +
					"name='" + name + '\'' +
					", age=" + age +
					", isAdult=" + isAdult +
					", favouriteFood=" + favouriteFood +
					'}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Person person = (Person) o;
			return age == person.age && Objects.equals(name, person.name) && Objects.equals(isAdult, person.isAdult) && Objects.equals(favouriteFood, person.favouriteFood);
		}

		@Override
		public int hashCode() {
			return Objects.hash(name, age, isAdult, favouriteFood);
		}
	}
}
