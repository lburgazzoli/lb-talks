// camel-k:
//
// kamel run --dev src/example.groovy
//
from('timer:example?period=1s')
    .setBody().constant('hello')
    .log('${body}')
