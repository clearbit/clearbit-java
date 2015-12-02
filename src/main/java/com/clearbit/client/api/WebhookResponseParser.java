package com.clearbit.client.api;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.clearbit.ApiException;
import com.clearbit.JSON;
import com.clearbit.client.model.Company;
import com.clearbit.client.model.Person;
import com.clearbit.client.model.Type;
import com.clearbit.client.model.WebhookResponse;
import com.fasterxml.jackson.databind.JsonNode;

public class WebhookResponseParser {

  private final JSON json = new JSON();
  private final String apiKey;
  
  public WebhookResponseParser(String apiKey) {
    this.apiKey = apiKey;
  }

  /**
   * @param body the request body
   * @param xRequestSignatureHeader value of the "X-Request-Signature" header
   */
  public WebhookResponse parse(String body, String xRequestSignatureHeader) throws ApiException {
    String signature = xRequestSignatureHeader.startsWith("sha1=")
        ? xRequestSignatureHeader.substring(5)
        : xRequestSignatureHeader;
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
    WebhookResponse response = new WebhookResponse();
    Type type = Type.valueOf(node.get("type").asText().toUpperCase());
    response.setType(type);
    switch (type) {
      case PERSON:
        response.setBody(json.deserialize(node.get("body"), Person.class));
        break;
      case COMPANY:
        response.setBody(json.deserialize(node.get("body"), Company.class));
        break;
      default:
        throw new ApiException("Unexpected type: " + type);
    }
    response.setId(node.get("id").asText());
    response.setStatus(node.get("status").asInt());
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
