'use strict'
const Generator = require('yeoman-generator');
const chalk = require('chalk');
var fs = require("fs");

module.exports = class extends Generator {
    constructor(args, opts) {
        super(args, opts);
        this.argument('metadata', { type: String, required: true });
        this.content = JSON.parse(fs.readFileSync(this.options.metadata));
        this.lokiContent = JSON.parse(fs.readFileSync('loki.json'));
    }

    writing() {
        const packageFolder = this.lokiContent.packageName.replace(/\./g, '/');
        const javaDir = 'src/main/java/' + packageFolder + '/';
        const javaDirTemplate = 'src/main/java/package/';

        this.content.entityDetails.forEach(entity => {
            entity.packageName = this.lokiContent.packageName;
            // Domain Generation
            this.fs.copyTpl(
                this.templatePath(javaDirTemplate + 'domain/Entity.java'),
                this.destinationPath(javaDir + 'domain/' + entity.entityName + '.java'), entity);
        });
    }
}