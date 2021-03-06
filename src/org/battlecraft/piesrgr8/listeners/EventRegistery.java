package org.battlecraft.piesrgr8.listeners;

import org.battlecraft.iHersh.ranks.RanksEnum;
import org.battlecraft.piesrgr8.BattlecraftServer;
import org.battlecraft.piesrgr8.chat.AntiSwear;
import org.battlecraft.piesrgr8.chat.Chat;
import org.battlecraft.piesrgr8.combat.ForceChoke;
import org.battlecraft.piesrgr8.config.PlayersYML;
import org.battlecraft.piesrgr8.essentials.AFK;
import org.battlecraft.piesrgr8.essentials.ApplicationForm;
import org.battlecraft.piesrgr8.essentials.Invisibility;
import org.battlecraft.piesrgr8.fake.SilentJoin;
import org.battlecraft.piesrgr8.fake.Spammer;
import org.battlecraft.piesrgr8.firework.FireworkJoin;
import org.battlecraft.piesrgr8.firework.Fireworks;
import org.battlecraft.piesrgr8.firework.InstantFw;
import org.battlecraft.piesrgr8.gadgets.Gadget;
import org.battlecraft.piesrgr8.gadgets.GadgetGUI;
import org.battlecraft.piesrgr8.hub.DoubleJump;
import org.battlecraft.piesrgr8.hub.Hub;
import org.battlecraft.piesrgr8.hub.Launchers;
import org.battlecraft.piesrgr8.inventory.InvMethods;
import org.battlecraft.piesrgr8.inventory.RestoreInventory;
import org.battlecraft.piesrgr8.menu.MainPvP;
import org.battlecraft.piesrgr8.menu.Menus;
import org.battlecraft.piesrgr8.menu.NavGame;
import org.battlecraft.piesrgr8.menu.Sg;
import org.battlecraft.piesrgr8.party.PartyEvents;
import org.battlecraft.piesrgr8.party.PartyGUI;
import org.battlecraft.piesrgr8.players.DarthLaser123;
import org.battlecraft.piesrgr8.players.Friends;
import org.battlecraft.piesrgr8.players.IHersh;
import org.battlecraft.piesrgr8.players.LilyMc101;
import org.battlecraft.piesrgr8.players.Piesrgr8;
import org.battlecraft.piesrgr8.punish.Punish;
import org.battlecraft.piesrgr8.shop.Shop;
import org.battlecraft.piesrgr8.shop.material.ShopMaterial;
import org.battlecraft.piesrgr8.shop.material.ShopMaterialColorwool;
import org.battlecraft.piesrgr8.shop.material.ShopMaterialResource;
import org.battlecraft.piesrgr8.shop.material.ShopMaterialStone;
import org.battlecraft.piesrgr8.shop.material.ShopMaterialWood;
import org.battlecraft.piesrgr8.shop.tool.ShopTool;
import org.battlecraft.piesrgr8.signs.Buy;
import org.battlecraft.piesrgr8.signs.Sell;
import org.battlecraft.piesrgr8.stats.StatsManager;
import org.battlecraft.piesrgr8.teleportation.PlayerTp;
import org.battlecraft.piesrgr8.utils.Dynamicmotd;
import org.battlecraft.piesrgr8.utils.PacketUtil;
import org.battlecraft.piesrgr8.utils.ScoreboardMg;
import org.battlecraft.piesrgr8.utils.SignColors;
import org.battlecraft.piesrgr8.utils.online.TimerDaily;
import org.battlecraft.piesrgr8.weapons.Guns;
import org.battlecraft.piesrgr8.world.WorldFallingBlocks;
import org.battlecraft.piesrgr8.world.WorldHandler;
import org.battlecraft.piesrgr8.world.WorldTime;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventRegistery {
	
	BattlecraftServer plugin;

	public EventRegistery(BattlecraftServer p) {
		this.plugin = p;
	}
	
	public static void registerEvents(BattlecraftServer plugin){
	//Registering events for the game.
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new Hub(plugin), plugin);
		pm.registerEvents(new SilentJoin(plugin), plugin);
		pm.registerEvents(new PlayerListener(plugin), plugin);
		pm.registerEvents(new AntiSwear(plugin), plugin);
		pm.registerEvents(new WorldHandler(plugin), plugin);
		pm.registerEvents(new WorldFallingBlocks(plugin), plugin);
		pm.registerEvents(new Guns(plugin), plugin);
		pm.registerEvents(new PacketUtil(plugin), plugin);
		pm.registerEvents(new DeathListener(plugin), plugin);
		pm.registerEvents(new Spammer(plugin), plugin);
		pm.registerEvents(new Sell(plugin), plugin);
		pm.registerEvents(new RestoreInventory(plugin), plugin);
		pm.registerEvents(new Dynamicmotd(plugin), plugin);
		pm.registerEvents(new Menus(plugin), plugin);
		pm.registerEvents(new NavGame(plugin), plugin);
		pm.registerEvents(new PlayerTp(plugin), plugin);
		pm.registerEvents(new Launchers(plugin), plugin);
		pm.registerEvents(new Chat(plugin), plugin);
		pm.registerEvents(new ScoreboardMg(plugin), plugin);
		pm.registerEvents(new SignColors(plugin), plugin);
		pm.registerEvents(new DoubleJump(plugin), plugin);
		pm.registerEvents(new TimerDaily(plugin), plugin);
		pm.registerEvents(new Friends(plugin), plugin);
		pm.registerEvents(new RanksEnum(plugin), plugin);
		pm.registerEvents(new PlayersYML(plugin), plugin);
		pm.registerEvents(new Fireworks(plugin), plugin);
		pm.registerEvents(new Punish(plugin), plugin);
		pm.registerEvents(new Invisibility(plugin), plugin);
		pm.registerEvents(new EntityListener(plugin), plugin);
		pm.registerEvents(new AFK(plugin), plugin);
		pm.registerEvents(new ApplicationForm(plugin), plugin);
		pm.registerEvents(new InvMethods(plugin), plugin);
		pm.registerEvents(new WorldTime(plugin), plugin);
		
		//FOR HOLIDAYS
		pm.registerEvents(new FireworkJoin(plugin), plugin);
		
		//FOR PLAYERS
		pm.registerEvents(new Piesrgr8(plugin), plugin);
		pm.registerEvents(new IHersh(plugin), plugin);
		pm.registerEvents(new LilyMc101(plugin), plugin);
		pm.registerEvents(new DarthLaser123(plugin), plugin);

		// FOR STATS
		pm.registerEvents(new StatsManager(plugin), plugin);
		
		// FOR SHOP
		pm.registerEvents(new Shop(plugin), plugin);
		pm.registerEvents(new ShopMaterial(plugin), plugin);
		pm.registerEvents(new ShopMaterialWood(plugin), plugin);
		pm.registerEvents(new ShopMaterialStone(plugin), plugin);
		pm.registerEvents(new ShopMaterialResource(plugin), plugin);
		pm.registerEvents(new ShopMaterialColorwool(plugin), plugin);
		pm.registerEvents(new ShopTool(plugin), plugin);
		
		//FOR SIGNS
		pm.registerEvents(new Buy(plugin), plugin);
		
		//FOR COMBAT
		pm.registerEvents(new ForceChoke(plugin), plugin);
		
		//FOR PARTIES
		pm.registerEvents(new PartyGUI(plugin), plugin);
		pm.registerEvents(new PartyEvents(plugin), plugin);
		
		//FOR MENUS
		pm.registerEvents(new MainPvP(plugin), plugin);
		pm.registerEvents(new Sg(plugin), plugin);
		
		//FOR COSMETICS
		pm.registerEvents(new GadgetGUI(plugin), plugin);
		pm.registerEvents(new Gadget(plugin), plugin);
		
		
		pm.registerEvents(new InstantFw(plugin), plugin);
	}
}
