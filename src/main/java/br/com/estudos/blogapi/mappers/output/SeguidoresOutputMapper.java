package br.com.estudos.blogapi.mappers.output;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.estudos.blogapi.mappers.generic.GenericMapper;
import br.com.estudos.blogapi.model.dtos.output.SeguidoresOutputDTO;
import br.com.estudos.blogapi.model.entities.Seguidor;

@Mapper
public interface SeguidoresOutputMapper extends GenericMapper<Seguidor, SeguidoresOutputDTO> {

	SeguidoresOutputMapper INSTANCE = Mappers.getMapper(SeguidoresOutputMapper.class);

}
