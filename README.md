# Clearbit

A Java API client to [https://clearbit.com](https://clearbit.com).

## Installation

The client library can be imported from [Maven Central](https://mvnrepository.com/artifact/com.clearbit/client), or users can download source and build locally.

## Usage

Authorize requests by setting the API key found on your [account's settings page](https://clearbit.com/keys).

```java
import com.clearbit.ApiException;
import com.clearbit.client.api.CombinedApi;
import com.clearbit.client.model.PersonCompany;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClearbitExample {
	public static void main(String[] args) {
		final String clearbit_key = System.getenv("CLEARBIT_KEY");
		CombinedApi api = new CombinedApi();
		api.getApiClient().setUsername(clearbit_key);

		try {
			ObjectMapper mapper = new ObjectMapper();
			PersonCompany personCompany = api.streamingLookup("harlow@clearbit.com");

			// access attrs form response
			System.out.println(personCompany.getPerson().getEmail());

			// print full JSON payload
			String jsonBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(personCompany);
			System.out.println(jsonBody);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
}
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
  case PERSON_COMPANY:
    PersonCompany personCompany = (PersonCompany) response.getBody();
    break;
  default:
    throw new IllegalStateException("Unexpected type: " + response.getType());
}
```

## Supported APIs

Currently streaming for the following APIs is supported:

* [Person API](https://github.com/clearbit/clearbit-java/blob/master/src/main/java/com/clearbit/client/api/PersonApi.java#L32-L39)
* [Company API](https://github.com/clearbit/clearbit-java/blob/master/src/main/java/com/clearbit/client/api/CompanyApi.java#L32-L41)
* [Combined API](https://github.com/clearbit/clearbit-java/blob/master/src/main/java/com/clearbit/client/api/CombinedApi.java#L53-L58) (PersonCompany)

Please see the Clearbit docs for more information https://clearbit.com/docs

## Implementation Notes

Lombok is used in the model classes to efficiently represent the models without boilerplate for getters, setters, toStrings, etc. The code is delomboked during compilation so that the final output does not include any dependency on Lombok.

Download and install the Lombok plugin:
https://projectlombok.org/setup/eclipse

## Release

```
mvn release:prepare
mvn release:perform
```
