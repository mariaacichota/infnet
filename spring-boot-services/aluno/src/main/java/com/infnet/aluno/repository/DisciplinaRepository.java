package com.infnet.aluno.repository;

import com.infnet.aluno.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DisciplinaRepository extends JpaRepository<Disciplina, Long> {

}

