// camel-k:
//
// kamel run --dev src/source.groovy --secret telegram
//
from('telegram:bots')
    .setProperty('telegram.chat.id').header('CamelTelegramChatId')
    .convertBodyTo(String.class)
    .choice()
        .when().groovy('request.body == "chuck"')
            .to('knative:endpoint/chuck')
            .convertBodyTo(String.class)
            .setHeader('CamelTelegramChatId')
                .exchangeProperty('telegram.chat.id')
            .to('telegram:bots')
        .otherwise()
            .to('knative:channel/dev-null')
    .end()
