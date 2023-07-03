package com.example.tanerworking1.auth;


//Http isteklerini filtreleyecek
//Http request ve responlarının isteklerine cevap vermek için kullanılır,Http cevabı oluşturulacak, Sorunları hataları tokenın doğruluğunda gitmesi gereken yere gitmesine izin verecez

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * SpringSecurityTrainerConfiguration classında configure metodunun almış olduğu
 * HttpSecurity argümanına eklemiş olduğumuz filter class'ı olarak bu classı kullanacağız.
 * Bu class OncePerRequestFilter classını extend eden bir classtır.
 * Gelen istekler üzerinde controller'a gitmeden önce yapılması gereken işlemler OncePerRequestFilter'ın
 * doFilterInternal metodunu override etmemiz gerekmektedir. Gelen her istek ilk olarak bu metodda yapılan işlemlerden geçmektedir.
 */
@Component
public class JwtAuthenticatationFilter extends OncePerRequestFilter {

    private TokenManager tokenManager;

    private UserDetailService userDetailService;

    public JwtAuthenticatationFilter(TokenManager tokenManager,UserDetailService userDetailService) {
        this.tokenManager = tokenManager;
        this.userDetailService = userDetailService;
    }
    /**
     * Bu metod gelen her isteği karşılamaktadır.
     *
     * @param
     * @param
     * @param
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String tokenCore = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if(tokenCore != null && tokenCore.contains("Bearer") && tokenCore.split(" ").length > 1){
            token = tokenCore.split(" ")[1];
            try{
                username = tokenManager.getUserFromToken(token);
            }catch (Exception e) {
                logger.error(e.getMessage());
            }

        }
        /*
            token ve username değişkenlerinin null olup olmadığını ve Context'in authenticate olup olmadığını kontol ediyoruz
            eğer authenticate olunmamışsa token'ın geçerli olup olmadığını kontrol ediyoruz. Token geçerli ise UsernamePasswordAuthenticationToken'ı
            username ile oluşturup context'e authentication'ı setliyoruz.
        */
        if (token != null && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (tokenManager.hasTokenValid(token)) {
                UserDetails user = this.userDetailService.loadUserByUsername(username);
                if (Objects.nonNull(user)) {
                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(user,
                            null,
                            new ArrayList<>());
                    authenticationToken.setDetails
                            (new WebAuthenticationDetailsSource()
                                    .buildDetails(request));
                    SecurityContextHolder.getContext()
                            .setAuthentication(authenticationToken);
                }
            }
        }
         /*
        Servlet Filter chain of responsibility tasarım kalıbını implemente eden bir class olduğu için bu kısımda bir
        sonraki adıma devam etmesini sağlıyoruz.
        Detaylı bilgi için bkz:
        https://github.com/yusufyilmazfr/tasarim-desenleri-turkce-kaynak/tree/master/chain-of-responsibility/java
        https://www.mehmetcemyucel.com/2014/chain-of-responsibility-design-pattern/        */
        filterChain.doFilter(request,response);
    }
}
