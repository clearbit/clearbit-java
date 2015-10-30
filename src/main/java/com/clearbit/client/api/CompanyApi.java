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
   * The Company API lets you lookup company data via a domain name.
   * This is a blocking operation. If the company is not in the Clearbit database,
   * the connection will be held open until the lookup has completed. Typically 3-5 seconds.
   * @param domain the company's website domain
   * @return Company
   */
  public Company streamingLookup(String domain) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameters are set
     if (domain == null) {
        throw new ApiException(400, "Missing the required parameter 'domain' when calling CompanyApi.streamingLookup");
     }
     
    // create path and map variables
    String path = "https://company-stream.clearbit.com/v1/companies/domain/{domain}".replaceAll("\\{" + "domain" + "\\}", apiClient.escapeString(domain.toString()));

    // query params
    List<Pair> queryParams = new ArrayList<Pair>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();
    String accept = apiClient.selectHeaderAccept(new String[]{});
    String contentType = apiClient.selectHeaderContentType(new String[]{});

    String[] authNames = new String[] { "Basic Authentication" };

    TypeRef<Company> returnType = new TypeRef<Company>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
  }

  /**
   * Company API
   * The Company API lets you lookup company data via a domain name.
   * @param domain the company's website domain
   * @return Company
   */
  public Company lookup(String domain, String webhookId) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;
    
     // verify the required parameters are set
     if (domain == null) {
        throw new ApiException(400, "Missing the required parameter 'domain' when calling CompanyApi.lookup");
     }
     
    // create path and map variables
    String path = "https://company.clearbit.com/v1/companies/domain/{domain}"
        .replaceAll("\\{" + "domain" + "\\}", apiClient.escapeString(domain.toString()));

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

    TypeRef<Company> returnType = new TypeRef<Company>() {};
    return apiClient.invokeAPI(path, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
  }

}
