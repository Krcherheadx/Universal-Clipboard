package dev.hisham.universal_clipboard.controller;

import dev.hisham.universal_clipboard.model.Clip;
import dev.hisham.universal_clipboard.repository.ClipsCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //This annotation tells the IoC to regist the class as a bean
@RequestMapping("/clips") //This anno. to map the request of path "/clips" to this controller
public class ClipsController {
    //We want  to create an instance of ClipCollectionRepository, Since we've already marked the underlined class with "@repository" anno. to register it in the IoC (Application Context) then we shouldn't create the instance normally, rather, we should use Dependency injection
    //DI (Dependency Injection) : is a design pattern that implement IoC by injecting the dependencies externally rather than creating them internally, the injected dependencies (e.g. ClipCollectionRepo) are indeed created by the IoC Container, So no need to create them, infact if you created the dependencies internally it will be created twice ,and you'll handle the dependency yourself instead of delegating it to the IoC

    private final ClipsCollectionRepository repository;

    @Autowired
    public ClipsController(ClipsCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Clip> findAllClips() {
        return repository.getClips();
    }

}