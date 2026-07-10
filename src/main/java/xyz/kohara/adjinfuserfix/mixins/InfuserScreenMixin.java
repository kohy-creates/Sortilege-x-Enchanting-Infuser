package xyz.kohara.adjinfuserfix.mixins;

import fuzs.enchantinginfuser.world.inventory.InfuserMenu;
import net.lyof.sortilege.util.ItemHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(value = InfuserMenu.class, remap = false)
public class InfuserScreenMixin {

	@Shadow
	@Final
	private Container enchantSlots;

	@Shadow
	private Map<Enchantment, Integer> enchantments;

	@Inject(
			method = "clickEnchantButton",
			at = @At("HEAD"),
			cancellable = true)
	private void clickEnchantButton(Player player, CallbackInfoReturnable<Boolean> cir) {
		ItemStack itemStack = enchantSlots.getItem(0);
		int selectedEnchantments = (int) enchantments.values().stream()
				.filter(value -> value != 0)
				.count();
		if (selectedEnchantments > (ItemHelper.getTotalEnchantSlots(itemStack) - ItemHelper.getUsedEnchantSlots(itemStack))) {
			player.sendSystemMessage(Component.literal("Tᴏᴏ ᴍᴀɴʏ sᴇʟᴇᴄᴛᴇᴅ ᴇɴᴄʜᴀɴᴛᴍᴇɴᴛs!").withStyle(ChatFormatting.RED));
			cir.setReturnValue(false);
		}
	}
}
