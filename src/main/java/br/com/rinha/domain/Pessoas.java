package br.com.rinha.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pessoas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "nome", length = 32)
    private String nome;
    @Column(name = "cpfCnpj", unique = true, length = 14)
    private String cpfCnpj;
    private LocalDate nascimento;
    @ElementCollection
    @CollectionTable(name = "pessoas_seguros", joinColumns = @JoinColumn(name = "pessoas_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<String> seguros = new ArrayList<>();

}
