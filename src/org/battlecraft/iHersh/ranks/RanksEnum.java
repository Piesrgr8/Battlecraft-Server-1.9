package org.battlecraft.iHersh.ranks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.battlecraft.piesrgr8.utils.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class RanksEnum implements Listener, CommandExecutor {

	public static Map<Player, Enum<Ranks>> arrayRanks = new HashMap<Player, Enum<Ranks>>();
	public static ArrayList<Player> admin = new ArrayList<Player>();
	public static ArrayList<Player> staff = new ArrayList<Player>();
	static File f = new File("plugins/BattlecraftServer/ranks.yml");
	static YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);

	public enum Ranks {
		OWNER("owner"), // 4
		COWNER("cowner"), // 4
		DEV("dev"), // 4
		LEADER("leader"), // 4
		ADMIN("admin"), // 4
		SRMOD("srmod"), // c
		MOD("mod"), // c
		HELPER("helper"), // c

		BUILDER("builder"), // 9
		ARCHITECT("architect"), // 9
		VIP("vip"), // a
		VIPPLUS("vipplus"), // ab
		PLUSVIPPLUS("plusvipplus"), // ab
		PREMIUM("premium"), // d
		MASTER("master"), // e

		DEFAULT("default");
		
		private final String name;
		
		Ranks(String rank) {
			this.name = rank;
		}
		
		public String toString() {
			return name;
		}
	}

	public RanksEnum(BattlecraftServer battlecraftServer) {

	}

	/*
	 * public static String getPrefix(Enum<Ranks> e) {
	 * 
	 * if (e != null) { if (e.equals(Ranks.OWNER)) return "&4&lOWNER&r"; else if
	 * (e.equals(Ranks.COWNER)) return "&4&lCO-OWNER"; else if
	 * (e.equals(Ranks.DEV)) return "&4&lDEV&r"; else if
	 * (e.equals(Ranks.LEADER)) return "&6&lLEADER&r"; else if
	 * (e.equals(Ranks.ADMIN)) return "&c&lADMIN&r"; else if
	 * (e.equals(Ranks.DEV)) return "&c&lDEV&r"; else if (e.equals(Ranks.MOD))
	 * return "&6&lMOD&r"; else if (e.equals(Ranks.SRMOD)) return
	 * "&6&lSR.MOD&r"; else if (e.equals(Ranks.HELPER)) return "&3&lHELPER&r";
	 * 
	 * else if (e.equals(Ranks.BUILDER)) return "&9&lBUILDER&r"; else if
	 * (e.equals(Ranks.ARCHITECT)) return "&9&lARCHITECT&r"; else if
	 * (e.equals(Ranks.VIP)) return "&a&lVIP&r"; else if
	 * (e.equals(Ranks.VIPPLUS)) return "&a&lVIP&b&l+&r"; else if
	 * (e.equals(Ranks.PLUSVIPPLUS)) return "&b&l+&a&lVIP&b&l+&r"; else if
	 * (e.equals(Ranks.PREMIUM)) return "&6&lPREMIUM&r"; else if
	 * (e.equals(Ranks.MASTER)) return "&3&lMASTER&r"; }
	 * 
	 * return ""; }
	 */

	public static String getPrefix(Enum<Ranks> e) {

		if (e != null) {
			if (e.equals(Ranks.OWNER))
				return "&8[&4&lOWNER&8]&4&o";
			else if (e.equals(Ranks.COWNER))
				return "&8[&4&lCO-OWNER&8]&4&o";
			else if (e.equals(Ranks.DEV))
				return "&8[&c&l&oDEV&8]&c&o";
			else if (e.equals(Ranks.ADMIN))
				return "&8[&c&lADMIN&8]&c&o";
			else if (e.equals(Ranks.SRMOD))
				return "&8[&6&lSR.MOD&8]&6&o";
			else if (e.equals(Ranks.MOD))
				return "&8[&6&lMod&8]&6";
			else if (e.equals(Ranks.HELPER))
				return "&8[&2&lHELPER&8]&2&o";

			else if (e.equals(Ranks.BUILDER))
				return "&8[&9&lBUILDER&8]&9&o";
			else if (e.equals(Ranks.ARCHITECT))
				return "&8[&9ARCHITECT&8]&9&o";

			else if (e.equals(Ranks.VIP))
				return "&8[&a&lVIP&8]&r";
			else if (e.equals(Ranks.VIPPLUS))
				return "&8[&a&lVIP&b+&8]&r";
			else if (e.equals(Ranks.PLUSVIPPLUS))
				return "&8[&b+&a&lVIP&b+&8]&r";
			else if (e.equals(Ranks.PREMIUM))
				return "&8[&6&l&oPremium&8]&r";
			else if (e.equals(Ranks.MASTER))
				return "&8[&5&l&oMaster&8]&r";
		}

		return "";
	}

	// only to make the file for ranks. used in Prefix.java as an
	// initiator
	public static void startRanks(BattlecraftServer plugin) {
		if (!new File(plugin.getDataFolder(), "ranks.yml").exists()) {
			try {
				new File(plugin.getDataFolder(), "ranks.yml").createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// probably gonna be used for /updaterank <player> <rank>
	public static void setRank(Player p, Enum<Ranks> e) {
		arrayRanks.put(p, e);
	}

	// This checks whether the player <p> is a staff member (Includes helper)
	public static boolean isStaff(Player p) {
		if (arrayRanks.get(p) == Ranks.OWNER || arrayRanks.get(p) == Ranks.COWNER || arrayRanks.get(p) == Ranks.LEADER
				|| arrayRanks.get(p) == Ranks.DEV || arrayRanks.get(p) == Ranks.ADMIN
				|| arrayRanks.get(p) == Ranks.SRMOD || arrayRanks.get(p) == Ranks.MOD
				|| arrayRanks.get(p) == Ranks.ARCHITECT || arrayRanks.get(p) == Ranks.BUILDER || arrayRanks.get(p) == Ranks.HELPER) {
			return true;
		} else
			return false;
	}
	
	public static boolean isOfflinePlayerStaff(OfflinePlayer p) {
		if (arrayRanks.get(p) == Ranks.OWNER || arrayRanks.get(p) == Ranks.COWNER || arrayRanks.get(p) == Ranks.LEADER
				|| arrayRanks.get(p) == Ranks.DEV || arrayRanks.get(p) == Ranks.ADMIN
				|| arrayRanks.get(p) == Ranks.SRMOD || arrayRanks.get(p) == Ranks.MOD
				|| arrayRanks.get(p) == Ranks.ARCHITECT || arrayRanks.get(p) == Ranks.BUILDER || arrayRanks.get(p) == Ranks.HELPER) {
			return true;
		} else
			return false;
	}
	
	public static boolean isStaffOnline() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (arrayRanks.get(p) == Ranks.OWNER || arrayRanks.get(p) == Ranks.COWNER || arrayRanks.get(p) == Ranks.LEADER
					|| arrayRanks.get(p) == Ranks.DEV || arrayRanks.get(p) == Ranks.ADMIN
					|| arrayRanks.get(p) == Ranks.SRMOD || arrayRanks.get(p) == Ranks.MOD
					|| arrayRanks.get(p) == Ranks.ARCHITECT || arrayRanks.get(p) == Ranks.BUILDER || arrayRanks.get(p) == Ranks.HELPER) {
				return true;
			} else
				return false;
		}
		return false;
	}
	
	public static boolean isNotStaff(Player p) {
		if (arrayRanks.get(p) == Ranks.DEFAULT || arrayRanks.get(p) == Ranks.VIP || arrayRanks.get(p) == Ranks.VIPPLUS
				|| arrayRanks.get(p) == Ranks.PLUSVIPPLUS || arrayRanks.get(p) == Ranks.MASTER
				|| arrayRanks.get(p) == Ranks.PREMIUM) {
			return true;
		} else
			return false;
	}

	// Just to get the rank of the player, will be used everywhere and many
	// times.
	public static Enum<Ranks> getRank(Player p) {
		if (arrayRanks.containsKey(p)) {
			return arrayRanks.get(p);
		} else
			return null;
	}

	public static Enum<Ranks> getOfflineRank(OfflinePlayer p) {
		if (arrayRanks.containsKey(p)) {
			return arrayRanks.get(p);
		} else
			return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (cmd.getLabel().equalsIgnoreCase("updaterank")) {

				if (RanksEnum.isAtLeast(p, Ranks.DEV)) {
					Player target = Bukkit.getPlayer(args[0]);
					Enum<Ranks> rank = getEnum(args[1]);

					arrayRanks.put(target, rank);
					yaml.set(target.getName(), rank.toString());
					try {
						yaml.save(f);
					} catch (IOException e) {
						e.printStackTrace();
					}

					p.sendMessage(c(Prefix.prefixRanks + target.getName() + "'s rank is now &6" + rank.toString()));
					return true;
				} else {
					p.sendMessage(c(Prefix.prefixRanks + "This requires permission rank [&6DEV&7]."));
					return true;
				}
			}
		} else {
			if (label.equalsIgnoreCase("updaterank")) {
				ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

				Player target = Bukkit.getPlayer(args[0]);
				Enum<Ranks> rank = getEnum(args[1]);

				arrayRanks.put(target, rank);
				yaml.set(target.getName(), rank.toString());
				try {
					yaml.save(f);
				} catch (IOException e) {
					e.printStackTrace();
				}

				console.sendMessage(
						c(Prefix.prefixRanks + "&6" + target.getName() + "&7's rank is now &6" + rank.toString()));
				return true;
			}
		}
		return false;
	}

	// I decided to make it so if the player's name isn't in the config they're
	// considered default. Makes my life easier.
	// Day 2: It did NOT make my life easier.
	/*
	 * @EventHandler public void onJoin(PlayerJoinEvent e) { Player p =
	 * e.getPlayer();
	 * 
	 * if() }
	 */

	// Checks to see if the player is at least x rank (in terms of staff
	// hierarchy)
	public static boolean isAtLeast(Player p, Enum<Ranks> e) {

		if (RanksEnum.getRank(p) != null) {

			if (e.equals(Ranks.DEFAULT)) {
				if (RanksEnum.getRank(p).equals(Ranks.ARCHITECT) || RanksEnum.getRank(p).equals(Ranks.BUILDER) || RanksEnum.getRank(p).equals(Ranks.SRMOD) || RanksEnum.getRank(p).equals(Ranks.VIP) || RanksEnum.getRank(p).equals(Ranks.VIPPLUS)
						|| RanksEnum.getRank(p).equals(Ranks.PLUSVIPPLUS) || RanksEnum.getRank(p).equals(Ranks.MASTER)
						|| RanksEnum.getRank(p).equals(Ranks.PREMIUM) || RanksEnum.getRank(p).equals(Ranks.HELPER)
						|| RanksEnum.getRank(p).equals(Ranks.MOD) || RanksEnum.getRank(p).equals(Ranks.ADMIN)
						|| RanksEnum.getRank(p).equals(Ranks.DEV) || RanksEnum.getRank(p).equals(Ranks.LEADER)
						|| RanksEnum.getRank(p).equals(Ranks.COWNER) || RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}

			if (e.equals(Ranks.VIP)) {
				if (RanksEnum.getRank(p).equals(Ranks.ARCHITECT) || RanksEnum.getRank(p).equals(Ranks.BUILDER) || RanksEnum.getRank(p).equals(Ranks.SRMOD) || RanksEnum.getRank(p).equals(Ranks.VIPPLUS) || RanksEnum.getRank(p).equals(Ranks.PLUSVIPPLUS)
						|| RanksEnum.getRank(p).equals(Ranks.MASTER) || RanksEnum.getRank(p).equals(Ranks.PREMIUM)
						|| RanksEnum.getRank(p).equals(Ranks.HELPER) || RanksEnum.getRank(p).equals(Ranks.MOD)
						|| RanksEnum.getRank(p).equals(Ranks.ADMIN) || RanksEnum.getRank(p).equals(Ranks.DEV)
						|| RanksEnum.getRank(p).equals(Ranks.LEADER) || RanksEnum.getRank(p).equals(Ranks.COWNER)
						|| RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}

			if (e.equals(Ranks.VIPPLUS)) {
				if (RanksEnum.getRank(p).equals(Ranks.ARCHITECT) || RanksEnum.getRank(p).equals(Ranks.BUILDER) || RanksEnum.getRank(p).equals(Ranks.SRMOD) || RanksEnum.getRank(p).equals(Ranks.PLUSVIPPLUS) || RanksEnum.getRank(p).equals(Ranks.MASTER)
						|| RanksEnum.getRank(p).equals(Ranks.PREMIUM) || RanksEnum.getRank(p).equals(Ranks.HELPER)
						|| RanksEnum.getRank(p).equals(Ranks.MOD) || RanksEnum.getRank(p).equals(Ranks.ADMIN)
						|| RanksEnum.getRank(p).equals(Ranks.DEV) || RanksEnum.getRank(p).equals(Ranks.LEADER)
						|| RanksEnum.getRank(p).equals(Ranks.COWNER) || RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}

			if (e.equals(Ranks.PLUSVIPPLUS)) {
				if (RanksEnum.getRank(p).equals(Ranks.ARCHITECT) || RanksEnum.getRank(p).equals(Ranks.BUILDER) || RanksEnum.getRank(p).equals(Ranks.SRMOD) || RanksEnum.getRank(p).equals(Ranks.MASTER) || RanksEnum.getRank(p).equals(Ranks.PREMIUM)
						|| RanksEnum.getRank(p).equals(Ranks.HELPER) || RanksEnum.getRank(p).equals(Ranks.MOD)
						|| RanksEnum.getRank(p).equals(Ranks.ADMIN) || RanksEnum.getRank(p).equals(Ranks.DEV)
						|| RanksEnum.getRank(p).equals(Ranks.LEADER) || RanksEnum.getRank(p).equals(Ranks.COWNER)
						|| RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}

			if (e.equals(Ranks.MASTER)) {
				if (RanksEnum.getRank(p).equals(Ranks.ARCHITECT) || RanksEnum.getRank(p).equals(Ranks.BUILDER) || RanksEnum.getRank(p).equals(Ranks.SRMOD) || RanksEnum.getRank(p).equals(Ranks.PREMIUM) || RanksEnum.getRank(p).equals(Ranks.HELPER)
						|| RanksEnum.getRank(p).equals(Ranks.MOD) || RanksEnum.getRank(p).equals(Ranks.ADMIN)
						|| RanksEnum.getRank(p).equals(Ranks.DEV) || RanksEnum.getRank(p).equals(Ranks.LEADER)
						|| RanksEnum.getRank(p).equals(Ranks.COWNER) || RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}

			if (e.equals(Ranks.PREMIUM)) {
				if (RanksEnum.getRank(p).equals(Ranks.ARCHITECT) || RanksEnum.getRank(p).equals(Ranks.BUILDER) || RanksEnum.getRank(p).equals(Ranks.SRMOD) || RanksEnum.getRank(p).equals(Ranks.HELPER) || RanksEnum.getRank(p).equals(Ranks.MOD)
						|| RanksEnum.getRank(p).equals(Ranks.ADMIN) || RanksEnum.getRank(p).equals(Ranks.DEV)
						|| RanksEnum.getRank(p).equals(Ranks.LEADER) || RanksEnum.getRank(p).equals(Ranks.COWNER)
						|| RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}

			if (e.equals(Ranks.HELPER)) {
				if (RanksEnum.getRank(p).equals(Ranks.ARCHITECT) || RanksEnum.getRank(p).equals(Ranks.BUILDER) || RanksEnum.getRank(p).equals(Ranks.SRMOD) || RanksEnum.getRank(p).equals(Ranks.HELPER) || RanksEnum.getRank(p).equals(Ranks.MOD)
						|| RanksEnum.getRank(p).equals(Ranks.ADMIN) || RanksEnum.getRank(p).equals(Ranks.DEV)
						|| RanksEnum.getRank(p).equals(Ranks.LEADER) || RanksEnum.getRank(p).equals(Ranks.COWNER)
						|| RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}

			if (e.equals(Ranks.MOD)) {
				if (RanksEnum.getRank(p).equals(Ranks.ARCHITECT) || RanksEnum.getRank(p).equals(Ranks.BUILDER) || RanksEnum.getRank(p).equals(Ranks.SRMOD) || RanksEnum.getRank(p).equals(Ranks.MOD) || RanksEnum.getRank(p).equals(Ranks.ADMIN)
						|| RanksEnum.getRank(p).equals(Ranks.DEV) || RanksEnum.getRank(p).equals(Ranks.LEADER)
						|| RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}
			
			if (e.equals(Ranks.BUILDER)) {
				if (RanksEnum.getRank(p).equals(Ranks.ARCHITECT) || RanksEnum.getRank(p).equals(Ranks.BUILDER) || RanksEnum.getRank(p).equals(Ranks.SRMOD) || RanksEnum.getRank(p).equals(Ranks.MOD) || RanksEnum.getRank(p).equals(Ranks.ADMIN)
						|| RanksEnum.getRank(p).equals(Ranks.DEV) || RanksEnum.getRank(p).equals(Ranks.LEADER)
						|| RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}
			
			if (e.equals(Ranks.ARCHITECT)) {
				if (RanksEnum.getRank(p).equals(Ranks.ARCHITECT) || RanksEnum.getRank(p).equals(Ranks.SRMOD) || RanksEnum.getRank(p).equals(Ranks.MOD) || RanksEnum.getRank(p).equals(Ranks.ADMIN)
						|| RanksEnum.getRank(p).equals(Ranks.DEV) || RanksEnum.getRank(p).equals(Ranks.LEADER)
						|| RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}

			if (e.equals(Ranks.SRMOD)) {
				if (RanksEnum.getRank(p).equals(Ranks.SRMOD) || RanksEnum.getRank(p).equals(Ranks.ADMIN)
						|| RanksEnum.getRank(p).equals(Ranks.DEV) || RanksEnum.getRank(p).equals(Ranks.LEADER)
						|| RanksEnum.getRank(p).equals(Ranks.COWNER) || RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}

			if (e.equals(Ranks.ADMIN)) {
				if (RanksEnum.getRank(p).equals(Ranks.ADMIN) || RanksEnum.getRank(p).equals(Ranks.DEV)
						|| RanksEnum.getRank(p).equals(Ranks.LEADER) || RanksEnum.getRank(p).equals(Ranks.COWNER)
						|| RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}

			if (e.equals(Ranks.LEADER)) {
				if (RanksEnum.getRank(p).equals(Ranks.DEV) || RanksEnum.getRank(p).equals(Ranks.LEADER)
						|| RanksEnum.getRank(p).equals(Ranks.COWNER) || RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}

			if (e.equals(Ranks.DEV)) {
				if (RanksEnum.getRank(p).equals(Ranks.DEV) || RanksEnum.getRank(p).equals(Ranks.COWNER)
						|| RanksEnum.getRank(p).equals(Ranks.OWNER)) {
					return true;
				} else
					return false;
			}

			if (e.equals(Ranks.OWNER))
				return true;
		} else
			return false;
		return false;
	}

	public static String c(String msg) {
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		return msg;
	}

	public static Enum<Ranks> getEnum(String s) {
		if (s != null) {
			for (Enum<Ranks> b : Ranks.values()) {
				if (s.equalsIgnoreCase(b.name()))
					return b;
			}
		}
		return null;
	}

	public static String sendErrorMessage(Enum<Ranks> e) {
		String msg = ChatColor.RED + "You are not entitled to the " + e + ChatColor.RED
				+ " rank! You can buy this rank from our website, at www.bcpvp101.enjin.com";
		return msg;
	}

	@EventHandler
	public void onPlayerJoin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		if (yaml.contains(p.getName())) {
			RanksEnum.setRank(p, getEnum(yaml.getString(p.getName())));
		}

		if (isStaff(p)) {
			staff.add(p);
		}

		if (isAtLeast(p, Ranks.ADMIN)) {
			if (staff.contains(p)) {
				staff.remove(p);
			}
			admin.add(p);
		}
	}
}