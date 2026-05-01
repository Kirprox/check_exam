package ru.job4j.checkexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.checkexam.model.UserResult;

public interface UserResultRepository extends JpaRepository<UserResult, Long> {
}
