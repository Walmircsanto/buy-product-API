package org.compra.service;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.compra.model.User;
import org.compra.repository.UserRepository;

@RequestScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public User createUser(User user) {
        if (user.getAge() >= 18) {
            this.userRepository.persist(user);
            this.userRepository.flush();
            return user;
        }else{
            throw new RuntimeException("Invalide age user");
        }

    }

    public User getUser(Long id){
        return  this.userRepository.findById(id);
    }

    public boolean existsUser(Long id){
        return this.userRepository.findById(id) != null;
    }

    public void paymentUser(Long id, Double amount){
        User  user = this.userRepository.findById(id);
        if( user != null){
            user.setBalance(user.getBalance()-amount);
            this.userRepository.flush();

        }else{
            throw new RuntimeException("User not found");
        }


    }






}
