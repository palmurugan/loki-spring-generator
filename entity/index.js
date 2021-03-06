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

            // Domain Creation
            this.fs.copyTpl(
                this.templatePath(javaDirTemplate + 'domain/Entity.java'),
                this.destinationPath(javaDir + 'domain/' + entity.entityName + '.java'), entity);

            // DTO Creation
            this.fs.copyTpl(
                this.templatePath(javaDirTemplate + 'dto/Dto.java'),
                this.destinationPath(javaDir + 'dto/' + entity.entityName + 'DTO.java'), entity);

            // Mapper Creation
            this.fs.copyTpl(
                this.templatePath(javaDirTemplate + 'mapper/Mapper.java'),
                this.destinationPath(javaDir + 'mapper/' + entity.entityName + 'Mapper.java'), entity);

            // Validator Creation
            this.fs.copyTpl(
                this.templatePath(javaDirTemplate + 'validator/Validator.java'),
                this.destinationPath(javaDir + 'validator/' + entity.entityName + 'Validator.java'), entity);

            // Repository Creation
            this.fs.copyTpl(
                this.templatePath(javaDirTemplate + 'repository/Repository.java'),
                this.destinationPath(javaDir + 'repository/' + entity.entityName + 'Repository.java'), entity);

            // Service Interface Creation
            this.fs.copyTpl(
                this.templatePath(javaDirTemplate + 'service/Service.java'),
                this.destinationPath(javaDir + 'service/' + entity.entityName + 'Service.java'), entity);

            // Service Implementation Creation
            this.fs.copyTpl(
                this.templatePath(javaDirTemplate + 'service/impl/ServiceImpl.java'),
                this.destinationPath(javaDir + 'service/impl/' + entity.entityName + 'ServiceImpl.java'), entity);

            // Resource Creation
            this.fs.copyTpl(
                this.templatePath(javaDirTemplate + 'web/rest/Resource.java'),
                this.destinationPath(javaDir + 'web/rest/' + entity.entityName + 'Resource.java'), entity);

        });
    }
}