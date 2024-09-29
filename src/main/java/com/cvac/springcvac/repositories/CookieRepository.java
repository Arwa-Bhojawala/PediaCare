package com.cvac.springcvac.repositories;

import com.cvac.springcvac.models.Cookies;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieRepository extends CrudRepository<Cookies, String> {
}
