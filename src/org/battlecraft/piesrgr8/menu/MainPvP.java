package org.battlecraft.piesrgr8.menu;

import java.util.Arrays;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.battlecraft.piesrgr8.utils.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

@SuppressWarnings("deprecation")
public class MainPvP implements Listener {

	BattlecraftServer plugin;

	public MainPvP(BattlecraftServer p) {
		this.plugin = p;
	}

	public void teleportInWorld(Player player, World world, double x, double y, double z) {
		player.teleport(new Location(world, x, y, z));

		if (world == null) {
			player.sendMessage(Prefix.prefixHub + ChatColor.RED + "This world doesnt exist!");
		}
	}

	public static void openGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Main PvP");

		// Creating the items and registering them.

		Wool wool = new Wool(DyeColor.GREEN);
		ItemStack g = wool.toItemStack(1);
		ItemMeta g1 = g.getItemMeta();

		Wool wool1 = new Wool(DyeColor.GREEN);
		ItemStack a = wool1.toItemStack(1);
		ItemMeta a1 = a.getItemMeta();

		Wool wool2 = new Wool(DyeColor.GREEN);
		ItemStack b = wool2.toItemStack(1);
		ItemMeta b1 = b.getItemMeta();

		Wool wool3 = new Wool(DyeColor.RED);
		ItemStack c = wool3.toItemStack(1);
		ItemMeta c1 = c.getItemMeta();

		Wool wool4 = new Wool(DyeColor.RED);
		ItemStack d = wool4.toItemStack(1);
		ItemMeta d1 = d.getItemMeta();

		Wool wool5 = new Wool(DyeColor.RED);
		ItemStack e = wool5.toItemStack(1);
		ItemMeta e1 = e.getItemMeta();

		ItemStack bk = new ItemStack(Material.ARROW);
		ItemMeta bk1 = bk.getItemMeta();

		ItemStack air = new ItemStack(Material.LEGACY_STAINED_GLASS_PANE);
		ItemMeta air1 = air.getItemMeta();

		// Setting the meta, or names, of all of the blocks that will be added
		// to the inventory.

		g1.setDisplayName("1. " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Wood " + ChatColor.RESET + ChatColor.ITALIC
				+ ChatColor.BOLD + "VS. " + ChatColor.DARK_BLUE + ChatColor.BOLD + "Stone!");
		g1.setLore(Arrays.asList(ChatColor.GRAY + "" + ChatColor.ITALIC + "Click to join!"));
		g.setItemMeta(g1);

		a1.setDisplayName("2. " + ChatColor.GOLD + "" + ChatColor.BOLD + "Gold " + ChatColor.RESET + ChatColor.ITALIC
				+ ChatColor.BOLD + "VS. " + ChatColor.WHITE + ChatColor.BOLD + "Iron!");
		a1.setLore(Arrays.asList(ChatColor.GRAY + "" + ChatColor.ITALIC + "Click to join!"));
		a.setItemMeta(a1);

		b1.setDisplayName("3." + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Emerald " + ChatColor.RESET + ChatColor.ITALIC
				+ ChatColor.BOLD + "VS. " + ChatColor.AQUA + ChatColor.BOLD + "Diamond!");
		b1.setLore(Arrays.asList(ChatColor.GRAY + "" + ChatColor.ITALIC + "Click to join!"));
		b.setItemMeta(b1);

		c1.setDisplayName("4. " + ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Blue " + ChatColor.RESET + ChatColor.ITALIC
				+ ChatColor.BOLD + "VS. " + ChatColor.DARK_RED + ChatColor.BOLD + "Red!");
		c1.setLore(Arrays.asList(ChatColor.GRAY + "" + ChatColor.ITALIC + "Click to join!"));
		c.setItemMeta(c1);

		d1.setDisplayName("5. " + ChatColor.GREEN + "" + ChatColor.BOLD + "Green " + ChatColor.RESET + ChatColor.ITALIC
				+ ChatColor.BOLD + "VS. " + ChatColor.DARK_RED + ChatColor.BOLD + "Red!");
		d1.setLore(Arrays.asList(ChatColor.GRAY + "" + ChatColor.ITALIC + "Click to join!"));
		d.setItemMeta(d1);

		e1.setDisplayName("6. " + ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Blue " + ChatColor.RESET + ChatColor.ITALIC
				+ ChatColor.BOLD + "VS. " + ChatColor.GOLD + ChatColor.BOLD + "Gold!");
		e1.setLore(Arrays.asList(ChatColor.GRAY + "" + ChatColor.ITALIC + "Click to join!"));
		e.setItemMeta(e1);

		bk1.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "BACK!");
		bk.setItemMeta(bk1);

		air1.setDisplayName(ChatColor.BLACK + " ");
		air.setItemMeta(air1);

		// Set the items in their places.

		inv.setItem(0, g);
		inv.setItem(1, a);
		inv.setItem(2, b);
		inv.setItem(3, c);
		inv.setItem(4, d);
		inv.setItem(5, e);
		// MAINTENANCE FOR CREATIVE
		inv.setItem(6, air);
		inv.setItem(7, air);
		inv.setItem(8, bk);

		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		String test = "Test";
		if (!ChatColor.stripColor(e.getView().getTitle()).equalsIgnoreCase("Main PvP"))
			return;

		final Player p = (Player) e.getWhoClicked();
		e.setCancelled(true);

		if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR)
				|| !e.getCurrentItem().hasItemMeta()) {
			return;
		}

		// GAME 1
		if (e.getCurrentItem().getItemMeta().getDisplayName().toString().contains("Stone!")) {
			teleportInWorld(p, Bukkit.getWorld(test), 0, 4, 0);
			p.sendMessage(Prefix.prefixMain + ChatColor.GREEN + "Joined a game in " + ChatColor.GREEN + ""
					+ ChatColor.BOLD + "Main PvP.");
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					Bukkit.getServer().dispatchCommand(p, "mg join Battlecraft");
				}
			}, 40);

		}

		// GAME 2
		if (e.getCurrentItem().getItemMeta().getDisplayName().toString().contains("Iron!")) {
			teleportInWorld(p, Bukkit.getWorld(test), 0, 4, 0);
			p.sendMessage(Prefix.prefixMain + ChatColor.GREEN + "Joined a game in " + ChatColor.GREEN + ""
					+ ChatColor.BOLD + "Main PvP.");
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					Bukkit.getServer().dispatchCommand(p, "mg join Battlecraft2");
				}
			}, 40);

		}

		// GAME 3
		if (e.getCurrentItem().getItemMeta().getDisplayName().toString().contains("Diamond!")) {
			teleportInWorld(p, Bukkit.getWorld(test), 0, 4, 0);
			p.sendMessage(Prefix.prefixMain + ChatColor.GREEN + "Joined a game in " + ChatColor.GREEN + ""
					+ ChatColor.BOLD + "Main PvP.");
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					Bukkit.getServer().dispatchCommand(p, "mg join Battlecraft3");
				}
			}, 40);

		}

		// GAME 4
		if (e.getCurrentItem().getItemMeta().getDisplayName().toString().contains("Blue")) {
			teleportInWorld(p, Bukkit.getWorld(test), 0, 4, 0);
			p.sendMessage(Prefix.prefixMain + ChatColor.GREEN + "Joined a game in " + ChatColor.GREEN + ""
					+ ChatColor.BOLD + "Main PvP.");
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					Bukkit.getServer().dispatchCommand(p, "mg join Battlecraft4");
				}
			}, 40);

		}

		if (e.getCurrentItem().getItemMeta().getDisplayName().toString().contains("Red!")) {
			teleportInWorld(p, Bukkit.getWorld(test), 0, 4, 0);
			p.sendMessage(Prefix.prefixMain + ChatColor.GREEN + "Joined a game in " + ChatColor.GREEN + ""
					+ ChatColor.BOLD + "Main PvP.");
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					Bukkit.getServer().dispatchCommand(p, "mg join Battlecraft5");
				}
			}, 40);

		}

		if (e.getCurrentItem().getItemMeta().getDisplayName().toString().contains("Gold!")) {
			teleportInWorld(p, Bukkit.getWorld(test), 0, 4, 0);
			p.sendMessage(Prefix.prefixMain + ChatColor.GREEN + "Joined a game in " + ChatColor.GREEN + ""
					+ ChatColor.BOLD + "Main PvP.");
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					Bukkit.getServer().dispatchCommand(p, "mg join Battlecraft6");
				}
			}, 40);

		}

		if (e.getCurrentItem().getItemMeta().getDisplayName().toString().contains(ChatColor.stripColor("BACK"))) {
			NavGame.openGUI(p);
		}
	}

}
