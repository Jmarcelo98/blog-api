package br.com.estudos.blogapi.mappers.output;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.estudos.blogapi.mappers.generic.GenericMapper;
import br.com.estudos.blogapi.model.dtos.output.FollowersOutputDTO;
import br.com.estudos.blogapi.model.entities.Follower;

@Mapper
public interface FollowersOutputMapper extends GenericMapper<Follower, FollowersOutputDTO> {

	FollowersOutputMapper INSTANCE = Mappers.getMapper(FollowersOutputMapper.class);

}
