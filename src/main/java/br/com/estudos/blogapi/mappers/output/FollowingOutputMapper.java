package br.com.estudos.blogapi.mappers.output;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.estudos.blogapi.mappers.generic.GenericMapper;
import br.com.estudos.blogapi.model.dtos.output.FollowingOutputDTO;
import br.com.estudos.blogapi.model.entities.Follower;

@Mapper
public interface FollowingOutputMapper extends GenericMapper<Follower, FollowingOutputDTO> {

	FollowingOutputMapper INSTANCE = Mappers.getMapper(FollowingOutputMapper.class);

}
