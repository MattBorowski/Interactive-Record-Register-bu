package io.github.MattBorowski.hello;

import io.github.MattBorowski.lang.Lang;
import io.github.MattBorowski.lang.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

class HelloService {
    static final String FALLBACK_NAME= "world";
    static final Lang FALLBACK_LANG= new Lang(1, "Hello", "en");
    private final Logger logger= LoggerFactory.getLogger(HelloService.class);

    private LangRepository repository;

    HelloService(){
        this(new LangRepository());
    }
    HelloService(LangRepository repository){
        this.repository= repository;
    }

     String prepareGreeting(String name, Integer langId){
        langId= Optional.ofNullable(langId).orElse(FALLBACK_LANG.getId());
        var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
         return welcomeMsg +  " " + nameToWelcome +  "!";
     }
}
