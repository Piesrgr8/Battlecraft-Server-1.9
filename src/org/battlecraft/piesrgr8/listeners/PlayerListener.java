package org.battlecraft.piesrgr8.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.battlecraft.iHersh.ranks.Permissions;
import org.battlecraft.iHersh.ranks.RanksEnum;
import org.battlecraft.iHersh.ranks.RanksEnum.Ranks;
import org.battlecraft.piesrgr8.BattlecraftServer;
import org.battlecraft.piesrgr8.ClanMain;
import org.battlecraft.piesrgr8.chat.Notifications;
import org.battlecraft.piesrgr8.clans.Clans;
import org.battlecraft.piesrgr8.config.PlayersYML;
import org.battlecraft.piesrgr8.party.Party;
import org.battlecraft.piesrgr8.players.Friends;
import org.battlecraft.piesrgr8.poll.Poll;
import org.battlecraft.piesrgr8.stats.StatsManager;
import org.battlecraft.piesrgr8.teleportation.PlayerTp;
import org.battlecraft.piesrgr8.utils.Color;
import org.battlecraft.piesrgr8.utils.Dynamicmotd;
import org.battlecraft.piesrgr8.utils.MaintenanceMain;
import org.battlecraft.piesrgr8.utils.PacketUtil;
import org.battlecraft.piesrgr8.utils.Prefix;
import org.battlecraft.piesrgr8.utils.ScoreboardMg;
import org.battlecraft.piesrgr8.utils.online.TimerDaily;
import org.battlecraft.piesrgr8.world.WorldTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

	BattlecraftServer plugin;
	ClanMain clanPL;
	Permissions perms = new Permissions(plugin);
	public static ArrayList<String> defaults = new ArrayList<String>();

	Clans clans = new Clans(clanPL);

	public PlayerListener(BattlecraftServer p) {
		this.plugin = p;
	}

	@EventHandler
	public void noDamageClan(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			Player p1 = (Player) e.getDamager();

			if (clans.isInClan(p) && clans.isInSameClan(p, p1)) {
				try {
					e.setCancelled(true);
				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString();
		final Player fromUUID = Bukkit.getServer().getPlayer(UUID.fromString(uuid));

		defaults.add(p.getName());

		// Set the join message for everyone to see.
		e.setJoinMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + fromUUID.getName()
				+ ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + " joined");

		// Check to see if anyone is invisible or not.

		// Save the friends list and start the daily timer.
		Friends.saveFriends(p);
		TimerDaily.timer(p);

		// For admins only. If dont have this perm, then this wont execute.
		if (RanksEnum.isAtLeast(p, Ranks.ADMIN)) {
			File f = new File("plugins//BattlecraftServer//players//" + fromUUID.getUniqueId().toString() + ".yml");
			YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);

			if (yaml.getBoolean(fromUUID.getName() + ".adminM")) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						fromUUID.sendMessage(Prefix.prefixAdmin + ChatColor.YELLOW
								+ "You have admin messages on. This means when people execute commands"
								+ " like /tp will be displayed to you. To toggle, use the /admin command.");
					}
				}, 150);
			}
		}

		File f = new File("plugins//BattlecraftServer//players//" + fromUUID.getUniqueId().toString() + ".yml");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);

		// We will create the stats list for the player IF they dont have one
		// already.
		StatsManager.createStats(p);

		// Add them to a list so that other players can teleport to them.
		PlayerTp.players.add(fromUUID.getName());

		// Lonely motd.
		motd(p);
		Poll.sendJoinMessage(p);
		
		//SET PERMS
		perms.setupPermissions(p);

		// SET TABLIST
		p.setPlayerListHeader(Color.c("&7&m----[-&r  &cBATTLECRAFT&r  &7&m-]----\n"));
		p.setPlayerListFooter(Color.c("\n&eVisit &bwww.bcpvp101.enjin.com &efor ranks and perks!"));

		// If the player isnt in the hub, they will be teleported back to the
		// hub.
		if (!fromUUID.getWorld().getName().equalsIgnoreCase("Hub1")) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					fromUUID.teleport(new Location(Bukkit.getServer().getWorld("Hub1"), 1041, 11, 586));
				}
			}, 25);
		}

		if (plugin.getConfig().getBoolean("titleonjoin") == true) {
			PacketUtil.onJoin(plugin, p);
		}

		if (!fromUUID.hasPlayedBefore()) {
			e.setJoinMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + fromUUID.getName()
					+ ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + " joined " + ChatColor.YELLOW + "("
					+ ChatColor.AQUA + "First Login!" + ChatColor.YELLOW + ")");
			PlayersYML.setFirstLogin(fromUUID);
		}

		// Add player's ip to yml
		String ip = p.getAddress().getAddress().toString();
		ip = ip.replaceAll("/", "");
		ip = ip.replaceAll("\\.", "-");
		yaml.set(p.getName(), ip);

		// But also add to a map for later uses.
		if (!(Dynamicmotd.motdPlayer.containsKey(ip))) {
			Dynamicmotd.motdPlayer.put(ip, p.getName());
		}
		// TODO

		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				WorldTime.onTimeChange();
			}
		}, 20, 50);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + p.getName()
				+ ChatColor.DARK_RED + "" + ChatColor.ITALIC + " left");
		PlayerTp.players.remove(p.getName());
		ScoreboardMg.removeHubBoard(p);
		PlayersYML.setLastLogin(p);
		perms.removePermissions(p);

		if (Party.isInParty(p)) {
			if (Party.getLeaderName(p).equals(p.getName())) {
				Party.stopParty(p);
			} else {
				Party.removePartyMember(p, Party.getLeaderName(p));
			}
		}
	}

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();

		if (MaintenanceMain.enabled == true && !p.isOp()) {

			e.disallow(Result.KICK_WHITELIST,
					Color.c("&7[&c&lBC&9&lMaintenance&7]&r\n"
							+ "&eSorry, but the server is currently in maintenance mode!\n"
							+ "&ePlease come back later when we are ready!"));
		}

		if (e.getResult() == Result.KICK_FULL && RanksEnum.isAtLeast(e.getPlayer(), Ranks.VIP)) {
			e.allow();
		}

		File f = new File("plugins//BattlecraftServer//players//" + p.getUniqueId().toString() + ".yml");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);

		if (yaml.contains(p.getName() + ".nick")) {
			p.setDisplayName("*" + ChatColor.translateAlternateColorCodes('&', yaml.getString(p.getName() + ".nick")));
		}
	}

	@SuppressWarnings("deprecation")
	public void motd(final Player p) {
		int on = Bukkit.getServer().getOnlinePlayers().size();
		p.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&7&l&m>&c&m--------&7&m&l[&6&m--------&7&m&l[&f &c&lBATTLECRAFT &7&m&l]&6&m--------&7&l&m]&c&m---------&7&l&m<"));
		p.sendMessage(
				ChatColor.translateAlternateColorCodes('&', "             &lWelcome, " + p.getDisplayName() + "!"));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "             &6&lGo to a portal to get started!"));
		p.sendMessage(
				ChatColor.translateAlternateColorCodes('&', "             &lType &a&l/help&r&l for more information."));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"             &lType &a&l/list&r&l to see who else is online."));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "             &lPlayers online:&a&l " + on + "&r"));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "             &lEnjoy your stay!"));
		p.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&7&l&m>&c&m--------&7&m&l[&6&m--------&7&m&l[&f &c&lBATTLECRAFT &7&m&l]&6&m--------&7&l&m]&c&m---------&7&l&m<"));

		Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
			public void run() {
				Notifications.notify(p);
			}
		}, 100);
	}
}