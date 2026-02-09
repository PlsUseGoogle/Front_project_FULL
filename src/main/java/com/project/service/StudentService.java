package com.project.service;

import com.project.model.Student;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<Student> getAllStudents(Pageable pageable) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        RestResponsePage<Student> page = restClient.get()
                .uri(uriBuilder -> ServiceUtil.applySort(
                                uriBuilder.path(RESOURCE_PATH)
                                        .queryParam("page", pageable.getPageNumber())
                                        .queryParam("size", pageable.getPageSize()),
                                pageable.getSort())
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return page != null ? page : new RestResponsePage<>();
    }

    public Page<Student> searchStudents(String keyword, Pageable pageable) {
        if (keyword == null || keyword.isBlank()) {
            return getAllStudents(pageable);
        }

        RestClient restClient = restClientProvider.clientForCurrentUser();
        if (keyword.matches("^[0-9]+$")) {
            Student student = restClient.get()
                    .uri(RESOURCE_PATH + "/nrIndeksu/{nrIndeksu}", keyword)
                    .retrieve()
                    .body(Student.class);
            List<Student> content = student != null ? List.of(student) : List.of();
            return new PageImpl<>(content, pageable, content.size());
        }

        String param = keyword.contains("@") ? "email" : null;
        if (param != null) {
            return searchByParam(param, keyword, pageable);
        }

        Page<Student> byNazwisko = searchByParam("nazwisko", keyword, pageable);
        if (!byNazwisko.isEmpty()) {
            return byNazwisko;
        }
        return searchByParam("imie", keyword, pageable);
    }

    private Page<Student> searchByParam(String paramName, String value, Pageable pageable) {
        RestClient restClient = restClientProvider.clientForCurrentUser();
        RestResponsePage<Student> page = restClient.get()
                .uri(uriBuilder -> ServiceUtil.applySort(
                                uriBuilder.path(RESOURCE_PATH)
                                        .queryParam(paramName, value)
                                        .queryParam("page", pageable.getPageNumber())
                                        .queryParam("size", pageable.getPageSize()),
                                pageable.getSort())
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return page != null ? page : new RestResponsePage<>();
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
            List<ProjektRef> projekty = (student.getProjektIds() != null ? student.getProjektIds() : List.<Integer>of()).stream()
                    .filter(java.util.Objects::nonNull)
                    .distinct()
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
