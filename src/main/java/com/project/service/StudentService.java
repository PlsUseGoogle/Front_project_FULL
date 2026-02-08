package com.project.service;

import com.project.model.Student;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private static final String RESOURCE_PATH = "/studenci";

    private final ApiRestClientProvider restClientProvider;

    public StudentService(ApiRestClientProvider restClientProvider) {
        this.restClientProvider = restClientProvider;
    }

    public List<Student> getAllStudents() {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        RestResponsePage<Student> page = restClient.get()
                .uri(uriBuilder -> uriBuilder.path(RESOURCE_PATH)
                        .queryParam("page", 0)
                        .queryParam("size", 1000)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return page != null ? page.getContent() : List.of();
    }

    public List<Student> searchStudents(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getAllStudents();
        }

        RestClient restClient = restClientProvider.clientForCurrentUser();
        if (keyword.matches("^[0-9]+$")) {
            Student student = restClient.get()
                    .uri(RESOURCE_PATH + "/nrIndeksu/{nrIndeksu}", keyword)
                    .retrieve()
                    .body(Student.class);
            return student != null ? List.of(student) : List.of();
        }

        String param = keyword.contains("@") ? "email" : null;
        if (param != null) {
            return searchByParam(param, keyword);
        }

        List<Student> byNazwisko = searchByParam("nazwisko", keyword);
        List<Student> byImie = searchByParam("imie", keyword);

        if (byNazwisko.isEmpty() && byImie.isEmpty()) {
            return List.of();
        }

        return java.util.stream.Stream.concat(byNazwisko.stream(), byImie.stream())
                .collect(java.util.stream.Collectors.collectingAndThen(
                        java.util.stream.Collectors.toMap(Student::getStudentId, s -> s, (a, b) -> a),
                        map -> map.values().stream().toList()
                ));
    }

    private List<Student> searchByParam(String paramName, String value) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        RestResponsePage<Student> page = restClient.get()
                .uri(uriBuilder -> uriBuilder.path(RESOURCE_PATH)
                        .queryParam(paramName, value)
                        .queryParam("page", 0)
                        .queryParam("size", 1000)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return page != null ? page.getContent() : List.of();
    }

    public Optional<Student> getStudentById(Integer id) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        Student student = restClient.get()
                .uri(RESOURCE_PATH + "/{studentId}", id)
                .retrieve()
                .body(Student.class);
        return Optional.ofNullable(student);
    }

    public void saveStudent(Student student) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        StudentRequest payload = StudentRequest.fromStudent(student);
        if (student.getStudentId() == null) {
            restClient.post()
                    .uri(RESOURCE_PATH)
                    .body(payload)
                    .retrieve()
                    .toBodilessEntity();
        } else {
            restClient.put()
                    .uri(RESOURCE_PATH + "/{studentId}", student.getStudentId())
                    .body(payload)
                    .retrieve()
                    .toBodilessEntity();
        }
    }

    public void deleteStudent(Integer studentId) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        restClient.delete()
                .uri(RESOURCE_PATH + "/{studentId}", studentId)
                .retrieve()
                .toBodilessEntity();
    }

    public void registerStudent(Student student) {
        RestClient restClient = restClientProvider.clientForAnonymous();
        StudentRegistrationRequest payload = StudentRegistrationRequest.fromStudent(student);
        restClient.post()
                .uri("/register")
                .body(payload)
                .retrieve()
                .toBodilessEntity();
    }

    public Optional<Student> getCurrentStudent() {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        Student student = restClient.get()
                .uri(RESOURCE_PATH + "/me")
                .retrieve()
                .body(Student.class);
        return Optional.ofNullable(student);
    }

    public void updateCurrentStudent(Student student) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        StudentProfileRequest payload = StudentProfileRequest.fromStudent(student);
        restClient.put()
                .uri(RESOURCE_PATH + "/me")
                .body(payload)
                .retrieve()
                .toBodilessEntity();
    }

    private record StudentRequest(String imie, String nazwisko, String nrIndeksu,
                                  String email, boolean stacjonarny, String password,
                                  List<ProjektRef> projekty) {
        static StudentRequest fromStudent(Student student) {
            List<ProjektRef> projekty = student.getProjektIds().stream()
                    .map(ProjektRef::new)
                    .toList();
            return new StudentRequest(
                    student.getImie(),
                    student.getNazwisko(),
                    student.getNrIndeksu(),
                    student.getEmail(),
                    student.isStacjonarny(),
                    student.getPassword(),
                    projekty
            );
        }
    }

    private record ProjektRef(Integer projektId) {}

    private record StudentRegistrationRequest(String imie, String nazwisko, String nrIndeksu,
                                              String email, boolean stacjonarny, String password) {
        static StudentRegistrationRequest fromStudent(Student student) {
            return new StudentRegistrationRequest(
                    student.getImie(),
                    student.getNazwisko(),
                    student.getNrIndeksu(),
                    student.getEmail(),
                    student.isStacjonarny(),
                    student.getPassword()
            );
        }
    }

    private record StudentProfileRequest(String imie, String nazwisko, String nrIndeksu,
                                         String email, boolean stacjonarny, String password) {
        static StudentProfileRequest fromStudent(Student student) {
            String password = student.getPassword();
            if (password != null && password.isBlank()) {
                password = null;
            }
            return new StudentProfileRequest(
                    student.getImie(),
                    student.getNazwisko(),
                    student.getNrIndeksu(),
                    student.getEmail(),
                    student.isStacjonarny(),
                    password
            );
        }
    }
}
