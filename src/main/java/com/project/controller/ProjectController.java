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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // --- LOGOWANIE / GŁÓWNA ---
    @GetMapping("/")
    public String home() { return "index"; }

    @GetMapping({"/index", "/index.html"})
    public String index() { return "index"; }

    // --- REJESTRACJA ---
    @PostMapping("/register")
    public String registerUser(Student student) {
        studentService.registerStudent(student);
        return "redirect:/index";
    }

    @GetMapping({"/register", "/register.html"})
    public String registerPage(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    // --- PROJEKTY ---
    @GetMapping("/projektList")
    public String listProjekty(@RequestParam(required = false) String nazwa, Model model, Pageable pageable) {
        List<Projekt> projekty;
        org.springframework.data.domain.Page<Projekt> pageProjekty;
        if (nazwa != null && !nazwa.isBlank()) {
            pageProjekty = projektService.searchByNazwa(nazwa, pageable);
        } else {
            pageProjekty = projektService.getProjekty(pageable);
        }
        projekty = pageProjekty.getContent();
        Map<Integer, Long> zadaniaCountByProjektId = new HashMap<>();
        for (Projekt projekt : projekty) {
            if (projekt.getProjektId() != null) {
                long count = zadanieService.countZadaniaForProjekt(projekt.getProjektId());
                zadaniaCountByProjektId.put(projekt.getProjektId(), count);
            }
        }
        model.addAttribute("projekty", projekty);
        model.addAttribute("zadaniaCountByProjektId", zadaniaCountByProjektId);
        model.addAttribute("pageProjekty", pageProjekty);
        model.addAttribute("nazwa", nazwa);
        model.addAttribute("currentPage", pageProjekty.getNumber());
        model.addAttribute("totalPages", pageProjekty.getTotalPages());
        model.addAttribute("pageSize", pageProjekty.getSize());
        setSortAttributes(model, pageable.getSort());
        return "projekt";
    }

    @GetMapping("/projekt.html")
    public String projektRedirect() { return "redirect:/projektList"; }

    // formularz edycji / dodawania projektu (GET)
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

    @GetMapping("/projektView")
    public String viewProjekt(@RequestParam Integer projektId, Model model, Pageable pageable) {
        Projekt projekt = projektService.getProjekt(projektId).orElse(new Projekt());
        var pageZadania = zadanieService.getZadaniaByProjekt(projektId, pageable);
        model.addAttribute("projekt", projekt);
        model.addAttribute("zadania", pageZadania.getContent());
        model.addAttribute("pageZadania", pageZadania);
        model.addAttribute("currentPage", pageZadania.getNumber());
        model.addAttribute("totalPages", pageZadania.getTotalPages());
        model.addAttribute("pageSize", pageZadania.getSize());
        setSortAttributes(model, pageable.getSort());
        return "projekt-view";
    }
    @PostMapping("/projektEdit")
    public String saveProjekt(Projekt projekt) {
        projektService.setProjekt(projekt);
        return "redirect:/projektList";
    }

    // --- СТУДЕНТЫ ---
    @GetMapping("/studentList")
    public String listStudentow(@RequestParam(required = false) String keyword, Model model, Pageable pageable) {
        var page = studentService.searchStudents(keyword, pageable);
        model.addAttribute("studenci", page.getContent());
        model.addAttribute("pageStudenci", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", page.getSize());
        setSortAttributes(model, pageable.getSort());
        return "student";
    }

    @GetMapping("/student.html")
    public String studentRedirect() { return "redirect:/studentList"; }

    // --- ZADANIA ---
    @GetMapping("/zadanieList")
    public String listZadania(Model model, Pageable pageable) {
        var page = zadanieService.getZadaniaPage(pageable);
        model.addAttribute("zadania", page.getContent());
        model.addAttribute("pageZadania", page);
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", page.getSize());
        setSortAttributes(model, pageable.getSort());
        return "zadanie";
    }

    @GetMapping("/zadanie.html")
    public String zadanieRedirect() { return "redirect:/zadanieList"; }

    @GetMapping("/zadanieEdit")
    public String editZadanie(@RequestParam(name = "zadanieId", required = false) Integer zadanieId,
                              Model model) {
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

    // --- DODAWANIE STUDENTA (ADMIN) ---
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

    @GetMapping("/studentDelete")
    public String deleteStudent(@RequestParam Integer studentId) {
        studentService.deleteStudent(studentId);
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

    private void setSortAttributes(Model model, org.springframework.data.domain.Sort sort) {
        String sortField = "";
        String sortDir = "";
        String sortParam = null;
        if (sort != null && sort.isSorted()) {
            var order = sort.iterator().next();
            sortField = order.getProperty();
            sortDir = order.getDirection().name().toLowerCase();
            sortParam = sortField + "," + sortDir;
        }
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("sortParam", sortParam);
    }
}
