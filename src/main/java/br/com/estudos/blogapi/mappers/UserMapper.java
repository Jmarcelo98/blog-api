package br.com.estudos.blogapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.estudos.blogapi.mappers.generic.GenericMapper;
import br.com.estudos.blogapi.model.dtos.UserDTO;
import br.com.estudos.blogapi.model.entities.User;

@Mapper
public interface UserMapper extends GenericMapper<User, UserDTO> {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
