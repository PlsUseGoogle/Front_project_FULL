package com.project.controller;

import com.project.model.Projekt;
import com.project.model.Student;
import com.project.model.Zadanie;
import com.project.service.ProjektService;
import com.project.service.StudentService;
import com.project.service.ZadanieService;
import org.springframework.data.domain.Pageable;
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

    public ProjectController(ProjektService projektService,
                             StudentService studentService,
                             ZadanieService zadanieService) {
        this.projektService = projektService;
        this.studentService = studentService;
        this.zadanieService = zadanieService;
    }

    // --- ЛОГИН / ГЛАВНАЯ ---
    @GetMapping("/")
    public String home() { return "index"; }

    @GetMapping({"/index", "/index.html"})
    public String index() { return "index"; }

    // --- РЕГИСТРАЦИЯ (НОВЫЙ МЕТОД) ---
    @PostMapping("/register")
    public String registerUser(Student student) {
        studentService.registerStudent(student);
        return "redirect:/index";
    }

    @GetMapping("/register.html")
    public String registerPage(Model model) {
        model.addAttribute("student", new Student()); // Пустой объект для формы
        return "register";
    }

    // --- ПРОЕКТЫ ---
    @GetMapping("/projektList")
    public String listProjekty(@RequestParam(required = false) String nazwa, Model model, Pageable pageable) {
        if (nazwa != null && !nazwa.isBlank()) {
            model.addAttribute("projekty", projektService.searchByNazwa(nazwa, pageable).getContent());
        } else {
            model.addAttribute("projekty", projektService.getProjekty(pageable).getContent());
        }
        return "projekt";
    }

    @GetMapping("/projekt.html")
    public String projektRedirect() { return "redirect:/projektList"; }

    @GetMapping("/projektEdit")
    public String editProjekt(@RequestParam(required = false) Integer projektId,
                              @RequestParam(required = false) String delete,
                              Model model) {
        if (projektId != null && "true".equalsIgnoreCase(delete)) {
            projektService.deleteProjekt(projektId);
            return "redirect:/projektList";
        }
        model.addAttribute("projekt", projektId != null ? projektService.getProjekt(projektId).orElse(new Projekt()) : new Projekt());
        return "project-edit";
    }
    @PostMapping("/projektEdit")
    public String saveProjekt(Projekt projekt) {
        projektService.setProjekt(projekt);
        return "redirect:/projektList";
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
        model.addAttribute("listaProjektow", projektService.getProjekty(Pageable.ofSize(1000)).getContent());
        if (zadanie.getProjekt() == null) {
            zadanie.setProjekt(new Projekt());
        }
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

    // --- ДОБАВЛЕНИЕ СТУДЕНТА (АДМИН) ---

    @GetMapping("/studentAdd")
    public String addStudentPage(Model model) {
        model.addAttribute("student", new Student()); // Пустой объект для формы
        model.addAttribute("listaProjektow", projektService.getProjekty(Pageable.ofSize(1000)).getContent());
        model.addAttribute("formAction", "/studentSave");
        model.addAttribute("selfEdit", false);
        return "student-add"; // Имя нового HTML файла
    }

    @GetMapping("/studentEdit")
    public String editStudentPage(@RequestParam Integer studentId, Model model) {
        Student student = studentService.getStudentById(studentId).orElse(new Student());
        model.addAttribute("student", student);
        model.addAttribute("listaProjektow", projektService.getProjekty(Pageable.ofSize(1000)).getContent());
        model.addAttribute("formAction", "/studentSave");
        model.addAttribute("selfEdit", false);
        return "student-add";
    }

    @PostMapping("/studentSave")
    public String saveStudentFromForm(Student student) {
        studentService.saveStudent(student);
        return "redirect:/studentList";
    }

    @GetMapping("/studentProfile")
    public String studentProfile(Model model) {
        Student student = studentService.getCurrentStudent().orElse(new Student());
        model.addAttribute("student", student);
        model.addAttribute("formAction", "/studentProfileSave");
        model.addAttribute("selfEdit", true);
        return "student-add";
    }

    @PostMapping("/studentProfileSave")
    public String saveStudentProfile(Student student) {
        studentService.updateCurrentStudent(student);
        return "redirect:/projektList";
    }
}
