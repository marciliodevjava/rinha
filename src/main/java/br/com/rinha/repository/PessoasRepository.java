package br.com.rinha.repository;

import br.com.rinha.domain.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, String> {

}
