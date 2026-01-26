package xyz.kohara.adjinfuserfix;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.lyof.sortilege.util.ItemHelper;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public class ADJInfuserFix implements ModInitializer {

    @Override
    public void onInitialize() {
//        Uncomment when needed
//        I could probably make it conditional with java args, but I don't feel like googling it
//        CommandRegistrationCallback.EVENT.register(this::registerCommands);
    }

    private void registerCommands(CommandDispatcher<CommandSourceStack> commandSourceStackCommandDispatcher, CommandBuildContext buildContext, Commands.CommandSelection commandSelection) {
        commandSourceStackCommandDispatcher.register(
                Commands.literal("test")
                        .executes(context -> {
                            ItemStack stack = context.getSource().getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
                            System.out.println(ItemHelper.getTotalEnchantSlots(stack));
                            return 1;
                        })
        );
    }

}
