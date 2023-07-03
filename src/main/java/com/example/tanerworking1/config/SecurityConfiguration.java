package com.example.tanerworking1.config;

import com.example.tanerworking1.auth.JwtAuthenticatationFilter;
import com.example.tanerworking1.auth.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
configure metodunda  yazılan kurala göre çalışması için
 */
/**
 * Spring security otomatik konfigürasyonu devre dışı bırakmak için gerekli konfigürasyonların yapıldığı
 * Spring Security'nin WebSecurityConfigurerAdapter sınıfını extend eden sınıf.
 * Daha esnek bir yapı olması adına (birden fazla user role gibi) propertie üzerinden değil class üzerinden yapıyoruz.
 */

/*
    Spring Bean'e dahil etmek ve bunun bir konfigürasyon olduğunu belirtmek için bu anotasyonu kullanıyoruz.
 */
@Configuration
/*
    Oto konfigürasyonu etkisiz hale getirebilmek için bu anotasyon çok önemlidir.
    Bu anotasyon springSecurityFilterChain olarak bilinen bir servlet filter'ı oluşturarak
    gelen giden isteklerin filtrelenmesini ve bunun üzerinden authetication authorization işlemlerinin yapılmasını sağlar.
    Daha fazla bilgi için: https://docs.spring.io/spring-security/site/docs/4.0.1.RELEASE/reference/html/jc.html#hello-web-security-java-configuration
*/
@EnableWebSecurity
/*
    Metod bazında güvenlik kontrolü için @EnableGlobalMethodSecurity anotasyonu kullanılabilir.
    Bu anotasyon kullanıldığında metodlara @Secured anotasyonu ile o metodu hangi role sahip kullanıcının koşturacağı belirtilebilir.
    prePostEnabled = true vererek @Secured yapılan metoddan önce ve sonra @Pre... ve @Post... anotasyonları ile bazı kontroller vb işlemlerin yapılmasına izin verilir.
*/

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticatationFilter jwtTokenFilter;
    @Autowired
    private UserDetailService userDetailService;
    /**
     *
     * Http ye gelen istekler buraya gelecek. Bu filterenin içinden geçecek
     *
     */
    /**
     * Security konfigürasyonlarını bu metod üzerinden yapıyoruz.
     * Doğrulamayı nasıl yapacağımız, eğer izin vermek istiyorsak ki bizim uygulamamızda /auth/, /healthCheck ve /h2/
     * yollarında isteklerin reddedilmemesi her halükarda izim verilmesini istedik. Bunun dışında yine biz uygulamamızda
     * <code>.headers().frameOptions().sameOrigin()</code> ile aynı origin'e sahip iframelere (h2 konsolu iframeler ile çalışır) izin verdik.
     * Yine uygulamamızda <code>.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)</code> kullanarak Spring Security
     * tarafından HttpSession oluşturulmamasını ve bunun security context'e asla dahil edilmemesi gerektiğini bildirdik.
     * Sonrasında filtreleyecek class'ın hangisi olduğunu belirten addFilterBefor metodunu kullanıyoruz.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        Authentication hangi tipte olmalı bunu belirteceğiz
         */
        http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().frameOptions().sameOrigin();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * AuthenticationManager'ı spring context'e dahil etmek için bir instance return ediyoruz.
     * @return
     * @throws Exception
     */
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerInit() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * Encoder yapacağımız class'ı spring context'e dahil etmek için bir instance retun ediyoruz.
     * @return
     */
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager oluşturmak için kullanılır. userDetailService adıyla yazmış olduğumuz UserDetailService interface'ini implemente eden classımızı
     * tanıtarak authentication'ı bu servis üzerinden yapmasını sağlıyoruz. Tabi son olarak password encode olarak kullanacağımız class'ı da bu builder'a veriyoruz.
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailService)
                .passwordEncoder(getPasswordEncoder());
    }
}
