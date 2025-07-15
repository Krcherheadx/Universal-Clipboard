package dev.hisham.universal_clipboard.controller;

import dev.hisham.universal_clipboard.dto.CreateClipDTO;
import dev.hisham.universal_clipboard.dto.ResCreateClipDTO;
import dev.hisham.universal_clipboard.service.ClipService;
import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin()
@RestController //This annotation tells the IoC to register the class as a bean
@RequestMapping("/clips") //This anno. to map the request of path "/clips" to this controller
public class ClipController {
    //We want  to create an instance of ClipCollectionRepository, Since we've already marked the underlined class with "@repository" anno. to register it in the IoC (Application Context) then we shouldn't create the instance normally, rather, we should use Dependency injection
    //DI (Dependency Injection) : is a design pattern that implement IoC by injecting the dependencies externally rather than creating them internally, the injected dependencies (e.g. ClipCollectionRepo) are indeed created by the IoC Container, So no need to create them, infact if you created the dependencies internally it will be created twice ,and you'll handle the dependency yourself instead of delegating it to the IoC

//    private final ClipsCollectionRepository repository;
//
//    @Autowired
//    public ClipController(ClipsCollectionRepository repository) {
//        this.repository = repository;
//    }
//
//    @GetMapping("")
//    public List<ClipDTO> findAllClips() {
//        return repository.getClips();
//    }

    private final ClipService clipService;

    public ClipController(ClipService clipService) {
        this.clipService = clipService;
    }

    //TODO : implement authentication, deletion of the clip

    @MessageMapping("/add")
    @SendTo("/topic/clips")
    public ResCreateClipDTO addClip(@Valid @Payload CreateClipDTO createClipDTO, SimpMessageHeaderAccessor headerAccessor) {//user_id
        // must be extracted from the token
//        headerAccessor.getSessionAttributes().put("username", "lds");?
        return clipService.createClip(createClipDTO);

    }


}