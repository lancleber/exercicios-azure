package com.function;

import com.microsoft.azure.functions.annotation.*;

import org.json.JSONObject;

import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class ExercicioTres {
    /**
     * This function listens at endpoint "/api/ExercicioTresAtualizado". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/ExercicioTresAtualizado
     * 2. curl {your host}/api/ExercicioTresAtualizado?name=HTTP%20Query
     */
    @FunctionName("ExercicioTres")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST},
             authLevel = AuthorizationLevel.ANONYMOUS) 
             HttpRequestMessage<String> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

 // String algumHeader = request.getHeaders().get("useridentifier");
        
            JSONObject json = new JSONObject(request.getBody());

            String algumJson = json.getString("name");

            if (algumJson.equalsIgnoreCase("João") || algumJson.equalsIgnoreCase("José") || algumJson.equalsIgnoreCase("Joaquim")){
             return request.createResponseBuilder(HttpStatus.OK).body("Hello, the name " + algumJson + " is valid!").build();

            }else{
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body(algumJson + " is not valid! Type (João, José or Joaquim).").build();
        }
    }
}
