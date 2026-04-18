package ru.job4j.checkexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExamResponseDTO {
    private Long clientId;
    private Long examId;
    private String examName;
}
