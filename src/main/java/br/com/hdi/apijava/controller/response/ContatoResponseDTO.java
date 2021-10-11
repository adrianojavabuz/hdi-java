package br.com.hdi.apijava.controller.response;

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
public class ContatoResponseDTO implements Serializable {

    private String email;

    private Long telefone;

    private int flag;
}
