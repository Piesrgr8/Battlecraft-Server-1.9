package org.battlecraft.piesrgr8.hub;

import java.util.Arrays;

import org.battlecraft.piesrgr8.essentials.Rulebook;
import org.battlecraft.piesrgr8.utils.Color;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HubInv {

	@SuppressWarnings("deprecation")
	public static void hubInv(final Player p) {
		
		String right = ChatColor.GRAY + "(Right-Click)";
		
		final Inventory inv = p.getInventory();

		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);

		final ItemStack ns = new ItemStack(Material.NETHER_STAR);
		ItemMeta meta = ns.getItemMeta();

		final ItemStack ns1 = new ItemStack(Material.END_CRYSTAL);
		ItemMeta meta1 = ns1.getItemMeta();
		
		final ItemStack ns2 = new ItemStack(Material.LEGACY_STAINED_CLAY, 1, DyeColor.GREEN.getDyeData());
		ItemMeta meta2 = ns2.getItemMeta();
		
		final ItemStack ns4 = new ItemStack(Material.CHEST);
		ItemMeta meta4 = ns4.getItemMeta();

		meta.setDisplayName(ChatColor.YELLOW + "Game Selector " + right);
		meta.setLore(Arrays.asList(Color.c("&e&oThis will allow you to teleport"), Color.c("&e&oto different games on the server!")));
		ns.setItemMeta(meta);

		meta1.setDisplayName(ChatColor.BLUE + "Quick Game " + right);
		meta1.setLore(Arrays.asList(Color.c("&e&oWhen you want to join a game quick,") , Color.c("&e&othen use this item!")));
		ns1.setItemMeta(meta1);
		
		meta2.setDisplayName(ChatColor.GREEN + "Vanish Players " + right);
		meta2.setLore(Arrays.asList(Color.c("&e&oPlayers in the hub will disappear.")));
		ns2.setItemMeta(meta2);
		
		meta4.setDisplayName(ChatColor.GOLD + "Collections " + right);
		meta4.setLore(Arrays.asList(Color.c("&e&oPlay records, use items, edit particles, and etc.!")));
		ns4.setItemMeta(meta4);

		if (!inv.contains(ns1) && !inv.contains(ns) && !inv.contains(ns4) && !inv.contains(book) && !inv.contains(ns2)) {
		inv.setItem(0, ns);
		inv.setItem(4, ns4);
		inv.setItem(5, Rulebook.rulebook(p));
		inv.setItem(7, ns2);
		inv.setItem(8, ns1);
		}
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000000, 2));
	}
}
