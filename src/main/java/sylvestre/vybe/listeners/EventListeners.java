package sylvestre.vybe.listeners;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListeners extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        User user = event.getUser();
        String emoji = event.getReaction().getReactionEmote().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();


        String message = user.getAsTag() + "reacted to a message with " + emoji + " in the " + channelMention + " channel!";
        event.getGuild().getDefaultChannel().sendMessage(message).queue();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if(message.contains("ping")) {
            event.getChannel().sendMessage("pong").queue();
        }
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        String avatar = event.getUser().getEffectiveAvatarUrl();
        System.out.println(avatar);
    }
}
