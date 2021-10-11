package br.com.hdi.apijava.db.entity;

import br.com.hdi.apijava.db.entity.ContatoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements Serializable {
    private static final long serialVersionUID = -4820324162737841541L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nome;
    private Long documento;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contato_id", nullable = false)
    private List<ContatoEntity> contatos;



}
