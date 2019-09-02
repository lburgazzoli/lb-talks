import org.apache.camel.model.dataformat.JsonLibrary

from('knative:endpoint/chuck')
    .to('http://api.icndb.com/jokes/random?limitTo=[nerdy]&bridgeEndpoint=true')
    .unmarshal()
        .json(JsonLibrary.Jackson)
    .transform()
        .groovy('request.body.value.joke')
    .log('joke: ${body}')
