package com.backend.shopee.shopee_backend;

import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.domain.entities.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
                map().setEmail(source.getEmail());
                map().setPasswordHash(source.getPasswordHash());
                map().setCpf(source.getCpf());
                map().setBirthDate(source.getBirthDate());
                map().setConfirmEmail(source.getConfirmEmail());
                map().setUserImage(source.getUserImage());
                map().setPhone(source.getUserImage());
            }
        });

        return modelMapper;
    }
}
