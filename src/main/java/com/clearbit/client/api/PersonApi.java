package com.clearbit.client.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clearbit.ApiClient;
import com.clearbit.ApiException;
import com.clearbit.Configuration;
import com.clearbit.Pair;
import com.clearbit.TypeRef;
import com.clearbit.client.model.Person;

public class PersonApi {

  private ApiClient apiClient;

  public PersonApi() {
    this(Configuration.getDefaultApiClient());
  }

  public PersonApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  /**
   * The Person API lets you retrieve social information associated with an email address,
   * such as a person’s name, location and Twitter handle.
   * This is a blocking operation. If the person is not in the Clearbit database,
   * the connection will be held open until the lookup has completed. Typically 3-5 seconds.
   * @param email the person’s email address
   * @return Person
   */
  public Person streamingLookup(String email) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameters are set
     if (email == null) {
        throw new ApiException(400, "Missing the required parameter 'email' when calling PersonApi.streamingLookup");
     }
     
    // create path and map variables
    String path = "https://person-stream.clearbit.com/v1/people/email/{email}".replaceAll("\\{" + "email" + "\\}", apiClient.escapeString(email.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();
    String accept = apiClient.selectHeaderAccept(new String[]{});
    String contentType = apiClient.selectHeaderContentType(new String[]{});

    String[] authNames = new String[] { "Basic Authentication" };

    TypeRef<Person> returnType = new TypeRef<Person>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
  }

  /**
   * Person API
   * The Person API lets you retrieve social information associated with an email address, such as a person’s name, location and Twitter handle.
   * @param email the person’s email address
   * @return A cached Person, which may often be null. Registered webhook will receive actual response.
   */
  public Person lookup(String email, String webhookId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'email' is set
     if (email == null) {
        throw new ApiException(400, "Missing the required parameter 'email' when calling PersonApi.lookup");
     }
     
    // create path and map variables
    String path = "https://person.clearbit.com/v1/people/email/{email}"
        .replaceAll("\\{" + "email" + "\\}", apiClient.escapeString(email.toString()));

    if (webhookId != null) {
      path += "?webhook_id=" + apiClient.escapeString(webhookId.toString());
    }

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();
    String accept = apiClient.selectHeaderAccept(new String[]{});
    String contentType = apiClient.selectHeaderContentType(new String[]{});

    String[] authNames = new String[] { "Basic Authentication" };

    TypeRef<Person> returnType = new TypeRef<Person>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
  }

}
