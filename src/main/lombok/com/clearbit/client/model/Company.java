package com.clearbit.client.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Company {

  @JsonProperty String id;
  @JsonProperty String name;
  @JsonProperty String legalName;
  @JsonProperty String domain;
  @JsonProperty List<String> domainAliases;
  @JsonProperty String logo;
  @JsonProperty Site site;
  @JsonProperty List<String> tags;
  @JsonProperty Category category;
  @JsonProperty String description;
  @JsonProperty Integer foundedYear;
  @JsonProperty String location;
  @JsonProperty String timeZone;
  @JsonProperty Long utcOffset;
  @JsonProperty Geo geo;
  @JsonProperty Metrics metrics;
  @JsonProperty Facebook facebook;
  @JsonProperty LinkedIn linkedin;
  @JsonProperty Twitter twitter;
  @JsonProperty Crunchbase crunchbase;
  @JsonProperty Boolean emailProvider;
  @JsonProperty String type;
  @JsonProperty String phone;
  @JsonProperty String indexedAt;
  @JsonProperty List<String> tech;

  @Data
  public static class Site {
    @JsonProperty String title;
    @JsonProperty String h1;
    @JsonProperty String metaDescription;
    @JsonProperty List<String> phoneNumbers;
    @JsonProperty List<String> emailAddresses;
  }

  @Data
  public static class Category {
    @JsonProperty String sector;
    @JsonProperty String industryGroup;
    @JsonProperty String industry;
    @JsonProperty String subIndustry;
  }

  @Data
  public static class Geo {
    @JsonProperty String streetNumber;
    @JsonProperty String streetName;
    @JsonProperty String subPremise;
    @JsonProperty String city;
    @JsonProperty String state;
    @JsonProperty String stateCode;
    @JsonProperty String postalCode;
    @JsonProperty String country;
    @JsonProperty String countryCode;
    @JsonProperty Double lat;
    @JsonProperty Double lng;
  }

  @Data
  public static class Metrics {
    @JsonProperty Long alexaUsRank;
    @JsonProperty Long alexaGlobalRank;
    @JsonProperty Long employees;
    @JsonProperty String employeesRange;
    @JsonProperty Long marketCap;
    @JsonProperty Long raised;
    @JsonProperty Long annualRevenue;
    @JsonProperty Long fiscalYearEnd;
  }

  @Data
  public static class Facebook {
    @JsonProperty String handle;
  }

  @Data
  public static class LinkedIn {
    @JsonProperty String handle;
  }

  @Data
  public static class Twitter {
    @JsonProperty String handle;
    @JsonProperty String id;
    @JsonProperty String bio;
    @JsonProperty Long followers;
    @JsonProperty Long following;
    @JsonProperty String location;
    @JsonProperty String site;
    @JsonProperty String avatar;
  }

  @Data
  public static class AngelList {
    @JsonProperty String handle;
    @JsonProperty String bio;
    @JsonProperty String blog;
    @JsonProperty String site;
    @JsonProperty Long followers;
    @JsonProperty String avatar;
  }

  @Data
  public static class Crunchbase {
    @JsonProperty String handle;
  }
}
