# Clearbit

A Java API client to [https://clearbit.com](https://clearbit.com).

## Installation

You must currently checkout and build the source
TODO: deploy to Maven Central

## Usage

Authorize requests by setting the API key found on your [account's settings page](https://clearbit.com/keys).

```java
final String clearbit_key = System.getenv("CLEARBIT_KEY");
CompanyApi api = new CompanyApi();
api.getApiClient().setUsername(clearbit_key);
Company company = api.streamingLookup("clearbit.com");
```

This API is blocking by default - it holds a streaming connection open (this can take 3~5 seconds if we haven't seen the email before). We recommend using the webhook API for higher throughput.

To parse the response received from a webhook call, use the WebhookResponseParser:

```java
final String clearbit_key = System.getenv("CLEARBIT_KEY");
WebhookResponseParser parser = new WebhookResponseParser(clearbit_key);
WebhookResponse response = parser.parse(responseBody, xRequestSignatureHeader);
switch (response.getType()) {
  case PERSON:
    Person person = (Person) response.getBody();
    break;
  case COMPANY:
    Company company = (Company) response.getBody();
    break;
  default:
    throw new IllegalStateException("Unexpected type: " + response.getType());
}
```

## Supported APIs

Currently streaming for the following APIs is supported:

* Person
* Company

## Implementation Notes

Lombok is used in the model classes to efficiently represent the models without boilerplate for getters, setters, toStrings, etc. The code is delomboked during compilation so that the final output does not include any dependency on Lombok.
