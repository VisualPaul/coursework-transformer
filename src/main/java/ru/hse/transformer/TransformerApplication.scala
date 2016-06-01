package ru.hse.transformer

import javax.sql.DataSource

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class TransformerApplication

object TransformerApplication {
  def main(args: Array[String]) {
    SpringApplication.run(classOf[TransformerApplication], args: _*)
  }
  val dataSource : DataSource = dataSourceInit()
  @Bean(name=Array("dataSource"))
  private def dataSourceInit(): MysqlDataSource = {
    val manager = new MysqlDataSource()
    manager.setUser(DatabaseCredentials.databaseUserName)
    manager.setPassword(DatabaseCredentials.databasePassword)
    manager.setDatabaseName(DatabaseCredentials.databaseName)
    manager
  }
}
