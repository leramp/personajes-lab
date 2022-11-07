package com.alkemy.personajes.personajes.auth.config;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.alkemy.personajes.personajes.auth.filter.JwtRequestFilter;
import com.alkemy.personajes.personajes.auth.service.UserDetailsCustomService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity//habilitamos la seguridad a traves de web, endpoints y demás
//spring tiene muchos tipos de seguridad, nosotros usamos la de tipo web, nos manejamos con rest
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//insertamos el filtro y el servicio en el componente de configuración
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override//le indicamos cual es el userDetails que usamos en lugar del de default
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsCustomService);//esto es siempre igual
        //sobrescribimos el método userDetailsService y le pasamos uno personal
    }
    @Bean//esto es para poder encodear la  password y le decimos que no ponga ningún encoder
    public PasswordEncoder passwordEncoder(){//es decir, esto es método que va a generar un componente (bean)
        //en  nuestro contexto de spring pero nosotros le vamos a decir que no use passqord

        return NoOpPasswordEncoder.getInstance();//esto es un bean para poder encodear la password
    }//Esta deprecado porque no se usan password en la bbdd
    //pero viene bien para la práctica

    @Override
    @Bean//aca definimos directamente con lo que nos brinda spring quien sera el manejador de la autenticación, no hacemos nada
    public AuthenticationManager authenticationManagerBean() throws  Exception{
        return super.authenticationManagerBean();
    }
    @Override//aca configuramos como se comporta nuestro httpSecurity, configuramos un filtro y también
    //a que endpoint le damos seguridad y a cual no
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()//es un tipo de configuracion desabilitado, no hay que darle bolilla
                .authorizeRequests().antMatchers("/auth/*").permitAll()
                //con esto configuramos a que endpoint le damos seguridad y a cual no. Cualquiera
                //que matche con auth/* permitile to do
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                //aca le digo que ese fultro sera encargado d recibir todas las peticiones y procesarlas

                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       //
        //por defecto spring es STATEFUL, es decir que guarda el estado, por eso a la segundo no pide loguearse

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }//por otro lado le agregamos un filtro. Este es el filtro que se encarga de agarrar el header y ver si tiene los datos correctos para dejarlo pasar
}//nustro filtro es el primer parámetro porque queremos que se ejecute antes que el otro. Luego le diremos al otro (ahora lo vemos)
//que va funcionar correctamente

