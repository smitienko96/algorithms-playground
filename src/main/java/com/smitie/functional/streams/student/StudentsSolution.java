package com.smitie.functional.streams.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

public class StudentsSolution {

    private final List<Student> students;

    public StudentsSolution() {
        students = new ArrayList<>();
    }

    Student getByName(String name) {
        return students.stream().filter(s -> s.getName().equals(name)).findFirst().orElseThrow(NoSuchElementException::new);
    }

    List<Student> filterByAddress(String zipCode) {
        return students.stream().filter(s -> s.getAddress().getZipcode().equals(zipCode)).collect(toList());
    }

    List<Student> byPhoneNumber(Predicate<Student> number) {
        return students.stream()
                .filter(number)
                .collect(toList());
    }

    List<Student> fromTemp(List<TempStudent> tempStudents) {
        return tempStudents.stream()
                .map(toStudent)
                .collect(toList());
    }

    Map<String, List<Student>> groupByFilter(List<Student> students, Predicate<Student> studentFilter) {
        return students.stream()
                .collect(groupingBy(Student::getName, filtering(studentFilter, toList())));
    }


    List<String> names(List<Student> students) {
        return students.stream()
                .map(Student::getName)
                .collect(toList());
    }

    BiPredicate<Student, Predicate<String>> numberPredicate = (s, numberPredicate) -> s.getMobileNumbers()
            .stream()
            .map(MobileNumber::getNumber)
            .anyMatch(numberPredicate);


    private final Function<TempStudent, Student> toStudent = temp -> new Student(temp.name, temp.age, temp.address, new ArrayList<>(temp.mobileNumbers));
}
