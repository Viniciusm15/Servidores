package com.vinicius.serversapi.auth.service.contract;

import com.vinicius.serversapi.auth.model.core.Person;

public interface IPersonPhotoService {
    String getPersonPhotoUrl(Person person);
}
