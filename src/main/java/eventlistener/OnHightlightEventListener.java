package eventlistener;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.ChatGptUtil;

import java.io.IOException;

public class OnHightlightEventListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getMentions().isMentioned(event.getJDA().getSelfUser())){
            System.out.println(event.getMessage().getContentRaw().substring(22));
            if(event.getMessage().getContentRaw().substring(22).isEmpty() || event.getMessage()
                    .getContentRaw().substring(22).isBlank()){
                event.getChannel().sendMessage("Please provide a message").queue();
            }
            else {
                try {
                    String answer = ChatGptUtil.getAnswer(event.getMessage().getContentRaw());
                    event.getChannel().sendMessage(answer).queue();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                catch (IllegalStateException e){
                    event.getChannel().sendMessage("Bad request, try again").queue();
                }
            }
        }
    }
}
