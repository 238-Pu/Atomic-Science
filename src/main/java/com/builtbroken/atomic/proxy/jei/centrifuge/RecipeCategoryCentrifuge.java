package com.builtbroken.atomic.proxy.jei.centrifuge;

import com.builtbroken.atomic.AtomicScience;
import com.builtbroken.atomic.content.ASBlocks;
import com.builtbroken.atomic.proxy.jei.TooltipCallbackFluid;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

/**
 *
 * Created by Dark(DarkGuardsman, Robert) on 9/16/2018.
 */
public class RecipeCategoryCentrifuge implements IRecipeCategory<RecipeWrapperCentrifuge>
{
    public static final String ID = AtomicScience.PREFIX + "chem.centrifuge";
    public static final ResourceLocation backgroundTexture = new ResourceLocation(AtomicScience.DOMAIN, "textures/gui/jei.png");

    IDrawable icon;
    IDrawable background;

    public RecipeCategoryCentrifuge(IJeiHelpers helpers)
    {
        icon = helpers.getGuiHelper().createDrawableIngredient(new ItemStack(ASBlocks.blockChemCentrifuge));
        background = helpers.getGuiHelper().createDrawable(backgroundTexture, 0, 0, 98, 18);
    }

    @Override
    public String getUid()
    {
        return ID;
    }

    @Override
    public String getTitle()
    {
        return ASBlocks.blockChemCentrifuge.getLocalizedName();
    }

    @Override
    public String getModName()
    {
        return AtomicScience.DOMAIN;
    }

    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, RecipeWrapperCentrifuge recipeWrapper, IIngredients ingredients)
    {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

        guiItemStacks.init(0, true, 19, 0);
        guiItemStacks.init(1, false, 61, 0);

        guiFluidStacks.init(0, true, 1, 1);
        guiFluidStacks.init(1, false, 81, 1);
        guiFluidStacks.init(2, false, 100, 1);

        guiFluidStacks.addTooltipCallback(new TooltipCallbackFluid());

        List<ItemStack> inputs = recipeWrapper.recipe.getPossibleInputs();
        if (inputs != null)
        {
            guiItemStacks.set(0,  inputs);
        }
        List<ItemStack> outputs = recipeWrapper.recipe.getPossibleOutputs();
        if (outputs != null)
        {
            guiItemStacks.set(1, outputs);
        }

        if (recipeWrapper.recipe.inputTank != null)
        {
            guiFluidStacks.set(0, recipeWrapper.recipe.inputTank);
        }
        if (recipeWrapper.recipe.outputTank != null)
        {
            guiFluidStacks.set(1, recipeWrapper.recipe.outputTank);
        }
    }
}
