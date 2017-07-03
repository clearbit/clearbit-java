package com.clearbit.client.model;

import lombok.Data;

@Data
public class WebhookResponse {
  Type type;
  Object body;
  int status;
  String id;
}
