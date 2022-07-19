package br.com.estudos.blogapi.mappers.output;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.estudos.blogapi.mappers.generic.GenericMapper;
import br.com.estudos.blogapi.model.dtos.output.SeguindoOutputDTO;
import br.com.estudos.blogapi.model.entities.Seguidor;

@Mapper
public interface SeguindoOutputMapper extends GenericMapper<Seguidor, SeguindoOutputDTO> {

	SeguindoOutputMapper INSTANCE = Mappers.getMapper(SeguindoOutputMapper.class);

}
