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


        // POM.XML
        this.fs.copyTpl(this.templatePath('pom.xml'), this.destinationPath('pom.xml'), this.metadata);

        // Application Base Generation 
        this.fs.copyTpl(this.templatePath(javaDirTemplate + 'App.java'), this.destinationPath(javaDir + this.metadata.applicationName + '.java'), this.metadata);
    }
}