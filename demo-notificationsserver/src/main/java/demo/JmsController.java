package demo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class JmsController {

    private Logger log = Logger.getLogger(this.getClass());

    private final List<SseEmitter> emitters = new ArrayList<>();

    @Autowired
    private JmsService jmsService;

    @RequestMapping(value = "send", method = RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
    public NotificationDTO sendMessage(@RequestBody NotificationDTO dto){
        jmsService.sendMessage(dto);
        return dto;
    }

    @RequestMapping(path = "/stream", method = RequestMethod.GET)    
    public SseEmitter stream() throws IOException {

        SseEmitter emitter = new SseEmitter();

        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));

        return emitter;
    }

    @JmsListener(destination = "${queue.name}", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(NotificationDTO dto) throws Exception{

        log.info("Got message" + dto);

        if(emitters!=null && emitters.size() > 0) {
            emitters.forEach((SseEmitter emitter) -> {
                try {
                    emitter.send(dto, MediaType.APPLICATION_JSON);
                } catch (IOException e) {
                    emitter.complete();
                    emitters.remove(emitter);
                    log.info(e);
                }
            });
        }else{
            log.info("No emitters");
        }

    }


}