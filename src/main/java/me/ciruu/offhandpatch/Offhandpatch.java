package me.ciruu.offhandpatch;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Offhandpatch extends JavaPlugin implements Listener {

    /**
     * @author Ciruu
     */

    @Override
    public void onEnable() {
        System.out.println("Starting OffhandPatch plugin...");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            ItemStack offHand = player.getInventory().getItemInOffHand();
            if (player.getActiveItem().getType() == Material.GOLDEN_APPLE &&
                    ((offHand.getType() == Material.END_CRYSTAL && mainHand.getType() == Material.GOLDEN_APPLE) ||
                    (mainHand.getType() == Material.END_CRYSTAL && offHand.getType() == Material.GOLDEN_APPLE))
            ) {
                event.setCancelled(true);
            }
        }
    }
}
