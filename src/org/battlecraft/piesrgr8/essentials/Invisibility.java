package org.battlecraft.piesrgr8.essentials;

import java.util.ArrayList;
import java.util.List;

import org.battlecraft.iHersh.ranks.RanksEnum;
import org.battlecraft.iHersh.ranks.RanksEnum.Ranks;
import org.battlecraft.piesrgr8.BattlecraftServer;
import org.battlecraft.piesrgr8.utils.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class Invisibility implements CommandExecutor,Listener {

	String prefix = Prefix.prefixStealth;
	public static List<Player> vanish = new ArrayList<Player>();
	public static List<Player> invis = new ArrayList<Player>();
	
	BattlecraftServer plugin;
	
	public Invisibility(BattlecraftServer p) {
		this.plugin = p;
	}

	
	//This is for vanishing players that are seen on the server by the player.
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getLabel().equalsIgnoreCase("vs")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Use this in-game to vanish!");
				return false;
			}
			Player p = (Player) sender;

			if (!RanksEnum.isAtLeast((Player) sender, Ranks.MOD)) {
				sender.sendMessage(prefix + ChatColor.RED + "You don't have permission to vanish!");
				return false;
			}
			if (vanish.contains(p)) {
				showAllPlayers(p);
				p.sendMessage(prefix + ChatColor.GREEN + "All players are now visible!");
				vanish.remove(p);
				return true;

			} else if (!vanish.contains(p)) {
				hideAllPlayers(p);
				p.sendMessage(prefix + ChatColor.GREEN + "All players are now hidden!");
				vanish.add(p);
				return true;

			} else {
				return false;
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("vanish")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You must be a player in the game in order to use this command!");
				return true;
			}
			
			Player p = (Player) sender;
			
			if (!RanksEnum.isStaff(p)) {
				RanksEnum.sendErrorMessage(Ranks.HELPER);
			}
			
			if (invis.contains(p)) {
				showPlayer(p);
				p.sendMessage(ChatColor.GREEN + "You are now VISIBLE!");
				invis.remove(p);
				return true;
				
			} else if (!invis.contains(p)) {
				hidePlayer(p);
				p.sendMessage(ChatColor.GREEN + "You are now INVISIBLE!");
				invis.add(p);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Action a = e.getAction();
		ItemStack is = e.getItem();
		Player p = e.getPlayer();
		PlayerInventory inv = p.getInventory();
		
		ItemStack item1 = new ItemStack(Material.LEGACY_STAINED_CLAY, 1, DyeColor.GRAY.getDyeData());
		ItemMeta itemM1 = item1.getItemMeta();
		
		ItemStack item2 = new ItemStack(Material.LEGACY_STAINED_CLAY, 1, DyeColor.GREEN.getDyeData());
		ItemMeta itemM2 = item2.getItemMeta();
		
		itemM1.setDisplayName(ChatColor.GRAY + "Vanish Players (Right-Click)");
		item1.setItemMeta(itemM1);
		
		itemM2.setDisplayName(ChatColor.GREEN + "Vanish Players " + ChatColor.GRAY + "(Right-Click)");
		item2.setItemMeta(itemM2);

		if (a == Action.PHYSICAL || is == null || is.getType().equals(Material.AIR)) {
			return;
		}
		
		if (is.getType().equals(Material.CLAY) || is.getType().equals(Material.LEGACY_STAINED_CLAY) && is.hasItemMeta()) {
			e.setCancelled(true);
			if (Invisibility.vanish.contains(p)) {
				Invisibility.showAllPlayers(p);
				inv.setItem(7, item2);
				p.sendMessage(/*prefix + */ChatColor.GREEN + "All players are now visible!");
				Invisibility.vanish.remove(p);
				
			} else if (!Invisibility.vanish.contains(p)) {
				Invisibility.hideAllPlayers(p);
				inv.setItem(7, item1);
				p.sendMessage(/*prefix + */ChatColor.GREEN + "All players are now hidden!");
				Invisibility.vanish.add(p);

			} else {
				return;
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void showAllPlayers(Player player) {
		for (Player pl : Bukkit.getOnlinePlayers()) {
			player.showPlayer(pl);
		}
	}

	@SuppressWarnings("deprecation")
	public static void hideAllPlayers(Player player) {
		for (Player pl : Bukkit.getOnlinePlayers()) {
			player.hidePlayer(pl);
		}
	}
	
	//NOW FOR VANISH
	
	@SuppressWarnings("deprecation")
	public static void showPlayer(Player player) {
		for (Player pl : Bukkit.getOnlinePlayers()) {
			pl.showPlayer(player);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void hidePlayer(Player player) {
		for (Player pl : Bukkit.getOnlinePlayers()) {
			if (RanksEnum.isAtLeast(pl, Ranks.HELPER))
				continue;
			
			pl.hidePlayer(player);
		}
	}
}