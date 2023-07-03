package com.example.tanerworking1.controller;


import com.example.tanerworking1.auth.TokenManager;
import com.example.tanerworking1.model.dto.LoginDTO;
import com.example.tanerworking1.model.entity.User;
import com.example.tanerworking1.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:8083")
public class UserController {
    /*
     Loglama için bir instance oluşturuyoruz.
     Ayrıntılı bilgi için bkz: http://www.slf4j.org/manual.html
 */


    /*
        Bu sınıf üzerinde kullanacağımız bağımlılıkları inject ediyoruz.
    */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    /**
     * Gelen kullanıcı adı ve parola bilgilerine göre authentication sağlanır.
     * response header'a generate edilen jwt token setlenir ve 200 http status kodu ile Login Success ibaresi response body'de gönderilir.
     * Bilgilerde bir hata varsa 400 http status kodu ile The username or password is incorrect. Please try again.
     * ibaresi response body'de gönderilir.
     * @param loginDto
     * @return
     */
    @PostMapping("/login") //auth/login 'e gelen web istekleri ile bu metodun eşleştirilmesi sağlanır.
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDto) throws AuthenticationException {
        try {
            if(loginDto != null && loginDto.getUsername() != null
                    && loginDto.getPassword() != null){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
                authenticationManager.authenticate(authenticationToken);
                HttpHeaders headers = new HttpHeaders();
                String token = tokenManager
                        .generateToken(loginDto.getUsername());
                headers.set("Authorization","Bearer " + token);
                return ResponseEntity.ok().headers(headers).body(token);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The username or password is incorrect. Please try again.");

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad bad");
        }
    }

    /**
     * Kullanıcıdan alınan username ve password bilgisi database'e kaydedilir. 200 http status kodu ile username
     * has been successfully registered ibaresi gönderilir. Eğer bir sorun oluşursa 400 http status kodu ile
     * The username or password is incorrect. Please try again. response body'de gönderilir.
     * @param loginDto
     * @return
     */
    @PostMapping("/signup") //auth/signup 'a gelen web istekleri ile bu metodun eşleştirilmesi sağlanır.
    public ResponseEntity<String> signUp(@RequestBody LoginDTO loginDto){
        try {
            if(loginDto.getUsername() != null && loginDto.getPassword() != null){
                userRepository.save(new User(loginDto.getUsername(),passwordEncoder.encode(loginDto.getPassword())));
                return ResponseEntity.ok(loginDto.getUsername() +" has been successfully registered.");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The username or password is incorrect. Please try again.");

        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
