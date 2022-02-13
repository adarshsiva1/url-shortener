
# URL Shortener

This service is comprised of a Java backend and a simple Bootstrap frontend.


To run the application, navigate to the root of the directory and run using mvn spring-boot:run.



## Assumptions

This application supports the requirements with the following assumptions.

- Users will enter URLs in a consistent manner i.e. they will not enter http://apple.com then enter http://www.apple.com.
- Given more time, the above requirement of text inconsistency could be solved for by scrubbing the unneccessary www segment and utilizing the pre-existing validation function. 
- Another assumption is that users will enter a consistent URL for a given target, for example, a layperson might enter http://iphone.com as opposed to https://apple.com/iphone and given more time, the service could be expanded to see if the redirected URL is the same. 

## Features

- An inputted URL will be rejected unless it is prefixed with http:// or https://
- A feature of this application is that it can support concurrent users as the Service class applies a synchronized keyword to avoid concurrent users from creating a duplicate short URL.


- In a future iteration, instead of making direct use of a hashmap, it would be desirable to instead use Redis to offer persistence and scalability, then allowing us to scale the number of web servers, while connecting to a common Redis instance. 


## Setup

Git clone this project to your local

```bash
  cd url-shortener
  mvn spring-boot:run
```


## Running Tests

Tests are located in the test directory and can be run using an IDE.

Test cases for controller:

1) Testing home page returns a result
2) Testing that submitting a long URL results in a redirect to the endpoint defined for the success page.
3) Testing that the endpoint defined for the success page then renders the success page.
4) Testing that when a GET is sent to the success page when a URL is added, that the short URL is properly presented back to the user.
5) Testing that when a short URL is accessed, that was previously defined, it will redirect to the actual target.

### Test cases for service class

1) Test that adding one URL will result in it being assigned a short URL of localhost:8080/u?url=1.
2) Testing that a second URL will result in a short URL of localhost:8080/u?url=2.
3) Testing that when a short URL is supplied, the long URL is returned.
4) Testing that when a URL is entered twice, the original short URL is provided, without duplication.


## Support

For support, email adarshsiva@gmail.com

