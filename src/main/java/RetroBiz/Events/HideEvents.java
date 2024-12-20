package RetroBiz.Events;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class HideEvents implements Listener {

    private final JavaPlugin _plugin;

    public HideEvents(JavaPlugin plugin){
        _plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void AsyncPlayerChatEvent(AsyncChatEvent event) {
        Player who = event.getPlayer();
        String message = event.signedMessage().message();

        if(message.startsWith("~hide ")){
            String args = message.substring(6);
            String[] sub_args = args.split(" ");
            if(sub_args.length != 2) {
                who.sendMessage("wrong params, my padavan");
                event.setCancelled(true);
                return;
            }
            Player player = Bukkit.getPlayer(sub_args[0]);
            Player hiden_player = Bukkit.getPlayer(sub_args[1]);
            if(player == null || hiden_player == null){
                who.sendMessage("wrong player, my padavan");
                event.setCancelled(true);
                return;
            }
            player.hidePlayer(_plugin, hiden_player);
            who.sendMessage(hiden_player.getName() + " is hidden from " + player.getName());
            event.setCancelled(true);
        }
    }

}
