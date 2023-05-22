package com.neoris.reto.application.service;

import com.neoris.reto.application.dto.request.RegistroRequest;
import io.reactivex.Single;

public interface IRegistroService {
    Single<Boolean> registerUser(RegistroRequest registroRequest);

}
