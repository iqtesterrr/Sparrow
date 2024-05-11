package net.momirealms.sparrow.bukkit.command.feature;

import net.momirealms.sparrow.bukkit.SparrowBukkitPlugin;
import net.momirealms.sparrow.bukkit.command.MessagingCommandFeature;
import net.momirealms.sparrow.common.command.key.SparrowArgumentKeys;
import net.momirealms.sparrow.common.feature.enchant.EnchantManager;
import net.momirealms.sparrow.common.locale.MessageConstants;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.Command;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.parser.standard.IntegerParser;
import org.incendo.cloud.permission.Permission;

public class EnchantmentTablePlayerCommand extends MessagingCommandFeature<CommandSender> {

    @Override
    public String getFeatureID() {
        return "enchantmenttable_player";
    }

    @Override
    public Command.Builder<? extends CommandSender> assembleCommand(CommandManager<CommandSender> manager, Command.Builder<CommandSender> builder) {
        return builder
                .senderType(Player.class)
                .flag(manager.flagBuilder("shelves").withComponent(IntegerParser.integerParser(0)).withPermission(Permission.permission(EnchantManager.SHELVES_FLAG_PERMISSION)).build())
                .handler(commandContext -> {
                    boolean customShelves = commandContext.flags().hasFlag("shelves");
                    final Player player = commandContext.sender();
                    SparrowBukkitPlugin.getInstance().getEnchantManager().openEnchantmentTable(player, customShelves ? (int) commandContext.flags().getValue("shelves").get() : 0);
                    commandContext.store(SparrowArgumentKeys.MESSAGE, MessageConstants.COMMANDS_PLAYER_ENCHANTMENT_TABLE_SUCCESS);
                });
    }
}
