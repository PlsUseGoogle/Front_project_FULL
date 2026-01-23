package com.project.service;

import com.project.model.Student;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();

    public StudentService() {
        // !!! ИСПРАВЛЕНИЕ: Добавил пароль "123" в конце каждой строки !!!
        students.add(new Student(1, "Jan", "Kowalski", "12345", "jan@pbs.edu.pl", true, "123"));
        students.add(new Student(2, "Anna", "Nowak", "54321", "anna@pbs.edu.pl", false, "123"));
        students.add(new Student(3, "Piotr", "Wiśniewski", "99900", "piotr@pbs.edu.pl", true, "123"));
    }

    public List<Student> getAllStudents() {
        return students;
    }

    // Поиск по всем полям
    public List<Student> searchStudents(String keyword) {
        if (keyword == null || keyword.isEmpty()) return students;

        String lowerKw = keyword.toLowerCase();

        return students.stream()
                .filter(s -> s.getNazwisko().toLowerCase().contains(lowerKw) ||
                        s.getImie().toLowerCase().contains(lowerKw) ||
                        s.getNrIndeksu().contains(keyword) ||
                        s.getEmail().toLowerCase().contains(lowerKw))
                .collect(Collectors.toList());
    }

    public Optional<Student> getStudentById(Integer id) {
        return students.stream().filter(s -> s.getStudentId().equals(id)).findFirst();
    }

    // Метод для сохранения нового студента
    public void saveStudent(Student student) {
        // Если ID нет, генерируем новый
        if (student.getStudentId() == null) {
            student.setStudentId(students.size() + 1);
        }
        // Если пароль не указали (например, добавил админ), ставим "123" по умолчанию
        if (student.getPassword() == null || student.getPassword().isEmpty()) {
            student.setPassword("123");
        }
        students.add(student);
    }
}