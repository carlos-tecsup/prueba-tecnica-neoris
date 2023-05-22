package com.neoris.reto.application.service;


import com.neoris.reto.domain.Rol;
import io.reactivex.Single;

public interface IRolService {
    Single<Rol> crearRolDefault();

}
