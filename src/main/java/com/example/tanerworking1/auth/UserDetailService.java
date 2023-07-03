package com.example.tanerworking1.auth;


import com.example.tanerworking1.model.entity.User;
import com.example.tanerworking1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService arabirimi, kullanıcıyla ilgili verileri almak için kullanılır.
 * Spring'in AuthenticationProvider'ı tarafından kimlik doğrulama sırasında kullanıcıyla
 * ilgili işlemler
 * override etmiş olduğumuz loadByUserName üzerinden yapılır.
 */
@Component//Spring context'de bu classtan bir bean oluşturmak için kullanıyoruz.
public class UserDetailService implements UserDetailsService {

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " is not found.");
        }
        return new UserPrincipal(user);
    }
}
