package br.com.estudos.blogapi.mappers.output;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.estudos.blogapi.mappers.generic.GenericMapper;
import br.com.estudos.blogapi.model.dtos.output.UsuarioOutputDTO;
import br.com.estudos.blogapi.model.entities.Usuario;

@Mapper
public interface UsuarioOutputMapper extends GenericMapper<Usuario, UsuarioOutputDTO> {

	UsuarioOutputMapper INSTANCE = Mappers.getMapper(UsuarioOutputMapper.class);

}
