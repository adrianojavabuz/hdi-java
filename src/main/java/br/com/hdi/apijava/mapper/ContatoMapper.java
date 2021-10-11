package br.com.hdi.apijava.mapper;

import br.com.hdi.apijava.db.entity.ContatoEntity;
import br.com.hdi.apijava.dto.ContatoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ContatoMapper {

    public abstract ContatoEntity dtoToEntity(ContatoDTO dto);

    public abstract ContatoDTO entityToDto(ContatoEntity entity);

    public abstract List<ContatoDTO> toListDto(List<ContatoEntity> listEntity);

    public abstract List<ContatoEntity> toListEntity(List<ContatoDTO> listDto);
}
