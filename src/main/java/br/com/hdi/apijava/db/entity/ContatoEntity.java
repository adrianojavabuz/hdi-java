package br.com.hdi.apijava.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "contato")
public class ContatoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String email;
    private Long telefone;
    private int flag;
//    @ManyToOne()
//    @JoinColumn(name = "usuario_id", nullable = false)
 //   private UsuarioEntity usuario;

}
