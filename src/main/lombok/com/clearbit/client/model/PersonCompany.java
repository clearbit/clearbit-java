package com.clearbit.client.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class PersonCompany {
  @JsonProperty Person person;
  @JsonProperty Company company;
}
