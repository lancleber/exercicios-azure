package com.function;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class ExercicioUm {
    /**
     * This function listens at endpoint "/api/ExercicioUm". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/ExercicioUm
     * 2. curl {your host}/api/ExercicioUm?name=HTTP%20Query
     */
    @FunctionName("ExercicioUm")
    public HttpResponseMessage run(
        @HttpTrigger(
            name = "req",
            methods = {HttpMethod.GET, HttpMethod.POST},
            route = "ExercicioUm/{numero}",
            authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
       @BindingName("numero") Integer number,
            final ExecutionContext context) {
    context.getLogger().info("Java HTTP trigger processed a request.");

    // Parse query parameter
     // final String query = request.getQueryParameters().get("name");
     //final String name = request.getBody().orElse(query);

    context.getLogger().info("Valor recebido = " + number);
    if (number >= 5) {
        // return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name on the query string or in the request body").build();
        // return request.CreateResponse (HttpStatusCode.NotFound);
        return request.createResponseBuilder(HttpStatus.NOT_FOUND).body("Pass an integer number less than 5 in the query or in the body of the request").build();
    } else {
        return request.createResponseBuilder(HttpStatus.OK).body("Número: " + number).build();
    }
}
}
