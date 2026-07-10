package xyz.kohara.adjinfuserfix.mixins;

import net.lyof.sortilege.enchant.common.SoulboundEnchantment;
import net.lyof.sortilege.setup.ModTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SoulboundEnchantment.class, remap = false)
public class SortilegeSoulboundEnchantMixin {

	@Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true, remap = true)
	public void canEnchant(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		boolean canEnchant = stack.getMaxStackSize() == 1
				&& !stack.isEdible()
				&& !stack.is(ModTags.Items.SOULBIND_BLACKLIST)
				&& !(stack.getItem() instanceof BucketItem)
				/* && !(stack.getItem() instanceof BlockItem) */;
		cir.setReturnValue(canEnchant);
	}
}
