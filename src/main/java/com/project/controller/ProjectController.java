package com.project.controller;

import com.project.model.Projekt;
import com.project.model.Student;
import com.project.model.Zadanie;
import com.project.service.ProjektService;
import com.project.service.StudentService;
import com.project.service.ZadanieService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProjectController {

    private final ProjektService projektService;
    private final StudentService studentService;
    private final ZadanieService zadanieService;
    // !!! Добавили Менеджера пользователей
    private final InMemoryUserDetailsManager userDetailsManager;

    public ProjectController(ProjektService projektService,
                             StudentService studentService,
                             ZadanieService zadanieService,
                             InMemoryUserDetailsManager userDetailsManager) {
        this.projektService = projektService;
        this.studentService = studentService;
        this.zadanieService = zadanieService;
        this.userDetailsManager = userDetailsManager;
    }

    // --- ЛОГИН / ГЛАВНАЯ ---
    @GetMapping("/")
    public String home() { return "index"; }

    @GetMapping({"/index", "/index.html"})
    public String index() { return "index"; }

    // --- РЕГИСТРАЦИЯ (НОВЫЙ МЕТОД) ---
    @PostMapping("/register")
    public String registerUser(Student student) {
        // Создаем пользователя в системе безопасности
        // Берем email как логин, и password как пароль
        userDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username(student.getEmail()) // Логином будет Email
                        .password(student.getPassword()) // Паролем то, что ввели
                        .roles("USER")
                        .build()
        );
        // После регистрации перекидываем на логин (index)
        return "redirect:/index";
    }

    @GetMapping("/register.html")
    public String registerPage(Model model) {
        model.addAttribute("student", new Student()); // Пустой объект для формы
        return "register";
    }

    // --- ПРОЕКТЫ ---
    @GetMapping("/projektList")
    public String listProjekty(Model model, Pageable pageable) {
        model.addAttribute("projekty", projektService.getProjekty(pageable).getContent());
        return "projekt";
    }

    @GetMapping("/projekt.html")
    public String projektRedirect() { return "redirect:/projektList"; }

    @GetMapping("/projektEdit")
    public String editProjekt(@RequestParam(required = false) Integer projektId, Model model) {
        model.addAttribute("projekt", projektId != null ? projektService.getProjekt(projektId).orElse(new Projekt()) : new Projekt());
        return "project-edit";
    }

    // --- СТУДЕНТЫ ---
    @GetMapping("/studentList")
    public String listStudentow(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("studenci", studentService.searchStudents(keyword));
        return "student";
    }

    @GetMapping("/student.html")
    public String studentRedirect() { return "redirect:/studentList"; }

    // --- ЗАДАЧИ ---
    @GetMapping("/zadanieList")
    public String listZadania(Model model) {
        model.addAttribute("zadania", zadanieService.getAllZadania());
        model.addAttribute("studentService", studentService);
        return "zadanie";
    }

    @GetMapping("/zadanie.html")
    public String zadanieRedirect() { return "redirect:/zadanieList"; }

    @GetMapping("/zadanieEdit")
    public String editZadanie(@RequestParam(required = false) Integer zadanieId, Model model) {
        Zadanie zadanie = (zadanieId != null)
                ? zadanieService.getZadanie(zadanieId).orElse(new Zadanie())
                : new Zadanie();
        model.addAttribute("zadanie", zadanie);
        model.addAttribute("listaStudentow", studentService.getAllStudents());
        return "zadanie-edit";
    }

    @PostMapping("/zadanieSave")
    public String saveZadanie(Zadanie zadanie) {
        zadanieService.saveZadanie(zadanie);
        return "redirect:/zadanieList";
    }

    @GetMapping("/zadanieDelete")
    public String deleteZadanie(@RequestParam Integer zadanieId) {
        zadanieService.deleteZadanie(zadanieId);
        return "redirect:/zadanieList";
    }
}