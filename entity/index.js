'use strict'
const Generator = require('yeoman-generator');
const chalk = require('chalk');
var fs = require("fs");

module.exports = class extends Generator {
    constructor(args, opts) {
        super(args, opts);
    
        // This makes `appname` a required argument.
        this.argument('metadata', { type: String, required: true });
    
        // And you can then access it later; e.g.
        this.log(this.options.metadata);
        var content = fs.readFileSync(this.options.metadata);
        console.log("Output Content : \n"+ content);
      }

    writing() {
        console.log('writing')
    }
}