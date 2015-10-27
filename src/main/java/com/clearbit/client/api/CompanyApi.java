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
import com.clearbit.client.model.Company;

public class CompanyApi {

  private ApiClient apiClient;

  public CompanyApi() {
    this(Configuration.getDefaultApiClient());
  }

  public CompanyApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  /**
   * Person API
   * The Person API lets you retrieve social information associated with an email address, such as a person’s name, location and Twitter handle.
   * @param email the person’s email address
   * @return Person
   */
  public Company stream(String domain) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameter 'email' is set
     if (domain == null) {
        throw new ApiException(400, "Missing the required parameter 'domain' when calling CompanyApi.stream");
     }
     
    // create path and map variables
    String path = "https://company-stream.clearbit.com/v1/companies/domain/{domain}".replaceAll("\\{" + "domain" + "\\}", apiClient.escapeString(domain.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    final String[] accepts = {};
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {};
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    String[] authNames = new String[] { "Basic Authentication" };

    TypeRef<Company> returnType = new TypeRef<Company>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
  }
  
}
