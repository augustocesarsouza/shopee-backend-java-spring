package com.backend.shopee.shopee_backend;

import com.backend.shopee.shopee_backend.application.dto.*;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressUpdateDTOValidator;
import com.backend.shopee.shopee_backend.domain.entities.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Configuration
public class ModelConfiguration {
    @Bean
        public ModelMapper modelMapper() {
            ModelMapper modelMapper = new ModelMapper();

            modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setPropertyCondition(context -> context.getSource() != null);

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
                    map().setPhone(source.getPhone());
                    map().setGender(source.getGender());
                }
            });

            modelMapper.addMappings(new PropertyMap<Address, AddressDTO>() {
                @Override
                protected void configure() {
                    map().setId(source.getId());
                    map().setFullName(source.getFullName());
                    map().setPhoneNumber(source.getPhoneNumber());
                    map().setCep(source.getCep());
                    map().setStateCity(source.getStateCity());
                    map().setNeighborhood(source.getNeighborhood());
                    map().setStreet(source.getStreet());
                    map().setNumberHome(source.getNumberHome());
                    map().setComplement(source.getComplement());
                    map().setDefaultAddress(source.getDefaultAddress());
                    map().setUserId(source.getUserId());

                    when(Objects::nonNull)
                            .map(source.getUser(), destination.getUserDTO());

//                    map().setUserDTO(new UserDTO(source.getUser().getId(), source.getUser().getName(), source.getUser().getEmail(),
//                        source.getUser().getPasswordHash(), source.getUser().getCpf(), source.getUser().getBirthDate(), source.getUser().getConfirmEmail(),
//                        source.getUser().getUserImage(), null, null));
                }
            });

            modelMapper.addMappings(new PropertyMap<Promotion, PromotionDTO>() {
                @Override
                protected void configure() {
                    map().setId(source.getId());
                    map().setWhatIsThePromotion(source.getWhatIsThePromotion());
                    map().setTitle(source.getTitle());
                    map().setDescription(source.getDescription());
                    map().setDate(source.getDate());
                    map().setImg(source.getImg());
                    map().setListImgInner(source.getListImgInner());
                }
            });

            modelMapper.addMappings(new PropertyMap<PromotionUser, PromotionUserDTO>() {
                @Override
                protected void configure() {
                    map().setId(source.getId());
                    map().setPromotionId(source.getPromotionId());
                    map().setUserId(source.getUserId());

                    when(Objects::nonNull)
                            .map(source.getPromotion(), destination.getPromotionDTO());

                    when(Objects::nonNull)
                            .map(source.getUser(), destination.getUserDTO());

    //                    map().setUserDTO(new UserDTO(source.getUser().getId(), source.getUser().getName(), source.getUser().getEmail(),
    //                        source.getUser().getPasswordHash(), source.getUser().getCpf(), source.getUser().getBirthDate(), source.getUser().getConfirmEmail(),
    //                        source.getUser().getUserImage(), null, null));
                }
            });

            modelMapper.addMappings(new PropertyMap<ShopeeUpdate, ShopeeUpdateDTO>() {
                @Override
                protected void configure() {
                    map().setId(source.getId());
                    map().setTitle(source.getTitle());
                    map().setDescription(source.getDescription());
                    map().setDate(source.getDate());
                    map().setImg(source.getImg());
                }
            });

            modelMapper.addMappings(new PropertyMap<ShopeeUpdateUser, ShopeeUpdateUserDTO>() {
                @Override
                protected void configure() {
                    map().setId(source.getId());
                    map().setUserId(source.getUserId());
                    map().setShopeeUpdateId(source.getShopeeUpdateId());

                    when(Objects::nonNull)
                            .map(source.getShopeeUpdate(), destination.getShopeeUpdateDTO());

                    when(Objects::nonNull)
                            .map(source.getUser(), destination.getUserDTO());
                }
            });

        modelMapper.addMappings(new PropertyMap<Cupon, CuponDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setFirstText(source.getFirstText());
                map().setSecondText(source.getSecondText());
                map().setThirdText(source.getThirdText());
                map().setDateValidateCupon(source.getDateValidateCupon());
                map().setQuantityCupons(source.getQuantityCupons());
                map().setWhatCuponNumber(source.getWhatCuponNumber());
                map().setSecondImg(source.getSecondImg());
            }
        });

            return modelMapper;
    }
}