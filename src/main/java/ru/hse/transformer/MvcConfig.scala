package ru.hse.transformer

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.context.annotation.Configuration


@Configuration
class MvcConfig extends WebMvcAutoConfigurationAdapter {
  override def addViewControllers(registry: ViewControllerRegistry): Unit = {
    registry.addViewController("/").setViewName("greeting")
    registry.addViewController("/login").setViewName("login")
  }
}
