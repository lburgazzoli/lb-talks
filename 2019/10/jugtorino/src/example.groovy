// camel-k:
//
// kamel run --dev src/example.groovy
//
from('timer:groovy?period=1s')
    .setBody()
        .constant('Hello Camel K!')
    .to('log:info?showAll=false')
