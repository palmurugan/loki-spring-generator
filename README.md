# SpringBoot Application Generator
[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)]()

is a Yeoman generator that creates a SpringBoot Microservice application with JWT / Keycloak SSO authentication.

## Entity Generator

We can create an entity with different types of mappings (OneToMany, ManyToOne, OneToOne, ManyToMany). We can create an entities whith different databases like RDBMS, MongoDB, Casandra etc.

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

### Creating an Application
```sh
$ yo loki 
```

### Creating Entities
```sh
$ yo loki:entity <metadata location>

Ex: $ yo loki:entity ../metadata/metadata.json
```

### Sample Metadata for entity creation
```json
metadata.json

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
        },{
            "entityName": "Address",
            "attributes": [
                {
                    "name": "city",
                    "type": "String"
                },
                {
                    "name": "state",
                    "type": "String"
                }
            ]
        }
    ]
}
```

# Author
PalMurugan C