package edu.unca.smmattic.Demo;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.google.common.base.Joiner;

/*
 * This is a sample CommandExectuor
 */
public class DemoCommandExecutor implements CommandExecutor {
	private final Demo plugin;

	/*
	 * This command executor needs to know about its plugin from which it came
	 * from
	 */
	public DemoCommandExecutor(Demo plugin) {
		this.plugin = plugin;
	}

	/*
	 * On command set the sample message
	 */
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length == 0) {
			return false;
		} else if (!(sender instanceof Player)) {
			return false;
			// the cake will appear on the ground but not
			// necessarily where the player is looking
		} else if (args[0].equalsIgnoreCase("cake")) {
			Player fred = (Player) sender;
			Location loc = fred.getLocation();
			World w = loc.getWorld();
			loc.setX(loc.getX() + 1);
			Block b = w.getBlockAt(loc);
			b.setTypeId(92);
			return true;
			// the stored message now always begins with
			// the word "message"--do you know how to easily
			// fix that problem?
		} else if (args[0].equalsIgnoreCase("message")
				&& sender.hasPermission("demo.message")) {
			this.plugin.getConfig().set("sample.message",
					Joiner.on(' ').join(args));
			return true;
		} else if (args[0].equalsIgnoreCase("kick")
				&& sender.hasPermission("demo.kick")) {
			Player fred = plugin.getServer().getPlayer(args[1]);
			if (fred != null) {
				fred.kickPlayer("you've been kicked off!");
				sender.sendMessage(ChatColor.RED + args[1] + " was kicked off");
				plugin.logger.info(args[1] + " has been kicked off");
			} else {
				sender.sendMessage(ChatColor.RED + args[1]
						+ " is not logged on");
			}
			return true; 
		}
		 else if (args[0].equalsIgnoreCase("fly")
				&& sender.hasPermission("demo.fly")) {
			Player fred = (Player) sender;
		    fred.setAllowFlight(true);
		
			return true; }
	
         else {
			return false;
		}
	}
}



