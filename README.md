# CUSTOM GUI
> A Customizable GUI to enhance your Minecraft experience :D

## WHAT YOU CAN CUSTOMIZE IN CONFIG.YML
* In-Game Command to Open the GUI
  * **Default:** ```/gui```
* In-Game Command Description for the Command
  * **Default:** ```A Custom GUI Command```
* In-Game Permission for the Command
  * **Default:** ```custom.gui```
* Default Permission Value to Use the Command for All Players
  * **Default:** ```true```
* Size of the GUI
  * **Default:** ```45```
* Title of the GUI
  * **Default:** ```Custom GUI```
* Item(s) that Display in the GUI
* Name & Description that Displays for each Item when hovered over
* Slot Number each Item is placed in the GUI
* Command that executes when each Item is clicked

## HOW TO ADD ITEM TO CONFIG.YML
1. Under the **ITEMS:** section add the following:

```
  ITEMS:
    [ NAME ]:
      ITEM: [ ITEM_NAME ]
      NAME: [ GUI_ITEM_NAME ]
      DESCRIPTION: [ GUI_ITEM_DESCRIPTION ]
      SLOT_NUMBER: [ GUI_SLOT_NUMBER ]
      COMMAND: [ ITEM_COMMAND ]
```

2. Replace **[ NAME ]** with anything
    * Formatting must comply with .yml
3. Replace **[ ITEM_NAME ]** with any minecraft item
    * Must be formatted exactly as found in the URL below
    * https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html
4. Replace **[ GUI_ITEM_NAME ]** with anything
    * This will appear when then Item is being hovered over
5. Replace **[ GUI_ITEM_DESCRIPTION ]** with anything
    * This will appear when then Item is being hovered over
6. Replace **[ GUI_SLOT_NUMBER ]** with any number
    * GUI Slot Numbering starts at 0 from the Top Left Corner
7. Replace **[ ITEM_COMMAND ]** with any number
    * This is the Command the Console will Execute when the Item is clicked
    * Use ```%player%``` to get the Player Name that initiated the command

8. Finally, you should end up with something like the following:

```
  ITEMS:
    INSPECTOR:
      ITEM: DIRT
      NAME: §6DIRT
      DESCRIPTION: Click to get a piece of Dirt
      SLOT_NUMBER: 20
      COMMAND: give %player% minecraft:dirt 1
    PACKET:
      ITEM: BEDROCK
      NAME: §7NOTHING
      DESCRIPTION: Say Nothing
      SLOT_NUMBER: 22
      COMMAND: say nothing
    GITHUB:
      ITEM: COBBLESTONE
      NAME: §7COBBLESTONE
      DESCRIPTION: Click to get a piece of Cobblestone
      SLOT_NUMBER: 24
      COMMAND: give %player% minecraft:cobblestone 1
```