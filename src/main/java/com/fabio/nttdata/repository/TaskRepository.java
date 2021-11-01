package com.fabio.nttdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabio.nttdata.controller.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

}
