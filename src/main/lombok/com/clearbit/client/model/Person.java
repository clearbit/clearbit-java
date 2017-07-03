package com.clearbit.client.model;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class Person {

  @JsonProperty String id;
  @JsonProperty Name name;
  @JsonProperty String email;
  @JsonProperty String gender;
  @JsonProperty String location;
  @JsonProperty String timeZone;
  @JsonProperty Long utcOffset;
  @JsonProperty Geo geo;
  @JsonProperty String bio;
  @JsonProperty String site;
  @JsonProperty String avatar;
  @JsonProperty Employment employment;
  @JsonProperty Facebook facebook;
  @JsonProperty Github github;
  @JsonProperty Twitter twitter;
  @JsonProperty LinkedIn linkedin;
  @JsonProperty AboutMe aboutme;
  @JsonProperty Gravatar gravatar;
  @JsonProperty boolean fuzzy;
  @JsonProperty boolean emailProvider;
  @JsonProperty String indexedAt;

  @Data
  public static class Name {
    @JsonProperty String fullName;
    @JsonProperty String givenName;
    @JsonProperty String familyName;
  }

  @Data
  public static class Geo {
    @JsonProperty String city;
    @JsonProperty String state;
    @JsonProperty String stateCode;
    @JsonProperty String country;
    @JsonProperty String countryCode;
    @JsonProperty Double lat;
    @JsonProperty Double lng;
  }

  @Data
  public static class Employment {
    @JsonProperty String name;
    @JsonProperty String title;
    @JsonProperty String domain;
    @JsonProperty String role;
    @JsonProperty String seniority;
  }

  @Data
  public static class Facebook {
    @JsonProperty String handle;
  }

  @Data
  public static class Github {
    @JsonProperty String handle;
    @JsonProperty Long id;
    @JsonProperty String avatar;
    @JsonProperty String company;
    @JsonProperty String blog;
    @JsonProperty Long followers;
    @JsonProperty Long following;
  }

  @Data
  public static class Twitter {
    @JsonProperty String handle;
    @JsonProperty String id;
    @JsonProperty String bio;
    @JsonProperty Long followers;
    @JsonProperty Long following;
    @JsonProperty Long statuses;
    @JsonProperty Long favorites;
    @JsonProperty String location;
    @JsonProperty String site;
    @JsonProperty String avatar;
  }

  @Data
  public static class LinkedIn {
    @JsonProperty String handle;
  }

  @Data
  public static class AboutMe {
    @JsonProperty String handle;
    @JsonProperty String bio;
    @JsonProperty String avatar;
  }

  @Data
  public static class Gravatar {
    @JsonProperty String handle;
    @JsonProperty List<Url> urls;
    @JsonProperty String avatar;
    @JsonProperty List<Avatar> avatars;

    @Data
    public static class Url {
      @JsonProperty String value;
      @JsonProperty String title;
    }

    @Data
    public static class Avatar {
      @JsonProperty String url;
      @JsonProperty String type;
    }
  }
}
