package ru.job4j.checkexam.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.checkexam.dto.ExamResponseDTO;
import ru.job4j.checkexam.service.UserExamService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/exam")
public class ExamController {
    private final UserExamService userExamService;

    @GetMapping
    public ResponseEntity<List<ExamResponseDTO>> findAll() {
        return ResponseEntity.ok(userExamService.findAll());
    }
}
