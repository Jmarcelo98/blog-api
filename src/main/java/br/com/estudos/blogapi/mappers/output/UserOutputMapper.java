package br.com.estudos.blogapi.mappers.output;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.estudos.blogapi.mappers.generic.GenericMapper;
import br.com.estudos.blogapi.model.dtos.output.UserOutputDTO;
import br.com.estudos.blogapi.model.entities.User;

@Mapper
public interface UserOutputMapper extends GenericMapper<User, UserOutputDTO> {

	UserOutputMapper INSTANCE = Mappers.getMapper(UserOutputMapper.class);

}
