package me.ciruu.offhandpatch;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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
            if (cancelAttack(mainHand.getType(), offHand.getType(), player))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            ItemStack offHand = player.getInventory().getItemInOffHand();

            // Make sure that the player is interacting with an end crystal
            if (event.getMaterial() == Material.END_CRYSTAL && cancelPlace(mainHand.getType(), offHand.getType(), player))
                event.setCancelled(true);
        }
    }

    private boolean cancelAttack(Material mainHand, Material offHand, Player player) {
        return (((mainHand == Material.GOLDEN_APPLE && offHand == Material.END_CRYSTAL) ||
                ((mainHand == Material.END_CRYSTAL || mainHand == Material.DIAMOND_SWORD) && offHand == Material.GOLDEN_APPLE))
                        && player.getActiveItem().getType() == Material.GOLDEN_APPLE);
    }

    private boolean cancelPlace(Material mainHand, Material offHand, Player player) {
        return (mainHand == Material.GOLDEN_APPLE && offHand == Material.END_CRYSTAL) ||
                (mainHand == Material.END_CRYSTAL && offHand == Material.GOLDEN_APPLE && player.getActiveItem().getType() == Material.GOLDEN_APPLE);
    }
}
