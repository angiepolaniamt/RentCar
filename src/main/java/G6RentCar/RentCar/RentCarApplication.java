/*
1. Modelo o entidad
2. Interface
3. Repositorio
4. Servicios 
5. Controlador o WEB
*/

/*
Reporte de status, reporte de fechas y reporte de top clientes.
*/
package G6RentCar.RentCar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableConfigurationProperties
@EntityScan(basePackages = {"G6RentCar/RentCar/Model"})
@SpringBootApplication
public class RentCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentCarApplication.class, args);
	}

}
