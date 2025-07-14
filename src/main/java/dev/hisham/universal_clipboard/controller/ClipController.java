package dev.hisham.universal_clipboard.controller;

import dev.hisham.universal_clipboard.dto.CreateClipDTO;
import dev.hisham.universal_clipboard.dto.ResCreateClipDTO;
import dev.hisham.universal_clipboard.service.ClipService;
import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    //I'll implement a websocket destination that will broadcast the incoming message
    @MessageMapping("/add")
    @SendTo("/topic/clips")
    public ResCreateClipDTO addClip(@Valid @Payload CreateClipDTO createClipDTO, SimpMessageHeaderAccessor headerAccessor) {//user_id
        // must be extracted from the token
//        headerAccessor.getSessionAttributes().put("username", "lds");?
        return clipService.createClip(createClipDTO);

    }
    

    @MessageMapping("/greeting")
    public void consoleWelcom(@Payload Map<String, String> name) {
        System.out.println(name.get("name"));
    }


    //This the way to have a dynamic path
//    @GetMapping("{id}")
//    public ClipDTO findClipById(@PathVariable String id) {
//
//        return repository.getClipById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.ACCEPTED, "Check the id"));
//    }

    //Same as nest.js in terms of annotations
    //Note that private modifier is meant to limit other classes accessing,BUT, it doesn't make the endpoint private ( it doesn't make any sense)
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("")
//    private Object test(@Valid @RequestBody ClipDTO clipDTO) {
//        System.out.println(clipDTO);
//        List<ClipDTO> clipDTOS = repository.addClip(clipDTO);
//        return clipDTOS;
//    }

    //I'll just try if double pathvariable (params) are legit in spring
    //It works!, hmm , I think the way that Spring deals with the paths is by splitting the path by "/", that means if you send a req. with a path /clips/Hisham , it will go to the endpoint that has one variable, BUT if the name is somehow "Hisham/sw" the below endpoint will be triggered. In nest.js/exprees.js the order of the endpoints matter ,but it seems in Spring the opposite. So, PAY ATTENTION !
    @GetMapping("{name}/{text}")
    public String twoVaraibles(@PathVariable String name, @PathVariable String text) {
        return name + "  " + text;
    }
}