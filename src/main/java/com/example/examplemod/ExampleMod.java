package com.example.examplemod;

import com.example.examplemod.setup.Registrations;

import net.minecraftforge.fml.common.Mod;

@Mod(ExampleMod.MODID)
public class ExampleMod
{
	public static final String MODID = "examplemod";
    public ExampleMod()
    {
       Registrations.init();
    }
}
