# pushnotifier

## pushnotifier-api

The pushnotifier-api defines the interface for the Android and iOS push message service pushnotifier by the gidix GmbH.
Implementations will base on this API project.

## pushnotifier-spring

[![Build Status](https://travis-ci.org/blackanthrax/pushnotifier-producer.svg?branch=master)](https://travis-ci.org/blackanthrax/pushnotifier-producer)
[![Code Coverage](https://img.shields.io/codecov/c/github/blackanthrax/pushnotifier-producer.svg)](https://codecov.io/github/blackanthrax/pushnotifier-producer)

The project pushnotifier-spring implements the pushnotifier-api and is made for the use in spring projects.
It is based on the spring RestTemplate class.

requirements:
- jdk 1.7+
- spring 3.0+

### how to use

You will need a RestTemplate bean in your application to satisfy the dependencies of pushnotifier-spring.
Also the @EnablePushNotifications annotation will autoconfigure the beans you need.


```java

@Configuration
@EnablePushNotifications
public class Configuration {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
```

Since pushnotifier still needs the password in addition to the API token I highly recommend to encrypt passwords
in the properties. Jasypt is a library that makes this possible. There is even spring support with the 
EncryptablePropertiesPlaceholderConfigurer.

```java
	@Bean
        public PBEConfig config(){
            EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
            config.setAlgorithm("PBEWithMD5AndDES");
            config.setPassword("jhzgtzcr392048978");
            return config;
        }
        
        @Bean
        public StringEncryptor encryptor(PBEConfig config){
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setConfig(config);
            return encryptor;
        }

	@Bean
        public PropertyPlaceholderConfigurer conf(StringEncryptor encryptor){
            EncryptablePropertyPlaceholderConfigurer ppc = new EncryptablePropertyPlaceholderConfigurer(encryptor);
            ppc.setLocation(new PathMatchingResourcePatternResolver().getResource("classpath:application.properties"));
            return ppc;
        }

```

The properties you need to add look like this.

```
pushnotifier.producer.packageName=com.github.blackanthrax.pn.spring
pushnotifier.producer.username=user
pushnotifier.producer.password=ENC(encpasswd)
pushnotifier.producer.apiToken=12guin23948r7fn
```
