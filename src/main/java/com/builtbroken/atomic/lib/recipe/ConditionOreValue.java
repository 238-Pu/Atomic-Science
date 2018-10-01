package com.builtbroken.atomic.lib.recipe;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.OreDictionary;

import java.util.function.BooleanSupplier;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 3/20/2018.
 */
public class ConditionOreValue implements IConditionFactory
{
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json)
    {
        final boolean condition = Boolean.parseBoolean(JsonUtils.getString(json, "condition").toLowerCase());
        final String value = JsonUtils.getString(json, "value");
        return () -> hasOreValue(value) == condition;
}

    private boolean hasOreValue(String value)
    {
        NonNullList<ItemStack> list = OreDictionary.getOres(value);
        for (ItemStack stack : list)
        {
            if (!stack.isEmpty())
            {
                return true;
            }
        }
        return false;
    }
}
