package ru.job4j.checkexam.service;

import ru.job4j.checkexam.dto.ExamResponseDTO;

import java.util.List;

public interface UserExamService {
    List<ExamResponseDTO> findAll();
}
