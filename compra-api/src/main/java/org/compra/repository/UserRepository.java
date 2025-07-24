package org.compra.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.RequestScoped;
import org.compra.model.User;

@RequestScoped
public class UserRepository implements PanacheRepository<User> {
}
