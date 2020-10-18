//package Origin.EventMode.GUI;
//
//import org.bukkit.*;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//import org.bukkit.material.Wool;
//import sun.plugin2.main.server.Plugin;
//
//import java.util.Arrays;
//
///**
// * Created by josep on 26/06/2017.
// */
//public class TeamSelect implements Listener {
//
//
//
//    public static Inventory teamSelectGUI;
//
//
//    public static void teamMenu() {
//        teamSelectGUI = Bukkit.getServer().createInventory(null, 9, "Origin's First GUI");
//
//        ItemStack item =  new ItemStack(Material.DIRT, 1);
//        ItemMeta itemm = item.getItemMeta();
//        itemm.setDisplayName("Test");
//        itemm.setLore(Arrays.asList("This is a test menu"));
//        item.setItemMeta(itemm);
//
//        teamSelectGUI.setItem(1, item);
//        //teamSelectGUI.setItem(8, new ItemStack(Material.GOLD_BLOCK, 1));
//
//    }
//
//    public static void show(Player p) {
//        p.openInventory(teamSelectGUI);
//    }
//
//    @EventHandler
//    public void onInventoryClick(InventoryClickEvent e) {
//        if (!e.getInventory().getName().equalsIgnoreCase(teamSelectGUI.getName())) return;
//
//        if (e.getCurrentItem().getItemMeta() == null) return;
//
//        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Test")) {
//            e.setCancelled(true);
//            e.getWhoClicked().sendMessage("It worked!");
//            e.getWhoClicked().closeInventory();
//        }
//    }
//}