package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entity.User;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi(OpenApiCustomizer openApiCustomizer) {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .addOpenApiCustomizer(openApiCustomizer)
                .build();
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            openApi.info(new Info()
                    .title("User Management API")
                    .description("API per la gestione degli utenti")
                    .version("v1.0")
                    .contact(new Contact()
                            .name("Nome")
                            .email("email@example.com")
                            .url("https://www.example.com")));

            openApi.components(new io.swagger.v3.oas.models.Components()
                    .addSchemas("User", new Schema<User>()
                            .title("User")
                            .description("Schema per User")
                            .example(new User("Nome", "Cognome", LocalDate.of(1990, 1, 1), "Città")))
                    .addSchemas("UserList", new Schema<List<User>>()
                            .title("User List")
                            .description("Schema per lista di utenti")
                            .example(Arrays.asList(new User("Nome", "Cognome", LocalDate.of(1990, 1, 1), "Città")))));

            openApi.paths(new Paths()
                    .addPathItem("/api/user/{id}", new PathItem()
                            .get(new io.swagger.v3.oas.models.Operation()
                                    .summary("Leggi un utente per ID")
                                    .description("Restituisce i dettagli di un utente specifico basato sul suo ID")
                                    .addParametersItem(new Parameter()
                                            .name("id")
                                            .in("path")
                                            .required(true)
                                            .schema(new Schema<>().type("integer"))
                                            .description("ID dell'utente da cercare"))
                                    .addTagsItem("User API")
                                    .responses(new ApiResponses().addApiResponse("200", new ApiResponse()
                                            .description("Success")
                                            .content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()
                                                    .schema(new Schema<User>().$ref("#/components/schemas/User"))))))))
                    .addPathItem("/api/users", new PathItem()
                            .get(new io.swagger.v3.oas.models.Operation()
                                    .summary("Leggi tutti gli utenti")
                                    .description("Restituisce un elenco di tutti gli utenti")
                                    .addTagsItem("User API")
                                    .responses(new ApiResponses().addApiResponse("200", new ApiResponse()
                                            .description("Success")
                                            .content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()
                                                    .schema(new Schema<List<User>>().$ref("#/components/schemas/UserList"))))))))
                    .addPathItem("/api/user", new PathItem()
                            .post(new io.swagger.v3.oas.models.Operation()
                                    .summary("Inserisci un nuovo utente")
                                    .description("Inserisce un nuovo utente nel sistema")
                                    .addTagsItem("User API")
                                    .requestBody(new io.swagger.v3.oas.models.parameters.RequestBody()
                                            .content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()
                                                    .schema(new Schema<User>().$ref("#/components/schemas/User")))))
                                    .responses(new ApiResponses().addApiResponse("200", new ApiResponse()
                                            .description("Success"))))));
        };
    }
}
