package com.fion.reactor.demo.flatmap;

import com.fion.reactor.demo.entity.Course;
import com.fion.reactor.demo.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class FlatMapDemo {

    private List<Student> students;

    @Test
    public void flatMap() {
        StringBuilder consumer = new StringBuilder();

        Flux<Integer> flux = Flux.range(1, 20);
        Flux<Flux<Integer>> flux2 = flux.window(3);

        /**
         * 相当于flatMap将原二维的flux流降维了，成了一维flux流
         */
        Flux<Integer> flux3 = flux2.flatMap(f -> f);
        flux3.subscribe(v -> consumer.append(v).append(" "));
        log.info("[flat map flux] after faltMap, consumer = {}", consumer.toString());

        consumer.delete(0, consumer.length());

        List<Flux<Integer>> fluxList = new ArrayList<>();
        flux2.subscribe(v -> fluxList.add(v), null, null, subscription -> subscription.request(1));
        fluxList.get(0).subscribe(v -> consumer.append(v).append(" "));
        log.info("[flat map flux] fluxList[0], consumer = {}", consumer.toString());
    }

    @Test
    public void flatMapTest() {
        Flux<Student> studentFlux = Flux.fromIterable(students);
        studentFlux.flatMap(student -> Flux.fromIterable(student.getCourses()))
                .subscribe(course -> System.out.println("Course Name: " + course.getName() + ",\t\tCourse Score: " + course.getScore()));
    }

    @Before
    public void before() {
        students = new ArrayList<>();
        String[] studentNames = {"Fion", "Cheryl", "Lufy", "Nona", "Messi", "Jane"};
        for (int i = 0; i <studentNames.length; i++) {
            students.add(new Student(i + 1, studentNames[i], getCourses()));
        }
    }

    private List<Course> getCourses() {
        String[] courseNames = {"Java", "C++", "C", "C#", "Go", "Python"};

        List<Course> courses = new ArrayList<>();
        for (int i = 0; i <courseNames.length; i++) {
            courses.add(new Course(i + 1, courseNames[i], new Random().nextInt(100)));
        }
        return courses;
    }
}
