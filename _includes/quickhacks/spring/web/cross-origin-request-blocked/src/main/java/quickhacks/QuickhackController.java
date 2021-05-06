package quickhacks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuickhackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuickhackController.class);

    @CrossOrigin
    @PostMapping("/")
    public void addName(@RequestParam("name") final String name) {
        LOGGER.debug("Name: {}", name);
    }
}
