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
import com.clearbit.client.model.PersonCompany;

public class CombinedApi {
  private final ApiClient apiClient;
  private final String URL = "https://person.clearbit.com/v2/combined/find";
  private final String STREAMING_URL = "https://person-stream.clearbit.com/v2/combined/find";

  public CombinedApi() {
    this(Configuration.getDefaultApiClient());
  }

  public CombinedApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  /**
   * Streaming Combined Lookup
   * Looks up a person and company simultaneously based on a email address.
   * This is a blocking operation. If the person or company are not in the Clearbit database,
   * the connection will be held open until the lookup has completed (typically 3-5 seconds).
   * @param email the person’s email address
   * @return PersonCompany
   */
  public PersonCompany streamingLookup(String email) throws ApiException {
    // verify the required parameters are set
    if (email == null) {
      throw new ApiException(400, "Missing the required parameter 'email' when calling CombinedApi.streamingLookup");
    }

    // query params
    List<Pair> params = new ArrayList<Pair>();
    params.add(new Pair("email", email.toString()));

    return doReq(STREAMING_URL, params);
  }

  /**
   * Combined Lookup
   * Looks up a person and company simultaneously based on a email address.
   * @param email the person’s email address
   * @return PersonCompany, which can initially be null. Registered webhook will receive actual response.
   */
  public PersonCompany lookup(String email) throws ApiException {
    return lookup(email, null);
  }

  public PersonCompany lookup(String email, String webhookId) throws ApiException {
    // verify the required parameter 'email' is set
    if (email == null) {
      throw new ApiException(400, "Missing the required parameter 'email' when calling CombinedApi.lookup");
    }

    // query params
    List<Pair> params = new ArrayList<Pair>();
    params.add(new Pair("email", email.toString()));
    if (webhookId != null) {
      params.add(new Pair("webhook_id", webhookId.toString()));
    }

    return doReq(URL, params);
  }

  // doReq handles the HTTP request to the API endpoint
  private PersonCompany doReq(String uri, List<Pair> queryParams) throws ApiException {
    Object postBody = null;
    byte[] postBinaryBody = null;

    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, Object> formParams = new HashMap<String, Object>();

    String accept = apiClient.selectHeaderAccept(new String[]{});
    String contentType = apiClient.selectHeaderContentType(new String[]{});
    String[] authNames = new String[] { "Basic Authentication" };

    TypeRef<PersonCompany> returnType = new TypeRef<PersonCompany>() {};
    return apiClient.invokeAPI(uri, "GET", queryParams, postBody, postBinaryBody, headerParams, formParams, accept, contentType, authNames, returnType);
  }
}
