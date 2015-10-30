package com.clearbit.client.api;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.clearbit.ApiException;
import com.clearbit.JSON;
import com.clearbit.client.model.WebhookResponse;
import com.fasterxml.jackson.databind.JsonNode;

public class WebhookResponseParser {

  private final JSON json = new JSON();
  private final String apiKey;
  
  public WebhookResponseParser(String apiKey) {
    this.apiKey = apiKey;
  }

  public <T> WebhookResponse<T> parse(String body, String xRequestSignature, Class<T> returnType) throws ApiException {
    String signature = xRequestSignature.startsWith("sha1=") ? xRequestSignature.substring(5) : xRequestSignature;
    try {
      if (!signature.equals(hmacSha1(body, apiKey))) {
        throw new ApiException(401, "bad hmac-sha1 signature");
      }
    } catch (InvalidKeyException e) {
      throw new ApiException(401, "bad hmac-sha1 signature");
    } catch (SignatureException e) {
      throw new ApiException(401, "bad hmac-sha1 signature");
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException("this can't ever happen", e);
    }
    
    JsonNode node = json.deserialize(body);
    WebhookResponse<T> response = new WebhookResponse<T>();
    if (node.has("body")) {
      response.setBody(json.deserialize(node.get("body"), returnType));
    }
    if (node.has("id")) {
      response.setId(node.get("id").asText());
    }
    if (node.has("status")) {
      response.setStatus(node.get("status").asInt());
    }
    if (node.has("type")) {
      response.setType(node.get("type").asText());
    }
    return response;
  }

  private String hmacSha1(String data, String key)
      throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
      SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
      Mac mac = Mac.getInstance("HmacSHA1");
      mac.init(signingKey);
      return toHexString(mac.doFinal(data.getBytes()));
  }
  
  private static String toHexString(byte[] bytes) {
    Formatter formatter = new Formatter();
    try {
      for (byte b : bytes) {
        formatter.format("%02x", b);
      }
      return formatter.toString();
    } finally {
      formatter.close();
    }
  }

}
