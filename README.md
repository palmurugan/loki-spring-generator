# Loki - Microservice Generator
[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)]()

is a Yeoman generator that creates a SpringBoot Microservice application with JWT / Keycloak SSO authentication.

## Entity Generation

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
{
  "entityDetails": [
    {
      "entityName": "User",
      "attributes": [
        {
          "name": "userName",
          "type": "String",
          "unique": true,
          "nullable": false,
          "minLength": 6,
          "maxLength": 30,
          "regex": {
            "pattern": "[A-Za-z0-9]+",
            "errorMessage": "Username should be alphanumeric"
          }
        },
        {
          "name": "password",
          "type": "String",
          "unique": false,
          "nullable": false
        },
        {
          "name": "email",
          "type": "String",
          "unique": true,
          "nullable": false
        },
        {
          "name": "age",
          "type": "Integer",
          "unique": false,
          "nullable": false
        }
      ],
      "mappings": [
        {
          "type": "OneToMany",
          "entity": "Role",
          "placeholder": "roles"
        }
      ]
    },
    {
      "entityName": "Role",
      "attributes": [
        {
          "name": "name",
          "type": "String",
          "unique": true,
          "nullable": false,
          "minLength": 4,
          "maxLength": 15,
          "regex": {
            "pattern": "[A-Za-z0-9]+",
            "errorMessage": "RoleName should be alphanumeric"
          }
        }
      ]
    }
  ]
}
```
# Report Issue
Please feel free to report an issue

https://github.com/palmurugan/loki-spring-generator/issues

# Author
PalMurugan C