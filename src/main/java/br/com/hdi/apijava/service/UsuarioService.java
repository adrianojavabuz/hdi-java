package br.com.hdi.apijava.service;

import br.com.hdi.apijava.db.entity.UsuarioEntity;
import br.com.hdi.apijava.db.repository.ContatoRepository;
import br.com.hdi.apijava.db.repository.UsuarioRepository;
import br.com.hdi.apijava.dto.UsuarioDTO;
import br.com.hdi.apijava.mapper.UsuarioMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioMapper usuarioMapper;


    public List<UsuarioDTO> buscarTodosUsuarios() {
        return usuarioMapper.toListDto(usuarioRepository.findAll());

    }

    public UsuarioDTO buscarUsuarioPorDocumento(Long documento) {
        return usuarioMapper.entityToDto(usuarioRepository.findByDocumento(documento));

    }

    public UsuarioDTO saveUsuario(UsuarioDTO requestDTO) {
        UsuarioEntity user = usuarioMapper.dtoToEntity(requestDTO);

        return usuarioMapper.entityToDto(usuarioRepository.save(usuarioMapper.dtoToEntity(requestDTO)));

    }

    public UsuarioDTO atualizarUsuario(UsuarioDTO requestDTO, Long id) {
        UsuarioEntity user = usuarioRepository.findById(id).orElse(null);
        requestDTO.setId(user.getId());
        return usuarioMapper.entityToDto(usuarioRepository.save(usuarioMapper.dtoToEntity(requestDTO)));

    }

    public UsuarioDTO deletarUsuario (Long id){
        UsuarioEntity user = usuarioRepository.findById(id).orElse(null);
        usuarioRepository.delete(user);
        return usuarioMapper.entityToDto(user);
    }

}
