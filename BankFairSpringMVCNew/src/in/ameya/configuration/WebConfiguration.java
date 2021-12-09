package in.ameya.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("in.ameya")
public class WebConfiguration extends WebMvcConfigurerAdapter{

	//this method is used to call spring controller  for static resources such as javascript css 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		//super.addResourceHandlers(registry);
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public ViewResolver getvViewResolver() {
		InternalResourceViewResolver intenalResourceViewResolver =new InternalResourceViewResolver();
		intenalResourceViewResolver.setPrefix("/WEB-INF/views/");
		intenalResourceViewResolver.setSuffix(".jsp");
		return intenalResourceViewResolver;
	}
	
}
