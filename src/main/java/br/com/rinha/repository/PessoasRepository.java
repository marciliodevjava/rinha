package br.com.rinha.repository;

import br.com.rinha.domain.Pessoas;
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
    @Query(value = """
            INSERT INTO pessoa.tb_pessoas (id, nome, cpf_cnpj, nascimento) VALUES ( :id, :nome, :cpfCnpj, :nascimento);
                        """, nativeQuery = true)
    void salvarPessoa(@Param("id") String id,
                      @Param("nome") String nome,
                      @Param("cpfCnpj") String cpfCnpj,
                      @Param("nascimento") LocalDate nascimento);

    @Transactional
    @Modifying
    @Query(value = """
            INSERT INTO pessoa.pessoas_seguros (pessoas_id, seguros) VALUES ( :id, :seguros);
                        """, nativeQuery = true)
    void salvarSeguros(@Param("id") String id,
                       @Param("seguros") String seguros);

    @Query(value = "SELECT * FROM pessoa.tb_pessoas WHERE id = :id", nativeQuery = true)
    Optional<Pessoas> buscarPessoa(@Param("id") String id);

    @Query(value = "SELECT * FROM pessoa.tb_pessoas WHERE nome LIKE :nome% LIMIT 50", nativeQuery = true)
    List<Pessoas> buscarPessoaNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM pessoa.pessoas_seguros WHERE pessoas_id = :id", nativeQuery = true)
    List<String> buscarSegurosId(@Param("id") String id);

    @Query(value = "SELECT * FROM pessoa.pessoas_seguros WHERE seguros = :seguros", nativeQuery = true)
    List<String> buscarSeguros(@Param("seguros") String seguros);

    @Query(value = "SELECT * FROM pessoa.tb_pessoas WHERE nome LIKE :nome% LIMIT 1", nativeQuery = true)
    Optional<Pessoas> buscarPessoaNomeExiste(@Param("nome") String nome);
}