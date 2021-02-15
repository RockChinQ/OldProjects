package enchcracker;

public class Items
{
    public static final String LEATHER_HELMET = "leather_helmet";
    public static final String LEATHER_CHESTPLATE = "leather_chestplate";
    public static final String LEATHER_LEGGINGS = "leather_leggings";
    public static final String LEATHER_BOOTS = "leather_boots";
    public static final String IRON_HELMET = "iron_helmet";
    public static final String IRON_CHESTPLATE = "iron_chestplate";
    public static final String IRON_LEGGINGS = "iron_leggings";
    public static final String IRON_BOOTS = "iron_boots";
    public static final String CHAINMAIL_HELMET = "chainmail_helmet";
    public static final String CHAINMAIL_CHESTPLATE = "chainmail_chestplate";
    public static final String CHAINMAIL_LEGGINGS = "chainmail_leggings";
    public static final String CHAINMAIL_BOOTS = "chainmail_boots";
    public static final String GOLDEN_HELMET = "golden_helmet";
    public static final String GOLDEN_CHESTPLATE = "golden_chestplate";
    public static final String GOLDEN_LEGGINGS = "golden_leggings";
    public static final String GOLDEN_BOOTS = "golden_boots";
    public static final String DIAMOND_HELMET = "diamond_helmet";
    public static final String DIAMOND_CHESTPLATE = "diamond_chestplate";
    public static final String DIAMOND_LEGGINGS = "diamond_leggings";
    public static final String DIAMOND_BOOTS = "diamond_boots";
    public static final String WOODEN_SWORD = "wooden_sword";
    public static final String STONE_SWORD = "stone_sword";
    public static final String IRON_SWORD = "iron_sword";
    public static final String GOLDEN_SWORD = "golden_sword";
    public static final String DIAMOND_SWORD = "diamond_sword";
    public static final String WOODEN_PICKAXE = "wooden_pickaxe";
    public static final String STONE_PICKAXE = "stone_pickaxe";
    public static final String IRON_PICKAXE = "iron_pickaxe";
    public static final String GOLDEN_PICKAXE = "golden_pickaxe";
    public static final String DIAMOND_PICKAXE = "diamond_pickaxe";
    public static final String WOODEN_AXE = "wooden_axe";
    public static final String STONE_AXE = "stone_axe";
    public static final String IRON_AXE = "iron_axe";
    public static final String GOLDEN_AXE = "golden_axe";
    public static final String DIAMOND_AXE = "diamond_axe";
    public static final String WOODEN_SHOVEL = "wooden_shovel";
    public static final String STONE_SHOVEL = "stone_shovel";
    public static final String IRON_SHOVEL = "iron_shovel";
    public static final String GOLDEN_SHOVEL = "golden_shovel";
    public static final String DIAMOND_SHOVEL = "diamond_shovel";
    public static final String WOODEN_HOE = "wooden_hoe";
    public static final String STONE_HOE = "stone_hoe";
    public static final String IRON_HOE = "iron_hoe";
    public static final String GOLDEN_HOE = "golden_hoe";
    public static final String DIAMOND_HOE = "diamond_hoe";
    public static final String CARROT_ON_A_STICK = "carrot_on_a_stick";
    public static final String ELYTRA = "elytra";
    public static final String FISHING_ROD = "fishing_rod";
    public static final String FLINT_AND_STEEL = "flint_and_steel";
    public static final String SHEARS = "shears";
    public static final String SHIELD = "shield";
    public static final String BOW = "bow";
    public static final String BOOK = "book";
    public static final String ENCHANTED_BOOK = "enchanted_book";
    public static final String PUMPKIN = "pumpkin";
    public static final String SKULL = "skull";
    public static final String TRIDENT = "trident";
    public static final String TURTLE_HELMET = "turtle_helmet";
    
    public static boolean isArmor(final String item) {
        return (item.endsWith("_helmet") || item.endsWith("_chestplate") || item.endsWith("_leggings") || item.endsWith("_boots")) && ("leather_helmet".equals(item) || "leather_chestplate".equals(item) || "leather_leggings".equals(item) || "leather_boots".equals(item) || "iron_helmet".equals(item) || "iron_chestplate".equals(item) || "iron_leggings".equals(item) || "iron_boots".equals(item) || "chainmail_helmet".equals(item) || "chainmail_chestplate".equals(item) || "chainmail_leggings".equals(item) || "chainmail_boots".equals(item) || "golden_helmet".equals(item) || "golden_chestplate".equals(item) || "golden_leggings".equals(item) || "golden_boots".equals(item) || "diamond_helmet".equals(item) || "diamond_chestplate".equals(item) || "diamond_leggings".equals(item) || "diamond_boots".equals(item) || "turtle_helmet".equals(item));
    }
    
    public static boolean isHelmet(final String item) {
        return isArmor(item) && item.endsWith("_helmet");
    }
    
    public static boolean isChestplate(final String item) {
        return isArmor(item) && item.endsWith("_chestplate");
    }
    
    public static boolean isLeggings(final String item) {
        return isArmor(item) && item.endsWith("_leggings");
    }
    
    public static boolean isBoots(final String item) {
        return isArmor(item) && item.endsWith("_boots");
    }
    
    public static boolean isSword(final String item) {
        return item.endsWith("_sword") && ("wooden_sword".equals(item) || "stone_sword".equals(item) || "iron_sword".equals(item) || "golden_sword".equals(item) || "diamond_sword".equals(item));
    }
    
    public static boolean isAxe(final String item) {
        return item.endsWith("_axe") && ("wooden_axe".equals(item) || "stone_axe".equals(item) || "iron_axe".equals(item) || "golden_axe".equals(item) || "diamond_axe".equals(item));
    }
    
    public static boolean isHoe(final String item) {
        return item.endsWith("_hoe") && ("wooden_hoe".equals(item) || "stone_hoe".equals(item) || "iron_hoe".equals(item) || "golden_hoe".equals(item) || "diamond_hoe".equals(item));
    }
    
    public static boolean isTool(final String item) {
        return isAxe(item) || ((item.endsWith("_pickaxe") || item.endsWith("_shovel")) && ("wooden_pickaxe".equals(item) || "stone_pickaxe".equals(item) || "iron_pickaxe".equals(item) || "golden_pickaxe".equals(item) || "diamond_pickaxe".equals(item) || "wooden_shovel".equals(item) || "stone_shovel".equals(item) || "iron_shovel".equals(item) || "golden_shovel".equals(item) || "diamond_shovel".equals(item)));
    }
    
    public static boolean hasDurability(final String item) {
        return isArmor(item) || isTool(item) || isSword(item) || isHoe(item) || "bow".equals(item) || "carrot_on_a_stick".equals(item) || "elytra".equals(item) || "fishing_rod".equals(item) || "flint_and_steel".equals(item) || "shears".equals(item) || "shield".equals(item) || "trident".equals(item);
    }
    
    public static int getEnchantability(final String item) {
        if (isArmor(item)) {
            if (item.startsWith("leather_")) {
                return 15;
            }
            if (item.startsWith("iron_")) {
                return 9;
            }
            if (item.startsWith("chainmail_")) {
                return 12;
            }
            if (item.startsWith("golden_")) {
                return 25;
            }
            if (item.startsWith("diamond_")) {
                return 10;
            }
            if (item.startsWith("turtle_")) {
                return 9;
            }
        }
        if (isSword(item) || isTool(item)) {
            if (item.startsWith("wooden_")) {
                return 15;
            }
            if (item.startsWith("stone_")) {
                return 5;
            }
            if (item.startsWith("iron_")) {
                return 14;
            }
            if (item.startsWith("golden_")) {
                return 22;
            }
            if (item.startsWith("diamond_")) {
                return 10;
            }
        }
        if ("bow".equals(item)) {
            return 1;
        }
        if ("fishing_rod".equals(item)) {
            return 1;
        }
        if ("trident".equals(item)) {
            return 1;
        }
        if ("book".equals(item)) {
            return 1;
        }
        return 0;
    }
}
