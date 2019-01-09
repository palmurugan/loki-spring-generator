# SpringBoot Application Generator

is a Yeoman generator that creates a SpringBoot Microservice application with JWT / Keycloak SSO authentication.

## Entity Generator

We can create an entity with different types of mappings (OneToMany, ManyToOne, OneToOne, ManyToMany). We can create an entities whith different databases like RDBMS, MongoDB, Casandra etc.

```
yo loki:entity <metadata location>

```

### Sample Metadata for entity creation
```
{
    "entityDetails": [
        {
            "entityName": "User",
            "attributes": [
                {
                    "name": "userName",
                    "type": "String"
                },
                {
                    "name": "age",
                    "type": "Integer"
                }
            ]
        }
    ]
}
```


## Table of Contents

- [Quick install guide](#quick-install-guide)
- [Running the app](#running)
- [Docker Integration](#docker)

## Quick install guide

You need to have [Node.js](https://nodejs.org) installed.

	$ npm install -g yo
	$ npm install -g generator-loki
	$ yo loki

## Running

We will update 

# Author
PalMurugan C