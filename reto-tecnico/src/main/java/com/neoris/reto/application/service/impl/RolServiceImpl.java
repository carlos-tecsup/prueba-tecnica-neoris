package com.neoris.reto.application.service.impl;

import com.neoris.reto.application.mapper.RolMapper;
import com.neoris.reto.domain.Rol;
import com.neoris.reto.domain.repository.RolRepository;
import com.neoris.reto.application.service.IRolService;
import com.neoris.reto.application.util.enums.ERol;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class RolServiceImpl implements IRolService {
    private final RolRepository rolRepository;

    @Override
    public Single<Rol> crearRolDefault() {
        return Single.fromCallable(() -> {
            Rol roldefault = new Rol();
            roldefault.setName(ERol.ROL_USUARIO.name());
            rolRepository.save(roldefault);
            return roldefault;
        });
    }
}
