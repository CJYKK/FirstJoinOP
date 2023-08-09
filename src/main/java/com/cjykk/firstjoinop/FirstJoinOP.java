// -- coding: utf-8 --
package com.cjykk.firstjoinop;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class FirstJoinOP extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("插件已启用");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("任务完成！插件已停用。");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        player.setOp(true);
        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "FirstJoinOP" + ChatColor.GRAY + "] "  + ChatColor.AQUA + "您已获得" + ChatColor.LIGHT_PURPLE + "OP" + ChatColor.AQUA + "权限。感谢您的使用！");
        getLogger().warning("已给玩家" + playerName + "OP权限！插件禁用中……");
        this.setEnabled(false);
    }
}
