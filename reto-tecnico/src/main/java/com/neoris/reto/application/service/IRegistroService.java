package com.neoris.reto.application.service;

import com.neoris.reto.application.dto.request.RegistroRequest;

public interface IRegistroService {
    boolean registerUser(RegistroRequest registroRequest);

}
