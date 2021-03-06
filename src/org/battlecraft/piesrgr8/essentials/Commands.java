package org.battlecraft.piesrgr8.essentials;

import org.battlecraft.iHersh.ranks.RanksEnum;
import org.battlecraft.piesrgr8.BattlecraftServer;
import org.battlecraft.piesrgr8.chat.Chat;
import org.battlecraft.piesrgr8.chat.Send;
import org.battlecraft.piesrgr8.chat.Tell;
import org.battlecraft.piesrgr8.fake.FakeBan;
import org.battlecraft.piesrgr8.fake.FakeJoin;
import org.battlecraft.piesrgr8.fake.FakeKick;
import org.battlecraft.piesrgr8.fake.ForceOp;
import org.battlecraft.piesrgr8.fake.Spammer;
import org.battlecraft.piesrgr8.firework.FireworkJoin;
import org.battlecraft.piesrgr8.gadgets.Records;
import org.battlecraft.piesrgr8.hub.Hub;
import org.battlecraft.piesrgr8.inventory.Clear;
import org.battlecraft.piesrgr8.inventory.Enderchest;
import org.battlecraft.piesrgr8.inventory.Invsee;
import org.battlecraft.piesrgr8.menu.Menus;
import org.battlecraft.piesrgr8.particles.Particles;
import org.battlecraft.piesrgr8.party.PartyCmd;
import org.battlecraft.piesrgr8.players.Friends;
import org.battlecraft.piesrgr8.poll.Poll;
import org.battlecraft.piesrgr8.punish.Punish;
import org.battlecraft.piesrgr8.shop.Kiosk;
import org.battlecraft.piesrgr8.staff.Admin;
import org.battlecraft.piesrgr8.staff.Ask;
import org.battlecraft.piesrgr8.staff.StaffCommand;
import org.battlecraft.piesrgr8.stats.Stats;
import org.battlecraft.piesrgr8.support.Help;
import org.battlecraft.piesrgr8.support.Issue;
import org.battlecraft.piesrgr8.support.Report;
import org.battlecraft.piesrgr8.teleportation.Teleportation;
import org.battlecraft.piesrgr8.teleportation.Warp;
import org.battlecraft.piesrgr8.utils.ClickChat;
import org.battlecraft.piesrgr8.utils.MaintenanceCommand;
import org.battlecraft.piesrgr8.utils.Prefix;
import org.battlecraft.piesrgr8.utils.RestartCommand;
import org.battlecraft.piesrgr8.utils.Skulls;
import org.battlecraft.piesrgr8.utils.Test;
import org.battlecraft.piesrgr8.weapons.Armors;
import org.battlecraft.piesrgr8.weapons.CommandEnchant;
import org.battlecraft.piesrgr8.weapons.Weapons;
import org.battlecraft.piesrgr8.world.WorldHandler;
import org.battlecraft.piesrgr8.world.WorldSave;
import org.battlecraft.piesrgr8.world.WorldScanProx;
import org.battlecraft.piesrgr8.world.WorldTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

	BattlecraftServer plugin;

	public Commands(BattlecraftServer p) {
		this.plugin = p;
	}

	//Any command that is in this plugin is registered here.
	public static void registerCommands(BattlecraftServer plugin) {
		plugin.getCommand("admin").setExecutor(new Admin(plugin));
		plugin.getCommand("application").setExecutor(new ApplicationForm(plugin));
		plugin.getCommand("afk").setExecutor(new AFK(plugin));
		plugin.getCommand("armor").setExecutor(new Armors());
		plugin.getCommand("answer").setExecutor(new Ask());
		plugin.getCommand("ask").setExecutor(new Ask());
		plugin.getCommand("bc").setExecutor(new Commands(plugin));
		plugin.getCommand("chat").setExecutor(new Chat(plugin));
		plugin.getCommand("clear").setExecutor(new Clear());
		plugin.getCommand("death").setExecutor(new Health());
		plugin.getCommand("delwarp").setExecutor(new Warp());
		plugin.getCommand("enchant").setExecutor(new CommandEnchant(plugin));
		plugin.getCommand("enderchest").setExecutor(new Enderchest());
		plugin.getCommand("fakej").setExecutor(new FakeJoin());
		plugin.getCommand("fakel").setExecutor(new FakeJoin());
		plugin.getCommand("fakeb").setExecutor(new FakeBan());
		plugin.getCommand("fakek").setExecutor(new FakeKick());
		plugin.getCommand("find").setExecutor(new Find(plugin));
		plugin.getCommand("fly").setExecutor(new Fly());
		plugin.getCommand("forceop").setExecutor(new ForceOp(plugin));
		plugin.getCommand("friend").setExecutor(new Friends(plugin));
		plugin.getCommand("fw").setExecutor(new FireworkJoin(plugin));
		plugin.getCommand("g0").setExecutor(new Gamemode());
		plugin.getCommand("g1").setExecutor(new Gamemode());
		plugin.getCommand("g2").setExecutor(new Gamemode());
		plugin.getCommand("heal").setExecutor(new Health());
		plugin.getCommand("help").setExecutor(new Help());
		plugin.getCommand("hub").setExecutor(new Hub(plugin));
		plugin.getCommand("information").setExecutor(new Help());
		plugin.getCommand("invsee").setExecutor(new Invsee());
		plugin.getCommand("issue").setExecutor(new Issue());
		plugin.getCommand("jukebox").setExecutor(new Records(plugin));
		plugin.getCommand("kit").setExecutor(new Kits(plugin));
		plugin.getCommand("kiosk").setExecutor(new Kiosk(plugin));
		plugin.getCommand("maintenance").setExecutor(new MaintenanceCommand(plugin));
		plugin.getCommand("menu").setExecutor(new Menus(plugin));
		plugin.getCommand("nick").setExecutor(new Nick());
		plugin.getCommand("particle").setExecutor(new Particles(plugin));
		plugin.getCommand("party").setExecutor(new PartyCmd(plugin));
		plugin.getCommand("ping").setExecutor(new Ping(plugin));
		plugin.getCommand("poll").setExecutor(new Poll(plugin));
		plugin.getCommand("punish").setExecutor(new Punish(plugin));
		plugin.getCommand("reload").setExecutor(new RestartCommand(plugin));
		plugin.getCommand("report").setExecutor(new Report());
		plugin.getCommand("save").setExecutor(new WorldSave(plugin));
		plugin.getCommand("scan").setExecutor(new WorldScanProx());
		plugin.getCommand("send").setExecutor(new Send(plugin));
		plugin.getCommand("sethub").setExecutor(new Hub(plugin));
		plugin.getCommand("setwarp").setExecutor(new Warp());
		plugin.getCommand("skull").setExecutor(new Skulls(plugin));
		plugin.getCommand("spam").setExecutor(new Spammer(plugin));
		plugin.getCommand("spawn").setExecutor(new Hub(plugin));
		plugin.getCommand("staffchat").setExecutor(new StaffCommand());
		plugin.getCommand("stats").setExecutor(new Stats(plugin));
		plugin.getCommand("sword").setExecutor(new Weapons(plugin));
		plugin.getCommand("tell").setExecutor(new Tell(plugin));
		plugin.getCommand("test").setExecutor(new Test(plugin));
		plugin.getCommand("time").setExecutor(new WorldTime(plugin));
		plugin.getCommand("tp").setExecutor(new Teleportation());
		plugin.getCommand("tpc").setExecutor(new Teleportation());
		plugin.getCommand("tphere").setExecutor(new Teleportation());
		plugin.getCommand("updaterank").setExecutor(new RanksEnum(plugin));
		plugin.getCommand("vanish").setExecutor(new Invisibility(plugin));
		plugin.getCommand("vs").setExecutor(new Invisibility(plugin));
		plugin.getCommand("website").setExecutor(new ClickChat(plugin));
		plugin.getCommand("weather").setExecutor(new WorldHandler(plugin));
		plugin.getCommand("warp").setExecutor(new Warp());
		plugin.getCommand("whois").setExecutor(new Whois());
		plugin.getCommand("world").setExecutor(new WorldHandler(plugin));
	}

	// THIS IS THE RELOAD COMMAND & MAIN COMMAND
	//heckin memes boi
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("bc")) {
			if (!sender.hasPermission("bc.main")) {
				sender.sendMessage(Prefix.prefixMain + ChatColor.RED + "You don't have permission to use "
						+ ChatColor.YELLOW + "/bc!");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage(Prefix.prefixMain + ChatColor.GREEN + "Version: "
						+ plugin.getDescription().getVersion());
				sender.sendMessage(Prefix.prefixMain + ChatColor.GREEN + "Creator: " + ChatColor.GOLD
						+ plugin.getDescription().getAuthors());
				return true;
			}

			// Reloading the configuration

			if (args[0].equalsIgnoreCase("reload")) {
				if (args.length == 1) {
					plugin.reloadConfig();
					sender.sendMessage(
							Prefix.prefixMain + ChatColor.GREEN + "This has been passed successfully!");
					return true;
				}
			}

			// Broadcasting

			if (args.length > 0) {
				if (cmd.getName().equalsIgnoreCase("addp") || cmd.getName().equalsIgnoreCase("reload")) {
					return false;
				}

				String bc = "";
				for (String message : args) {
					bc = (bc + ChatColor.translateAlternateColorCodes('&', message) + " ");
				}
				Bukkit.getServer().broadcastMessage(Prefix.prefixMain + ChatColor.GREEN + bc);
			}
		}
		return true;
	}
}
