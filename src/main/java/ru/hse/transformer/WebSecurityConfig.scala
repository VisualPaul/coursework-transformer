package ru.hse.transformer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter{
  override def configure(http: HttpSecurity): Unit = {
    http.authorizeRequests().anyRequest().hasAuthority("USER").and()
    http.formLogin().and()

  }
  @Autowired
  def configureGlobal(auth: AuthenticationManagerBuilder) : Unit = {
    //auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
    auth.jdbcAuthentication().dataSource(TransformerApplication.dataSource)
      .usersByUsernameQuery(
        "SELECT users.name, users.password, users.enabled FROM users WHERE users.name=?")
      .authoritiesByUsernameQuery(
        """SELECT users.name, roles.name FROM users
             INNER JOIN user_roles ON users.id=user_roles.user_id
             INNER JOIN roles ON user_roles.role_id = roles.role_id
           WHERE users.name = ?""")
        .passwordEncoder(passwordEncoder())
  }
  @Bean
  def passwordEncoder() : PasswordEncoder ={
    new BCryptPasswordEncoder()
  }
}
