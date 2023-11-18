package com.malek.app.rest.Repository;

import com.malek.app.rest.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
