package com.clearbit.client.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clearbit.*;
import com.clearbit.client.model.Person;
import com.clearbit.client.model.Company;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class CombinedApi {

  private ApiClient apiClient;
  private final JSON json = new JSON();

  public CombinedApi() {
    this(Configuration.getDefaultApiClient());
  }

  public CombinedApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public Map<String, Object> streamingLookup(String email) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameters are set
     if (email == null) {
        throw new ApiException(400, "Missing the required parameter 'email' when calling CombinedApi.streamingLookup");
     }
     
    // create path and map variables
//    String path = "https://person-stream.clearbit.com/v2/combined/find?email={email}".replaceAll("\\{" + "email" + "\\}", apiClient.escapeString(email.toString()));
      String path = "https://person-stream.clearbit.com/v2/combined/find";

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
      queryParams.add(new Pair("email", email));
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();
    String accept = apiClient.selectHeaderAccept(new String[]{});
    String contentType = apiClient.selectHeaderContentType(new String[]{});

    String[] authNames = new String[] { "Basic Authentication" };

    TypeRef<String> returnType = new TypeRef<String>() {};
    String response =  apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
    JsonNode nodes = json.deserialize(response);
    Person person = json.deserialize(nodes.get("person"), Person.class);
      Company company = json.deserialize(nodes.get("company"), Company.class);
      Map<String, Object> ret = new HashMap<String, Object>();
      ret.put("PERSON", person);
      ret.put("COMPANY", company);

    return ret;
  }

  public Map<String, Object> subscriptionLookup(String email, String webhook_id) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'email' is set
     if (email == null) {
        throw new ApiException(400, "Missing the required parameter 'email' when calling PersonApi.lookup");
     }
     
    // create path and map variables
      String path = "https://person.clearbit.com/v2/combined/find";
//      String path = "https://person-stream.clearbit.com/v2/combined/find";
//    String path = "https://person.clearbit.com/v1/people/email/{email}"
//        .replaceAll("\\{" + "email" + "\\}", apiClient.escapeString(email.toString()));

//    if (webhookId != null) {
//      path += "?webhook_id=" + apiClient.escapeString(webhookId.toString());
//    }

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
      queryParams.add(new Pair("email", email));
      queryParams.add(new Pair("subscribe", "true"));
      queryParams.add(new Pair("webhook_id", webhook_id));
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();
    String accept = apiClient.selectHeaderAccept(new String[]{});
    String contentType = apiClient.selectHeaderContentType(new String[]{});

    String[] authNames = new String[] { "Basic Authentication" };

    TypeRef<String> returnType = new TypeRef<String>() {};
    String response = apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);

      if (response == null) {
          return null;
      }

      JsonNode nodes = json.deserialize(response);
      Person person = json.deserialize(nodes.get("person"), Person.class);
      Company company = json.deserialize(nodes.get("company"), Company.class);
      Map<String, Object> ret = new HashMap<String, Object>();
      ret.put("PERSON", person);
      ret.put("COMPANY", company);

      return ret;
  }

}
