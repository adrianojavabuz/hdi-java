package br.com.hdi.apijava.mapper;

import br.com.hdi.apijava.db.entity.UsuarioEntity;
import br.com.hdi.apijava.dto.UsuarioDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {

    public abstract UsuarioEntity dtoToEntity(UsuarioDTO dto);

    public abstract UsuarioDTO entityToDto(UsuarioEntity entity);

    public abstract List<UsuarioDTO> toListDto(List<UsuarioEntity> listEntity);

    public abstract List<UsuarioEntity> toListEntity(List<UsuarioDTO> listDto);
}
