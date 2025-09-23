package com.infnet.aluno.repository;

import com.infnet.aluno.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findByDisciplinaIdAndNotaGreaterThanEqual(Long disciplinaId, Double nota);

    List<Matricula> findByDisciplinaIdAndNotaLessThan(Long disciplinaId, Double nota);
}