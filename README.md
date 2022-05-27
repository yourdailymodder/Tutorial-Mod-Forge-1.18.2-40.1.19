# 1. Simple Item
 First we will create a new simple item for our tutorial mod.
**If you are new to programming then i recommend you to read this!**
 Here i will explain what we did and why.
 
## 1. Removing unnecessary codes
In this tutorial series most of the codes that forge gave us in their example mod is not needed.
If you are new at modding then it might just confuse you so we will remove them.

All that will be left is this:
```
@Mod("examplemod")
public class ExampleMod
{
    public ExampleMod()
    {
    }
}
```

### Whats new here?
###### @Mod("examplemod")
The @Mod will help you to define a Mod to the Forge Mod Loader **(FML in the future)**
 * Any class found with this annotation applied will be loaded as a Mod. The instance that is loaded will
   represent the mod to other Mods in the system. It will be sent various subclasses of {@code ModLifecycleEvent}
   at pre-defined times during the loading of the game.
   
This means the String that you write after @Mod will be the unique identifier for your mod.
This unique identifier will be your **modid**
**WARNING: You have to use lower case letters here!

## 2. Changing the ExampleMod class
When you are creating your mod you will have to write out your modid many times because of that we will create a new String variable.
```
@Mod("examplemod")
public class ExampleMod
{
    public static final String MODID = "examplemod";
    
    public ExampleMod()
    {
    }
}
```

QUESTION:
*Why is it a good idea to use this?
Its not hard to write our modid out sometimes.*

ANSWER:
If you want to change your modid in the future you will only have to change this MODID variable.

**NEXT: Use our MODID variable for the first time**

```
@Mod(ExampleMod.MODID)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    
    public ExampleMod()
    {
    }
}
```
Our mod knows that the ExampleMod.MODID means "examplemod".
SUCCESS!

Next we will write this in our constructor:
```
@Mod(ExampleMod.MODID)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    
    public ExampleMod()
    {
        Registrations.init();
    }
}
```
This means we want to call a method named init from the Registrations class!
This class does not exist, so we have to create it.

## 3. Item registrations
The registrations class with its init method will look like this:
```
public class Registrations {
	public static void init() {
	}
}
```
Save everything, import the Registrations class in the ExampleMod and close the ExampleMod class.
We are done with that one.

Now we can register our item.
For that we need to create a list for our item(s).
```
private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MODID);
```
Our list for items is created by calling the static method "create" from the DeferredRegister class
The method needs 2 parameter.
-A registry type (ForgeRegistries.ITEMS)
-and our modid
Like this the game will know that we are creating a new item for our mod.

But to let the game know about this new list we have to register it to the EventBus!
And we do that in our "init" method:
```
public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
```
Now our list for items is registered.
Don't forget! 
This method is called in the constructor of our ExampleMod and thats why it is registered.

Next is the new item:
(For our new item we will use the name ruby)
```
public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ()-> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
```
We put a new element to the ITEMS list by calling the register method.
This method needs 2 parameter.
-registry name for our item (MUST BE LOWER CASE)
-a supplier that returns the class of the item

NOTICE: .tab(CreativeModeTab.TAB_MISC) <- this is where we can find our item!

Our class will look like this now:
```
package com.example.examplemod.setup;

import com.example.examplemod.ExampleMod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registrations {

	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MODID);
			
	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ()-> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	
}
```
## 4. language, model and texture
We are almost DONE!
**Now we need some new folders:**
in the src/main/resources/ folder
-create a new folder called assets
-in that folder create a folder named as our modid so examplemod.
-in that folder we need to create 3 new folders
  -lang
  -models
  -textures
in the models folder create a new folder names as item
and do the same for the textures folder

**Now time to create json files:**
In the lang folder create a new json file and name it as en_us.json
This will contain the registry names of our mod and we can translate the registry name here.
```
{
  "item.examplemod.ruby":"Ruby"
}
```

Now we will create our model file for the item.
In the models/item/ folder create a new json file and name it as ruby.json (so the registry name of our item)
```
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "examplemod:item/ruby"
  }
}
```
"parent": "minecraft:item/generated", - this means that it will use generated.json file as a parent.
So everything that is in the generated.json model file will be inherited by this file.
The generated.json file is from minecrafts assets/models/item folder.
It contains how the item should be displayed. (rotation, size, position)

"layer0": "examplemod:item/ruby" - this means that the path to our texture can be found in the textures/item folder.

LAST THING:
Create a new 16x16 (pixel) texture named as the registered name of our item (so ruby.png)
and put it in the textures/item folder.

We are done!
Good Job!
You have your first item that can be found in the Misc. tab in creative mode!
