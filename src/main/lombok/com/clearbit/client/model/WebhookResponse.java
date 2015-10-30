package com.clearbit.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WebhookResponse<T> {

  T body;
  int status;
  String type;
  String id;

}
