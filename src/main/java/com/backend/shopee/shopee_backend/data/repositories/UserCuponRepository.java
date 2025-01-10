package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.UserCuponDTO;
import com.backend.shopee.shopee_backend.data.context.UserCuponRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.UserCupon;
import com.backend.shopee.shopee_backend.domain.repositories.IUserCuponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserCuponRepository implements IUserCuponRepository {
    private final UserCuponRepositoryJPA userCuponRepositoryJPA;

    @Autowired
    public UserCuponRepository(UserCuponRepositoryJPA userCuponRepositoryJPA) {
        this.userCuponRepositoryJPA = userCuponRepositoryJPA;
    }

    @Override
    public List<UserCuponDTO> GetAllCuponByUserId(UUID userCuponId) {
        return userCuponRepositoryJPA.GetAllCuponByUserId(userCuponId);
    }

    @Override
    public UserCupon create(UserCupon userCupon) {
        if(userCupon == null)
            return null;

        return userCuponRepositoryJPA.save(userCupon);
    }

    @Override
    public UserCupon update(UserCupon userCupon) {
        return null;
    }

    @Override
    public UserCupon delete(UUID userCuponId) {
        if(userCuponId == null)
            return null;

        var userCupon = userCuponRepositoryJPA.findById(userCuponId).orElse(null);

        if(userCupon == null)
            return null;

        userCuponRepositoryJPA.delete(userCupon);

        return userCupon;
    }
}
