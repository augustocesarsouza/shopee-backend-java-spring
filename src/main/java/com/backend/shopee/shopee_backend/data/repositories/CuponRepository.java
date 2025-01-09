package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.CuponDTO;
import com.backend.shopee.shopee_backend.data.context.CuponRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.Cupon;
import com.backend.shopee.shopee_backend.domain.repositories.ICuponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CuponRepository implements ICuponRepository {
    private final CuponRepositoryJPA cuponRepositoryJPA;

    @Autowired
    public CuponRepository(CuponRepositoryJPA cuponRepositoryJPA) {
        this.cuponRepositoryJPA = cuponRepositoryJPA;
    }

    @Override
    public CuponDTO GetCuponById(UUID cuponId) {
        return cuponRepositoryJPA.GetCuponById(cuponId);
    }

    @Override
    public CuponDTO GetCuponByIdToVerifyIfExist(UUID cuponId) {
        return cuponRepositoryJPA.GetCuponByIdToVerifyIfExist(cuponId);
    }

    @Override
    public Cupon create(Cupon cupon) {
        if(cupon == null)
            return null;

        return cuponRepositoryJPA.save(cupon);
    }

    @Override
    public Cupon update(Cupon cupon) {
        return null;
    }

    @Override
    public Cupon delete(UUID cuponId) {
        if(cuponId == null)
            return null;

        var cupon = cuponRepositoryJPA.findById(cuponId).orElse(null);

        if(cupon == null)
            return null;

        cuponRepositoryJPA.delete(cupon);

        return cupon;
    }
}
