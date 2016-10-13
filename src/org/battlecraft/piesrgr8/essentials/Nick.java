package org.battlecraft.piesrgr8.essentials;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Nick implements CommandExecutor {

	File f = new File("plugins/BattlecraftServer/players.yml");
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("nick")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Not a player!");
				return true;
			}
			Player p = (Player) sender;
			if (!p.hasPermission("bc.nick")) {
				p.sendMessage("You dont have permission to set your nickname");
				return true;
			}

			if (args.length == 0) {
				p.sendMessage("What will be your nickname?");
				return true;
			}

			if (args.length == 1) {
				String bc = "";
				for (String message : args) {
					bc = (bc + message + "");
				}
				
				p.setDisplayName("*" + ChatColor.translateAlternateColorCodes('&', yaml.getString(p.getName() + ".nick")));

				if (!yaml.contains(p.getName() + ".nick")) {
					yaml.createSection(p.getName() + ".nick");
				}

				yaml.set(p.getName() + ".nick", bc);
				try {
					yaml.save(f);
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendMessage("Successfully set nick name! Your nick is now " + p.getDisplayName());
				p.sendMessage("Loggout or do this command again if your nick doesnt appear.");
				return true;
			}
		}
		return true;
	}
}