package sylvestre.vybe;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import sylvestre.vybe.listeners.EventListeners;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    private final ShardManager shardManager;
    private final Dotenv config;

    public DiscordBot(ShardManager shardManager, Dotenv config) {
        this.shardManager = shardManager;
        this.config = config;
    }


    /**
     * Loads environment variables and builds the bot shard manager
     * @throws LoginException occurs when bot token is invalid
     */

    public static void main(String[] args) throws LoginException {

        // Load environment variables
        Dotenv config = Dotenv.configure().load();
        String Token = config.get("TOKEN");

        // Setup shard manager
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(Token);
        TextChannel channel = builder.build().getTextChannelById("1031300681656705085");
        if (channel != null) {
            channel.sendMessage("Hello Vybist!").queue();
        }

        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("Elevate by Drake"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);



        ShardManager shardManager = builder.build();

        //Register Listeners
        shardManager.addEventListener(new EventListeners());
    }

    public Dotenv getConfig(){
        return config;
    }

    /**
     * Retrieves the bot shard manager
     * @return the shardManager instance for the bot
     */
    public ShardManager getShardManager() {
        return shardManager;
    }


}