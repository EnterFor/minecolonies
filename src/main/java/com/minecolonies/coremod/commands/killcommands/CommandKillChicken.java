package com.minecolonies.coremod.commands.killcommands;

import com.minecolonies.coremod.commands.commandTypes.IMCOPCommand;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.text.StringTextComponent;

public class CommandKillChicken implements IMCOPCommand
{

    int entitiesKilled = 0;

    /**
     * What happens when the command is executed
     *
     * @param context the context of the command execution
     */
    @Override
    public int onExecute(final CommandContext<CommandSource> context)
    {
        final Entity sender = context.getSource().getEntity();
        entitiesKilled = 0;

        context.getSource().getServer().getWorld(sender.dimension).getEntities(EntityType.CHICKEN, entity -> true).forEach(entity ->
        {
            entity.remove();
            entitiesKilled++;
        });
        sender.sendMessage(new StringTextComponent(entitiesKilled + " entities killed"));
        return 1;
    }

    /**
     * Name string of the command.
     */
    @Override
    public String getName()
    {
        return "chicken";
    }
}
