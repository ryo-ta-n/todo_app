package com.example.todoapp.controller;

import com.example.todoapp.model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {

    private List<Todo> todos = new ArrayList<>();
    private Long idCounter = 1L;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todos);
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@RequestParam String title) {
        todos.add(new Todo(idCounter++, title, false));
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTodo(@RequestParam Long id) {
        todos.removeIf(todo -> todo.getId().equals(id));
        return "redirect:/";
    }

    @PostMapping("/toggle")
    public String toggleTodo(@RequestParam Long id) {
        for (Todo todo : todos) {
            if (todo.getId().equals(id)) {
                todo.setCompleted(!todo.isCompleted());
                break;
            }
        }
        return "redirect:/";
    }

}
