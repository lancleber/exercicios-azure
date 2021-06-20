package com.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Azure Functions with HTTP Trigger.
 */
public class ExercicioQuatro {
    /**
     * This function listens at endpoint "/api/ExercicioQuatroAtualizado". Two ways
     * to invoke it using "curl" command in bash: 1. curl -d "HTTP Body" {your
     * host}/api/ExercicioQuatroAtualizado 2. curl {your
     * host}/api/ExercicioQuatroAtualizado?name=HTTP%20Query
     */
    @FunctionName("ExercicioQuatro")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = { HttpMethod.GET,
                    HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<String> request,
            final ExecutionContext context) throws JSONException {

        // context.getLogger().info(request.getHeaders().toString());
        context.getLogger().info("Java HTTP trigger processed a request.");

        String algumHeader = request.getHeaders().get("useridentifier");
        // String algumHeader = request.getHeaders().get(toString().toLowerCase());

        JSONObject json = new JSONObject(request.getBody());

        String algumJson = json.optString("name", "");
        // System.out.println(algumHeader);

        if (algumHeader.isEmpty() || !algumHeader.equals("azurefunctions")) {
            return (HttpResponseMessage) request.createResponseBuilder(HttpStatus.UNAUTHORIZED);

        } else if (algumJson.equalsIgnoreCase("João") || algumJson.equalsIgnoreCase("José")
                || algumJson.equalsIgnoreCase("Joaquim")) {
            return request.createResponseBuilder(HttpStatus.OK).body("Hello, the name " + algumJson + " is valid!")
                    .build();

        } else {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body(algumJson + " is not valid! Type (João, José or Joaquim).").build();
        }
    }
}