package net.momirealms.sparrow.bukkit.command.feature;

import net.kyori.adventure.text.Component;
import net.momirealms.sparrow.bukkit.SparrowBukkitPlugin;
import net.momirealms.sparrow.bukkit.command.AbstractCommand;
import net.momirealms.sparrow.bukkit.util.EntityUtils;
import net.momirealms.sparrow.common.locale.TranslationManager;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.incendo.cloud.Command;
import org.incendo.cloud.bukkit.BukkitCommandManager;
import org.incendo.cloud.bukkit.data.MultipleEntitySelector;
import org.incendo.cloud.bukkit.parser.WorldParser;
import org.incendo.cloud.bukkit.parser.selector.MultipleEntitySelectorParser;
import org.incendo.cloud.util.TypeUtils;

public class ReloadAdminCommand extends AbstractCommand {

    @Override
    public String getFeatureID() {
        return "reload_admin";
    }

    @Override
    public Command.Builder<? extends CommandSender> assembleCommand(BukkitCommandManager<CommandSender> manager, Command.Builder<CommandSender> builder) {
        return builder
                .flag(manager.flagBuilder("silent").withAliases("s"))
                .handler(commandContext -> {
                    long time1 = System.currentTimeMillis();
                    SparrowBukkitPlugin.getInstance().reload();
                    String timeTook = String.valueOf(System.currentTimeMillis() - time1);
                    boolean silent = commandContext.flags().hasFlag("silent");
                    if (!silent) {
                        SparrowBukkitPlugin.getInstance().getSenderFactory()
                                .wrap(commandContext.sender())
                                .sendMessage(
                                        TranslationManager.render(
                                                Component.translatable()
                                                        .key("commands.reload")
                                                        .arguments(Component.text(timeTook))
                                                        .build()
                                        )
                                );
                    }
                });
    }
}