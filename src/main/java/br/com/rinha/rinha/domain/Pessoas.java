package br.com.rinha.rinha.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.UUID;

@Entity
@Table(name = "tb_pessoas")
public class Pessoas implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String nome;
    private String cpfCnpj;
    private LocalDate nascimento;
    private LinkedList<String> seguros = new LinkedList<>();


}
