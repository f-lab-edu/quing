package flab.quing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Quing",
                description = "API 명세서",
                version = "v1",
                contact = @Contact(
                        name = "quing"
//                        email = "sample@email.co.kr"
                )
        )
)

@Configuration
public class SwaggerConfig {
}