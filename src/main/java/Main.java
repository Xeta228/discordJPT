import eventlistener.OnHightlightEventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties
                .load(new FileInputStream
                        ("src/main/resources/application.properties"));
        String DISCORD_TOKEN = properties.getProperty("discord_key");
        JDA jda = JDABuilder
                .createDefault(DISCORD_TOKEN)
                .setActivity(Activity.playing("chatJPA v 1.0")).build();
        jda.addEventListener(new OnHightlightEventListener());
    }
}
