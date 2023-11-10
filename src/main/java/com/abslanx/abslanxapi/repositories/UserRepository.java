package com.abslanx.abslanxapi.repositories;

import com.abslanx.abslanxapi.models.User;
import org.springframework.stereotype.Repository;


public interface UserRepository {

    User findByUsername(String username);

    User save(User user);
}
