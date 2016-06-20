package com.clearbit.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WebhookResponse {

	Type type;
	Object body;
	int status;
	String id;

}
