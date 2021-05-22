package quickhacks;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;

@Controller("/quickhack")
public class QuickhackController {

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "Example Response";
    }

    @Post
    public QuickhackReturnMessage postMethod(@Body QuickhackInputMessage inputMessage) {
        QuickhackReturnMessage retMessage = new QuickhackReturnMessage();
        retMessage.setReturnMessage("Hello " + inputMessage.getName() + ", thank you for sending the message");
        return retMessage;
    }
}

@Introspected
class QuickhackInputMessage {
    private String name;

    public QuickhackInputMessage() {
    }

    public QuickhackInputMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

@Introspected
class QuickhackReturnMessage {
    private String returnMessage;

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
