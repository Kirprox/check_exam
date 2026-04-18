package ru.job4j.checkexam.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.checkexam.dto.ExamResponseDTO;
import ru.job4j.checkexam.model.Exam;
import ru.job4j.checkexam.model.UserResult;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SimpleUserExamService implements UserExamService {
    @Value("${desc-api-url}")
    private String url;

    private final RestTemplate client;

    public SimpleUserExamService(RestTemplate client) {
        this.client = client;
    }

    private final Map<Long, UserResult> userResultMap = Map.of(
            1L, new UserResult(1L, 1L),
            2L, new UserResult(1L, 2L),
            3L, new UserResult(2L, 3L),
            4L, new UserResult(2L, 4L)
    );

    @Override
    public List<ExamResponseDTO> findAll() {
        List<Exam> examList = client.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Exam>>() {
                }).getBody();

        if (examList == null) {
            throw new NoSuchElementException("ошибка при получении списка экзаменов");
        }

        Map<Long, String> examMap = examList.stream()
                .collect(Collectors.toMap(
                        Exam::getId,
                        Exam::getName
                ));

        List<ExamResponseDTO> resultList = userResultMap.values().stream()
                .map(userResult -> new ExamResponseDTO(
                        userResult.getUserId(),
                        userResult.getExamId(),
                        examMap.get(userResult.getExamId())
                )).toList();
        return resultList;
    }
}
