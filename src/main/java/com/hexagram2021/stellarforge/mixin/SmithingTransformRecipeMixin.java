package com.hexagram2021.stellarforge.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SmithingTransformRecipe.class)
public abstract class SmithingTransformRecipeMixin implements Recipe<Container> {
	@Shadow @Final
	Ingredient template;
	@Shadow @Final
	Ingredient base;
	@Shadow @Final
	Ingredient addition;

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonNullList = NonNullList.create();
		nonNullList.add(this.template);
		nonNullList.add(this.base);
		nonNullList.add(this.addition);
		return nonNullList;
	}
}
