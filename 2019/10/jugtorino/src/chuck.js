// camel-k:
//
// kamel run --dev src/chuck.js
//
// camel-k:
//
// kamel run --dev src/chuck.js
//
var JsonLibrary = Java.type('org.apache.camel.model.dataformat.JsonLibrary');
var Function    = Java.extend(java.util.function.Function);
var toJson      = new Function(JSON.parse);
var toJoke      = new Function(body => body.value.joke);

from('knative:endpoint/chuck')
    .id('chuck')
    .to('http://api.icndb.com/jokes/random?limitTo=[nerdy]&bridgeEndpoint=true')
    .transform()
        .body(java.lang.String, toJson)
    .transform()
        .body(toJoke)
    .log('${body}');
 