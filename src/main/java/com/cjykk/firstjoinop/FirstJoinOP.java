package com.cjykk.firstjoinop;

import fr.xephi.authme.events.LoginEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class FirstJoinOP extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        if (isAuthMeInstalled()) {
            getLogger().info("已检测到AuthMe插件，启用兼容模式。");
        }
        else {
            getLogger().info("未检测到AuthMe。");
        }
        getLogger().info("插件已启动。");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("插件已停用。");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        if (!isAuthMeInstalled()) {
            giveOpAndDisable(player);
        }
        else {
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "FirstJoinOP" + ChatColor.GRAY + "] " + ChatColor.AQUA + "您将在登录成功后获得" + ChatColor.LIGHT_PURPLE + "OP" + ChatColor.AQUA + "权限。");
            registerAuthMeLoginListener(playerName, player);
        }
    }

    private boolean isAuthMeInstalled() {
        return getServer().getPluginManager().getPlugin("AuthMe") != null;
    }

    private void giveOpAndDisable(Player player) {
        player.setOp(true);
        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "FirstJoinOP" + ChatColor.GRAY + "] " + ChatColor.AQUA + "您已获得" + ChatColor.LIGHT_PURPLE + "OP" + ChatColor.AQUA + "权限。感谢您的使用！");
        getLogger().warning("已给玩家 " + player.getName() + " OP权限！插件禁用中……");
        getLogger().info("我滴任务，完！成！啦！");
        setEnabled(false);
    }

    private void registerAuthMeLoginListener(String playerName, Player player) {
        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onAuthMeLogin(LoginEvent authMeEvent) {
                if (authMeEvent.getPlayer().getName().equals(playerName)) {
                    giveOpAndDisable(player);
                }
            }
        }, this);
    }
}
