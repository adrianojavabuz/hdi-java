package br.com.hdi.apijava;

import br.com.hdi.apijava.db.entity.ContatoEntity;
import br.com.hdi.apijava.db.entity.UsuarioEntity;
import br.com.hdi.apijava.db.repository.ContatoRepository;
import br.com.hdi.apijava.db.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApiJavaApplication implements CommandLineRunner {

	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	ContatoRepository contatoRepository;
	public static void main(String[] args) {
		SpringApplication.run(ApiJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<ContatoEntity> lscontato = new ArrayList<>();
		UsuarioEntity usuarioEntity1 = UsuarioEntity.builder()
				.documento(465789834L)
				.nome("User1")
				.contatos(lscontato)
				.id(1L)
				.build();

		ContatoEntity contatoEntity = ContatoEntity.builder()
				.email("cebolinha@gmail.com")
				.flag(1)
				.telefone(11945467348L)
				.build();
		lscontato.add(contatoEntity);
		UsuarioEntity usuarioEntity = UsuarioEntity.builder()
				.documento(465789834L)
				.nome("User1")
				.contatos(lscontato)
				.build();

		usuarioRepository.save(usuarioEntity);

		contatoRepository.save(contatoEntity);




	}
}
