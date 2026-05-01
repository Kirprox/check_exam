package ru.job4j.checkexam.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.checkexam.dto.ExamResponseDTO;
import ru.job4j.checkexam.dto.ExamDto;
import ru.job4j.checkexam.model.UserResult;
import ru.job4j.checkexam.repository.UserResultRepository;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SimpleUserExamService implements UserExamService {

    private final UserResultRepository userResultRepository;
    @Value("${desc-api-url}")
    private String url;

    private final RestTemplate client;

    public SimpleUserExamService(UserResultRepository userResultRepository, RestTemplate client) {
        this.userResultRepository = userResultRepository;
        this.client = client;
    }

    @Override
    public List<ExamResponseDTO> findAll() {
        List<UserResult> results = userResultRepository.findAll();
        List<ExamDto> examDtoList = client.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ExamDto>>() {
                }).getBody();

        if (examDtoList == null) {
            throw new NoSuchElementException("ошибка при получении списка экзаменов");
        }

        Map<Long, String> examMap = examDtoList.stream()
                .collect(Collectors.toMap(
                        ExamDto::getId,
                        ExamDto::getName
                ));

        List<ExamResponseDTO> resultList = results.stream()
                        .map(userResult -> new ExamResponseDTO(
                                userResult.getUserId(),
                                userResult.getExamId(),
                                examMap.get(userResult.getExamId())
                        )).toList();


        return resultList;
    }
}
