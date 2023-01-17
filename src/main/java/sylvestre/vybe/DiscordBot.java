package sylvestre.vybe;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import sylvestre.vybe.listeners.EventListeners;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    private final ShardManager shardManager;
    private final Dotenv config;


    /**
     * Loads environment variables and builds the bot shard manager
     * @throws LoginException occurs when bot token is invalid
     */
    public DiscordBot() throws LoginException {
        // Load environment variables
        config = Dotenv.configure().load();
        String Token = config.get("TOKEN");

        // Setup shard manager
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(Token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("Elevate by Drake"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        shardManager = builder.build();

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
    public static void main(String[] args) {
        try {
            DiscordBot discordBot = new DiscordBot();
        } catch (LoginException e) {
            System.out.println("ERROR: Provided bot token is invalid");
        }
    }
}