package ren.natsuyuk1.utils;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.logging.Level;

/**
 * @author StarWishsama
 */
public class VaultHelper {
    private static Economy econ = null;

    public static void register() {
        if (Slimefun.instance() != null) {
            if (Slimefun.instance().getServer().getPluginManager().isPluginEnabled("Vault")) {
                RegisteredServiceProvider<Economy> rsp = Slimefun.instance().getServer().getServicesManager().getRegistration(Economy.class);
                if (rsp != null) {
                    Slimefun.logger().log(Level.INFO, "成功接入 Vault");
                    econ = rsp.getProvider();
                } else {
                    Slimefun.logger().log(Level.WARNING, "无法接入 Vault. 如果你是 CMI 用户, 请至配置文件启用经济系统");
                }
            } else {
                Slimefun.logger().log(Level.WARNING, "无法接入 Vault. 你必须先安装 Vault!");
            }
        }
    }

    public static Economy getEcon() {
        return econ;
    }

    public static boolean isUsable() {
        return econ != null && Slimefun.getRegistry().isUseMoneyUnlock();
    }
}
