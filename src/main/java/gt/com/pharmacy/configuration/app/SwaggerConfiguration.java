package gt.com.pharmacy.configuration.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "BACKEND API RESTFul Pharmacy",
                description = "This application is a RESTFul API to be able to consume all the services available in Pharmacy.",
                termsOfService = "www.pharmacy.com.gt/terms-and-conditions",
                version = "1.0.0",
                contact = @Contact(name = "Pharmacy", url = "http://localhost:8080", email = "customer-service@pharmacy.com"),
                license = @License(name = "Standard Software Use License for pharmacy", url = "www.pharmacy.com.gt/licence")
        ),
        servers = {
                @Server(description = "DEVELOPMENT SERVER", url = "http://localhost:8081"),
                @Server(description = "TESTING SERVER", url = "http://localhost:8082"),
                @Server(description = "PRODUCTION SERVER", url = "http://localhost:8080")
        },
        security = @SecurityRequirement(name = "Security Token")
)
@SecurityScheme(
        name = "Security Token",
        description = "Access Token For My API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfiguration {
}
