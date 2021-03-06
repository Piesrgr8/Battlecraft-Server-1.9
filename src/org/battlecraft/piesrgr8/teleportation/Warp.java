package org.battlecraft.piesrgr8.teleportation;


import java.io.File;
import java.io.IOException;

import org.battlecraft.iHersh.ranks.RanksEnum;
import org.battlecraft.iHersh.ranks.RanksEnum.Ranks;
import org.battlecraft.piesrgr8.staff.Admin;
import org.battlecraft.piesrgr8.utils.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setwarp")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Prefix.prefixWarp + ChatColor.RED + "You are not a player!");
				return true;
			}
			Player p = (Player) sender;
			if (!RanksEnum.isAtLeast((Player) sender, Ranks.ADMIN)) {
				p.sendMessage(
						Prefix.prefixWarp + ChatColor.RED + "You dont have permission to set up a warp.");
				return true;
			}
			if (args.length == 0) {
				p.sendMessage(Prefix.prefixWarp + ChatColor.RED + "What will the name be?");
				return true;
			}

			if (args.length == 1) {
				String bc = "";
				for (String message : args) {
					bc = (bc + message + "");
				}
				
				setupWarp(p, bc);
				
				p.sendMessage(Prefix.prefixWarp + ChatColor.GREEN + "Successfully created warp!");
				Admin.sendMessage(ChatColor.YELLOW + sender.getName() + ChatColor.GREEN + " created warp "
						+ ChatColor.YELLOW + bc + "!");
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("delwarp")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Prefix.prefixWarp + ChatColor.RED + "You are not a player!");
				return true;
			}
			Player p = (Player) sender;
			if (!RanksEnum.isAtLeast((Player) sender, Ranks.ADMIN)) {
				p.sendMessage(
						Prefix.prefixWarp + ChatColor.RED + "You dont have permission to set up a warp.");
				return true;
			}
			if (args.length == 0) {
				p.sendMessage(Prefix.prefixWarp + ChatColor.RED + "What is the name of the warp?");
				return true;
			}

			if (args.length == 1) {
				String bc = "";
				for (String message : args) {
					bc = (bc + message + "");
				}
				
				if (!isAWarp(bc)) {
					p.sendMessage(Prefix.prefixWarp + ChatColor.RED + "This warp does not exist!");
					return true;
				}
				
				deleteWarp(bc);
				
				p.sendMessage(Prefix.prefixWarp + ChatColor.GREEN + "Successfully deleted warp!");
				Admin.sendMessage(ChatColor.YELLOW + sender.getName() + ChatColor.GREEN + " deleted warp "
						+ ChatColor.YELLOW + bc + "!");
			}
		}

		if (cmd.getName().equalsIgnoreCase("warp")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Prefix.prefixWarp + ChatColor.RED + "You are not a player!");
				return true;
			}
			Player p = (Player) sender;

			if (args.length == 0) {
				p.sendMessage(Prefix.prefixWarp + ChatColor.RED + "Arguments are: /warp <name : list>");
				return true;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("list")) {
					File f1 = getFolder();
					String[] names = f1.list();
					StringBuilder sb = new StringBuilder();
	                for(int i = 0; i < names.length; i++)
	                {
	                    sb.append(names[i]).append(" ");
	                }
	                String msg = sb.toString().trim();
	                p.sendMessage(Prefix.prefixWarp + ChatColor.YELLOW + "Warps: " + ChatColor.GREEN + msg.replace(".yml", ","));
					return true;
				}
				
				if (!getYaml(args[0]).contains(args[0])) {
					p.sendMessage(Prefix.prefixWarp + ChatColor.RED + "Warp doesnt exist");
					return true;
				}

				p.sendMessage(Prefix.prefixWarp + ChatColor.GREEN + "Warp successful");
				Admin.sendMessage(ChatColor.YELLOW + sender.getName() + ChatColor.GREEN + " went to warp " + ChatColor.YELLOW + args[0]);
				p.teleport(new Location(Bukkit.getWorld(getYaml(args[0]).getString(args[0] + ".world")),
						getYaml(args[0]).getDouble(args[0] + ".xPos"), getYaml(args[0]).getDouble(args[0] + ".yPos"),
						getYaml(args[0]).getDouble(args[0] + ".zPos")));
			}
		}
		return true;
	}
	
	public static void setupWarp(Player p, String name) {
		File f = new File("plugins//BattlecraftServer//warps//" + name + ".yml");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
		
		yaml.createSection(name);
		yaml.createSection(name + ".world");
		yaml.createSection(name + ".xPos");
		yaml.createSection(name + ".yPos");
		yaml.createSection(name + ".zPos");
		yaml.set(name + ".world", p.getLocation().getWorld().getName());
		yaml.set(name + ".xPos", p.getLocation().getBlockX());
		yaml.set(name + ".yPos", p.getLocation().getBlockY());
		yaml.set(name + ".zPos", p.getLocation().getBlockZ());
		try {
			yaml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteWarp(String name) {
		File f = new File("plugins//BattlecraftServer//warps//" + name + ".yml");
		f.delete();
	}
	
	public static YamlConfiguration getYaml(String n) {
		File f = new File("plugins//BattlecraftServer//warps//" + n + ".yml");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
		
		return yaml;
	}
	
	public static File getFolder() {
		File f = new File("plugins//BattlecraftServer//warps//");
		return f;
	}
	
	public static boolean isAWarp(String n) {
		File f = new File("plugins//BattlecraftServer//warps//" + n + ".yml");
		if (f.getName().equals(n)) {
			return true;
		}
		return false;
	}
}
