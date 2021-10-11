package br.com.hdi.apijava.controller.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> implements Serializable {
	private static final long serialVersionUID = -4516589941682806811L;

	@JsonProperty("sucesso")
	private boolean success;
	
	@JsonProperty("mensagem")
	private String message;
	
	@JsonProperty("conteudo")
	private T data;
}
