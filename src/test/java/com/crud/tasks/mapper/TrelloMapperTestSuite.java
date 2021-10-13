package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals("Title", task.getTitle());
        assertEquals(1L, task.getId());
    }

    @Test
    void mapToTaskDtoTest() {
        //Given
        Task task = new Task(5L, "Title5", "Content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals("Title5", taskDto.getTitle());
        assertEquals(5L, taskDto.getId());
    }

    @Test
    void mapToTaskDtoListTest() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(5L, "Title2", "Content2"));

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertEquals(1, taskDtoList.size());
        assertEquals("Content2", taskDtoList.get(0).getContent());
    }
}
