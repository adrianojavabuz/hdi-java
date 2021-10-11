package br.com.hdi.apijava.service;

import br.com.hdi.apijava.db.entity.ContatoEntity;
import br.com.hdi.apijava.db.entity.UsuarioEntity;
import br.com.hdi.apijava.db.repository.ContatoRepository;
import br.com.hdi.apijava.db.repository.UsuarioRepository;
import br.com.hdi.apijava.dto.ContatoDTO;
import br.com.hdi.apijava.dto.UsuarioDTO;
import br.com.hdi.apijava.mapper.ContatoMapper;
import br.com.hdi.apijava.mapper.UsuarioMapper;
import br.com.hdi.apijava.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {ContatoService.class})
public class ContatoServiceTest {

    @InjectMocks
    private ContatoService usuarioService;

    @MockBean
    private ContatoRepository contatoRepository;

    @MockBean
    private ContatoMapper contatoMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetFindAll() {
        Mockito.when(contatoRepository.findAll()).thenReturn(getListContato());
        Mockito.when(contatoMapper.toListDto(getListContato())).thenReturn(getListUsuarioDTO());

        usuarioService.buscarTodosContatos();
        assertNotNull(usuarioService.buscarTodosContatos());

    }

    @Test
    public void shouldSave() {
        Mockito.when(contatoRepository.save(getContato())).thenReturn(getContato());
        Mockito.when(contatoMapper.dtoToEntity(getContatoDTO())).thenReturn(getContato());
        Mockito.when(contatoMapper.entityToDto(getContato())).thenReturn(getContatoDTO());

        usuarioService.saveContato(getContatoDTO());
        assertNotNull(usuarioService.saveContato(getContatoDTO()));

    }

    public ContatoEntity getContato(){
        List<ContatoEntity> lscontato = new ArrayList<>();
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .documento(465789834L)
                .id(1L)
                .nome("User1")
                .contatos(lscontato)
                .build();



        ContatoEntity contatoEntity = ContatoEntity.builder()
                .email("cebolinha@gmail.com")
                .flag(1)
                .id(2L)
                .telefone(11945467348L)
                .usuario(usuarioEntity)
                .build();

        return  contatoEntity;

    }

    public ContatoDTO getContatoDTO(){
        List<ContatoDTO> lscontato = new ArrayList<>();
        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .documento(465789834L)
                .nome("User1")
                .contatos(lscontato)
                .build();



        ContatoDTO contatoDTO = ContatoDTO.builder()
                .email("cebolinha@gmail.com")
                .id(2L)
                .flag(1)
                .telefone(11945467348L)
                .build();

        return  contatoDTO;

    }

    public List<ContatoEntity> getListContato() {
        List<ContatoEntity> ls = new ArrayList<ContatoEntity>();
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
                .usuario(usuarioEntity1)
                .build();
        lscontato.add(contatoEntity);
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .documento(465789834L)
                .nome("User1")
                .contatos(lscontato)
                .build();


        return ls;
    }
    //
    public List<ContatoDTO> getListUsuarioDTO() {
        List<ContatoDTO> ls = new ArrayList<ContatoDTO>();
        List<ContatoDTO> lscontato = new ArrayList<>();

        ContatoDTO contatoDTO = ContatoDTO.builder()
                .email("cebolinha@gmail.com")
                .flag(1)
                .telefone(11945467348L)
                .build();
        lscontato.add(contatoDTO);

        ls.add(contatoDTO);

        return ls;

    }

}
