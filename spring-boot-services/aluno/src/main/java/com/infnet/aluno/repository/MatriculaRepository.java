package com.infnet.aluno.repository;

public class MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findByDisciplinaIdAndNotaGreaterThanEqual(Long disciplinaId, Double nota);

    List<Matricula> findByDisciplinaIdAndNotaLessThan(Long disciplinaId, Double nota);
}