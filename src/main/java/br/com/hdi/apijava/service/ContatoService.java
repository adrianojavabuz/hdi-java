package br.com.hdi.apijava.service;

import br.com.hdi.apijava.db.entity.ContatoEntity;
import br.com.hdi.apijava.db.entity.UsuarioEntity;
import br.com.hdi.apijava.db.repository.ContatoRepository;
import br.com.hdi.apijava.db.repository.UsuarioRepository;
import br.com.hdi.apijava.dto.ContatoDTO;
import br.com.hdi.apijava.dto.UsuarioDTO;
import br.com.hdi.apijava.mapper.ContatoMapper;
import br.com.hdi.apijava.mapper.UsuarioMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ContatoService {

    @Autowired
    ContatoRepository contatoRepository;
    @Autowired
    ContatoMapper contatoMapper;


    public List<ContatoDTO> buscarTodosContatos() {
        return contatoMapper.toListDto(contatoRepository.findAll());

    }

    public ContatoDTO buscarUsuarioPorId(Long id) {
        return contatoMapper.entityToDto(contatoRepository.findById(id).orElse(null));

    }

    public ContatoDTO saveContato(ContatoDTO requestDTO) {
        return contatoMapper.entityToDto(contatoRepository.save(contatoMapper.dtoToEntity(requestDTO)));

    }

    public ContatoDTO atualizarContato(ContatoDTO requestDTO, Long id) {
        ContatoEntity contato = contatoRepository.findById(id).orElse(null);
        requestDTO.setId(contato.getId());
        return contatoMapper.entityToDto(contatoRepository.save(contatoMapper.dtoToEntity(requestDTO)));

    }


    public ContatoDTO deletarContato (Long id){
        ContatoEntity contato = contatoRepository.findById(id).orElse(null);
        contatoRepository.delete(contato);
        return contatoMapper.entityToDto(contato);
    }


}
