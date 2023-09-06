package br.com.rinha.rinha.repository;

import br.com.rinha.rinha.domain.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, String> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO tb_pessoas " +
            "(id, nome, cpfCnpj, nascimento, seguros) " +
            "VALUES " +
            "(:id, :nome, :cpfCnpj, :nascimento, :seguros)"
            , nativeQuery = true)
    Optional<Pessoas> salvarPessoa(@Param("id") String id,
                                   @Param("nome") String nome,
                                   @Param("cpfCnpj") String cpfCnpj,
                                   @Param("nascimento") LocalDate nascimento,
                                   @Param("seguros") List<String> seguros);
}
