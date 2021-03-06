package org.battlecraft.piesrgr8.chat;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.battlecraft.piesrgr8.chat.Notifications.NotifType;
import org.battlecraft.piesrgr8.config.PlayersYML;
import org.battlecraft.piesrgr8.staff.Admin;
import org.battlecraft.piesrgr8.utils.Color;
import org.battlecraft.piesrgr8.utils.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tell implements CommandExecutor {

	BattlecraftServer plugin;

	public Tell(BattlecraftServer p) {
		this.plugin = p;
	}

	String bg = Prefix.prefixChat;

	// Registering a command to be usable.
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("tell")) {
					if (args.length == 0) {
						sender.sendMessage(bg + ChatColor.RED + "/tell <player> <message>");
						return true;
					}

					Player tar = Bukkit.getServer().getPlayer(args[0]);
					// Checking to see if the target is null when they hit enter. Otherwise, it will
					// ask them for a message if nothing else is typed in.
					if (args.length == 1) {
						if (tar == null) {
							sender.sendMessage(bg + ChatColor.RED + "This player isnt online.");
							return true;
						}

						if (tar.getName().equalsIgnoreCase(args[0])) {
							sender.sendMessage(bg + ChatColor.RED + "What will the message be?");
							return true;
						}
					}

					// This will now send the message using the string build method.
					if (args.length > 1) {
						StringBuilder sb = new StringBuilder();
						String msg;
						for (int i = 1; i < args.length; i++)
							sb.append(args[i]).append(" ");
						msg = sb.toString();

						String msg1 = ChatColor.GOLD + "To " + ChatColor.GREEN + args[0] + 
								ChatColor.GOLD + ": ";
						String msg2 = ChatColor.LIGHT_PURPLE + "From" + ChatColor.GREEN + sender.getName() + 
								ChatColor.GOLD + ": ";

						if (tar == null) {
							OfflinePlayer st = Bukkit.getServer().getOfflinePlayer(args[0]);
							PlayersYML.setMessageList(st, msg2 + Color.c(msg), NotifType.MSG);
							sender.sendMessage(msg1 + Color.c(msg));
							sender.sendMessage(
									bg + "This player isn't online, but they will recieve this once they log on!");
							return true;

						} else {

							sender.sendMessage(msg1 + Color.c(msg));
							tar.sendMessage(msg2 + Color.c(msg));
							Admin.sendMessage(
									Color.c("&e" + sender.getName() + " &asaid to &e" + tar.getName() + ": &6" + msg));
						}
				}
			}
		return true;
	}
}
