package com.function;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class ExercicioDois {
    /**
     * This function listens at endpoint "/api/Cleber". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/Cleber
     * 2. curl {your host}/api/Cleber?name=HTTP%20Query
     */
    @FunctionName("ExercicioDois")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", 
            methods = {HttpMethod.GET, HttpMethod.POST}, 
            route = "ExercicioDois/{age}",
            authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
           @BindingName("age") String age,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        String query = request.getQueryParameters().get("name");
        String name = request.getBody().orElse(query);
        context.getLogger().info("Valor recebido:" + name);
        if (name == null){
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name on the query string or in the request body").build();
        } else if(Integer.parseInt(age) >=5 ) {
            return request.createResponseBuilder(HttpStatus.NOT_FOUND).body("Please, revise the age, because this is greater than 5").build();
        }
        else {
            return request.createResponseBuilder(HttpStatus.OK).body("Hello, " + name + " you are : " + age + " year(s) old.").build();
        }
    }
}
