package ru.job4j.checkexam.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.checkexam.dto.ExamResponseDTO;
import ru.job4j.checkexam.model.UserResult;
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

    @PostMapping
    ResponseEntity<UserResult> save(@RequestBody UserResult userResult) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userExamService.save(userResult));
    }
}
