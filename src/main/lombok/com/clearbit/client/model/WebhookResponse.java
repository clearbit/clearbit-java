package com.clearbit.client.model;

import lombok.Data;

@Data
public class WebhookResponse {

  Type type;
  Object body;
  int status;
  String id;

  public void setId(String id) {
    this.id = id;
  }

  public void setStatus(int status) {
	this.status = status;
  }

  public void setType(Type type) {
	this.type = type;
  }

  public void setBody(Object obj) {
	this.body = obj;
  }
}
