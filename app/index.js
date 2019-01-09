'use strict'
const Generator = require('yeoman-generator');
const chalk = require('chalk');

module.exports = class extends Generator {
    async prompting() {
        this.metadata = await this.prompt([{
            type: 'input',
            name: 'applicationName',
            message: 'Application Name',
            default: 'LokiService'
        }, {
            type: 'input',
            name: 'packageName',
            message: 'Package Name',
            default: 'com.loki'
        }]);
    }

    writing() {
        const packageFolder = this.metadata.packageName.replace(/\./g, '/');
        const javaDir = 'src/main/java/' + packageFolder + '/';
        const resourceDir = 'src/main/resources/';
        const javaDirTemplate = 'src/main/java/package/';
        const resourceDirTemplate = 'src/main/resources/';

        // Loki.json
        this.fs.copyTpl(
            this.templatePath('loki.json'),
            this.destinationPath('loki.json'),
            this.metadata);

        // POM.XML
        this.fs.copyTpl(
            this.templatePath('pom.xml'),
            this.destinationPath('pom.xml'),
            this.metadata);

        // Application Base Generation 
        this.fs.copyTpl(
            this.templatePath(javaDirTemplate + 'App.java'),
            this.destinationPath(javaDir + this.metadata.applicationName + '.java'),
            this.metadata);

        // Configuration Generation
        this.fs.copyTpl(
            this.templatePath(javaDirTemplate + 'config/SwaggerConfiguration.java'),
            this.destinationPath(javaDir + 'config/SwaggerConfiguration.java'), this.metadata);

        // Utils Generation
        this.fs.copyTpl(
            this.templatePath(javaDirTemplate + 'utils/ApplicationConstants.java'),
            this.destinationPath(javaDir + 'utils/ApplicationConstants.java'), this.metadata);

        // BaseException Generation
        this.fs.copyTpl(
            this.templatePath(javaDirTemplate + 'web/exception/ExceptionTranslator.java'),
            this.destinationPath(javaDir + 'web/exception/ExceptionTranslator.java'),
            this.metadata);

        // Base Rest Generation
        this.fs.copyTpl(
            this.templatePath(javaDirTemplate + 'web/rest/LokiResource.java'),
            this.destinationPath(javaDir + 'web/rest/LokiResource.java'),
            this.metadata);

        // Resource generation
        this.fs.copyTpl(
            this.templatePath(resourceDirTemplate + 'application.properties'),
            this.destinationPath(resourceDir + 'application.properties'),
            this.metadata);


    }
}