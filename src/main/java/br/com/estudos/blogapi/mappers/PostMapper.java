package br.com.estudos.blogapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.estudos.blogapi.mappers.generic.GenericMapper;
import br.com.estudos.blogapi.model.dtos.PostDTO;
import br.com.estudos.blogapi.model.entities.Post;

@Mapper
public interface PostMapper extends GenericMapper<Post, PostDTO> {

	PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

}
