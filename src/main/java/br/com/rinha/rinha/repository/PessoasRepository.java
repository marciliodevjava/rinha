package br.com.rinha.rinha.repository;

import br.com.rinha.rinha.domain.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, String> {

}
