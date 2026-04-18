package ru.job4j.checkexam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResult {
    private Long userId;
    private Long examId;
}
