package br.com.hdi.apijava.controller.response;

import br.com.hdi.apijava.db.entity.ContatoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO implements Serializable {

    private Long id;

    private String nome;

    private Long documento;

    private List<ContatoEntity> contatos;
}
