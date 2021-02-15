package enchcracker;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.*;

public class Enchantments
{
    public static final int PROTECTION = 0;
    public static final int FIRE_PROTECTION = 1;
    public static final int FEATHER_FALLING = 2;
    public static final int BLAST_PROTECTION = 3;
    public static final int PROJECTILE_PROTECTION = 4;
    public static final int RESPIRATION = 5;
    public static final int AQUA_AFFINITY = 6;
    public static final int THORNS = 7;
    public static final int DEPTH_STRIDER = 8;
    public static final int FROST_WALKER = 9;
    public static final int BINDING_CURSE = 10;
    public static final int SHARPNESS = 16;
    public static final int SMITE = 17;
    public static final int BANE_OF_ARTHROPODS = 18;
    public static final int KNOCKBACK = 19;
    public static final int FIRE_ASPECT = 20;
    public static final int LOOTING = 21;
    public static final int SWEEPING = 22;
    public static final int EFFICIENCY = 32;
    public static final int SILK_TOUCH = 33;
    public static final int UNBREAKING = 34;
    public static final int FORTUNE = 35;
    public static final int POWER = 48;
    public static final int PUNCH = 49;
    public static final int FLAME = 50;
    public static final int INFINITY = 51;
    public static final int LUCK_OF_THE_SEA = 61;
    public static final int LURE = 62;
    public static final int LOYALTY = 64;
    public static final int IMPALING = 65;
    public static final int RIPTIDE = 66;
    public static final int CHANNELING = 67;
    public static final int MENDING = 70;
    public static final int VANISHING_CURSE = 71;
    public static final String[] ENCHANTMENT_NAMES;
    public static final Map<String, Integer> BY_NAME;
    public static final int[] ALL_ENCHANTMENTS;
    private static final Set<Set<Integer>> INCOMPATIBLE_GROUPS;
    
    static {
        ENCHANTMENT_NAMES = new String[256];
        BY_NAME = new HashMap<String, Integer>();
        ALL_ENCHANTMENTS = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 16, 17, 18, 19, 20, 21, 22, 32, 33, 34, 35, 48, 49, 50, 51, 61, 62, 64, 65, 66, 67, 70, 71 };
        INCOMPATIBLE_GROUPS = new HashSet<Set<Integer>>();
        Set<Integer> set = new HashSet<Integer>();
        set.add(51);
        set.add(70);
        Enchantments.INCOMPATIBLE_GROUPS.add(set);
        set = new HashSet<Integer>();
        set.add(16);
        set.add(17);
        set.add(18);
        Enchantments.INCOMPATIBLE_GROUPS.add(set);
        set = new HashSet<Integer>();
        set.add(8);
        set.add(9);
        Enchantments.INCOMPATIBLE_GROUPS.add(set);
        set = new HashSet<Integer>();
        set.add(0);
        set.add(1);
        set.add(3);
        set.add(4);
        Enchantments.INCOMPATIBLE_GROUPS.add(set);
        set = new HashSet<Integer>();
        set.add(33);
        set.add(21);
        Enchantments.INCOMPATIBLE_GROUPS.add(set);
        set = new HashSet<Integer>();
        set.add(33);
        set.add(35);
        Enchantments.INCOMPATIBLE_GROUPS.add(set);
        set = new HashSet<Integer>();
        set.add(33);
        set.add(61);
        Enchantments.INCOMPATIBLE_GROUPS.add(set);
        set = new HashSet<Integer>();
        set.add(66);
        set.add(64);
        Enchantments.INCOMPATIBLE_GROUPS.add(set);
        set = new HashSet<Integer>();
        set.add(66);
        set.add(67);
        Enchantments.INCOMPATIBLE_GROUPS.add(set);
        Field[] declaredFields;
        for (int length = (declaredFields = Enchantments.class.getDeclaredFields()).length, i = 0; i < length; ++i) {
            final Field field = declaredFields[i];
            if (field.getModifiers() == 25 && field.getType() == Integer.TYPE) {
                final String enchantmentName = field.getName().toLowerCase();
                int enchantmentId;
                try {
                    enchantmentId = field.getInt(null);
                }
                catch (Exception e) {
                    throw new Error(e);
                }
                Enchantments.ENCHANTMENT_NAMES[enchantmentId] = enchantmentName;
                Enchantments.BY_NAME.put(enchantmentName, enchantmentId);
            }
        }
    }
    
    public static String getName(final int enchantment) {
        if (enchantment < 0 || enchantment >= 256) {
            return null;
        }
        return Enchantments.ENCHANTMENT_NAMES[enchantment];
    }
    
    public static boolean canApply(final int enchantment, final String item, final boolean primary) {
        if ("book".equals(item)) {
            return true;
        }
        switch (enchantment) {
            case 0:
            case 1:
            case 3:
            case 4: {
                return Items.isArmor(item);
            }
            case 7: {
                return primary ? Items.isChestplate(item) : Items.isArmor(item);
            }
            case 2:
            case 8:
            case 9: {
                return Items.isBoots(item);
            }
            case 5:
            case 6: {
                return Items.isHelmet(item);
            }
            case 10: {
                return Items.isArmor(item) || "pumpkin".equals(item) || "elytra".equals(item) || "skull".equals(item);
            }
            case 16:
            case 17:
            case 18: {
                return Items.isSword(item) || (!primary && Items.isAxe(item));
            }
            case 19:
            case 20:
            case 21:
            case 22: {
                return Items.isSword(item);
            }
            case 32: {
                return Items.isTool(item) || (!primary && "shears".equals(item));
            }
            case 33:
            case 35: {
                return Items.isTool(item);
            }
            case 48:
            case 49:
            case 50:
            case 51: {
                return "bow".equals(item);
            }
            case 61:
            case 62: {
                return "fishing_rod".equals(item);
            }
            case 34:
            case 70: {
                return Items.hasDurability(item);
            }
            case 71: {
                return Items.hasDurability(item) || "pumpkin".equals(item) || "skull".equals(item);
            }
            case 64:
            case 65:
            case 66:
            case 67: {
                return "trident".equals(item);
            }
            default: {
                throw new IllegalArgumentException("Unknown enchantment: " + enchantment);
            }
        }
    }
    
    public static boolean isTreasure(final int enchantment) {
        return enchantment == 9 || enchantment == 70 || enchantment == 10 || enchantment == 71;
    }
    
    public static int getMaxLevel(final int enchantment) {
        switch (enchantment) {
            case 16:
            case 17:
            case 18:
            case 32:
            case 48:
            case 65: {
                return 5;
            }
            case 0:
            case 1:
            case 2:
            case 3:
            case 4: {
                return 4;
            }
            case 5:
            case 7:
            case 8:
            case 21:
            case 22:
            case 34:
            case 35:
            case 61:
            case 62:
            case 64:
            case 66: {
                return 3;
            }
            case 9:
            case 19:
            case 20:
            case 49: {
                return 2;
            }
            case 6:
            case 10:
            case 33:
            case 50:
            case 51:
            case 67:
            case 70:
            case 71: {
                return 1;
            }
            default: {
                throw new IllegalArgumentException("Unknown enchantment: " + enchantment);
            }
        }
    }
    
    public static int getMinEnchantability(final int enchantment, final int level) {
        switch (enchantment) {
            case 0: {
                return 1 + (level - 1) * 11;
            }
            case 1: {
                return 10 + (level - 1) * 8;
            }
            case 2: {
                return 5 + (level - 1) * 6;
            }
            case 3: {
                return 5 + (level - 1) * 8;
            }
            case 4: {
                return 3 + (level - 1) * 6;
            }
            case 5: {
                return level * 10;
            }
            case 6: {
                return 1;
            }
            case 7: {
                return 10 + (level - 1) * 20;
            }
            case 8: {
                return level * 10;
            }
            case 9: {
                return level * 10;
            }
            case 10: {
                return 25;
            }
            case 16: {
                return 1 + (level - 1) * 11;
            }
            case 17: {
                return 5 + (level - 1) * 8;
            }
            case 18: {
                return 5 + (level - 1) * 8;
            }
            case 19: {
                return 5 + (level - 1) * 20;
            }
            case 20: {
                return 10 + (level - 1) * 20;
            }
            case 21: {
                return 15 + (level - 1) * 9;
            }
            case 22: {
                return 5 + (level - 1) * 9;
            }
            case 32: {
                return 1 + (level - 1) * 10;
            }
            case 33: {
                return 15;
            }
            case 34: {
                return 5 + (level - 1) * 8;
            }
            case 35: {
                return 15 + (level - 1) * 9;
            }
            case 48: {
                return 1 + (level - 1) * 10;
            }
            case 49: {
                return 12 + (level - 1) * 20;
            }
            case 50: {
                return 20;
            }
            case 51: {
                return 20;
            }
            case 61: {
                return 15 + (level - 1) * 9;
            }
            case 62: {
                return 15 + (level - 1) * 9;
            }
            case 70: {
                return 25;
            }
            case 71: {
                return 25;
            }
            case 64: {
                return 5 + level * 7;
            }
            case 65: {
                return 1 + (level - 1) * 8;
            }
            case 66: {
                return 10 * level + 7;
            }
            case 67: {
                return 25;
            }
            default: {
                throw new IllegalArgumentException("Unknown enchantment: " + enchantment);
            }
        }
    }
    
    public static int getMaxEnchantability(final int enchantment, final int level) {
        switch (enchantment) {
            case 0: {
                return 1 + level * 11;
            }
            case 1: {
                return 10 + level * 8;
            }
            case 2: {
                return 5 + level * 6;
            }
            case 3: {
                return 5 + level * 8;
            }
            case 4: {
                return 3 + level * 6;
            }
            case 5: {
                return 30 + level * 10;
            }
            case 6: {
                return 41;
            }
            case 7: {
                return 40 + level * 20;
            }
            case 8: {
                return 15 + level * 10;
            }
            case 9: {
                return 15 + level * 10;
            }
            case 10: {
                return 50;
            }
            case 16: {
                return 21 + (level - 1) * 11;
            }
            case 17: {
                return 25 + (level - 1) * 8;
            }
            case 18: {
                return 25 + (level - 1) * 8;
            }
            case 19: {
                return 55 + (level - 1) * 20;
            }
            case 20: {
                return 40 + level * 20;
            }
            case 21: {
                return 65 + (level - 1) * 9;
            }
            case 22: {
                return 20 + (level - 1) * 9;
            }
            case 32: {
                return 50 + level * 10;
            }
            case 33: {
                return 65;
            }
            case 34: {
                return 55 + (level - 1) * 8;
            }
            case 35: {
                return 65 + (level - 1) * 9;
            }
            case 48: {
                return 16 + (level - 1) * 10;
            }
            case 49: {
                return 37 + (level - 1) * 20;
            }
            case 50: {
                return 50;
            }
            case 51: {
                return 50;
            }
            case 61: {
                return 65 + (level - 1) * 9;
            }
            case 62: {
                return 65 + (level - 1) * 9;
            }
            case 70: {
                return 75;
            }
            case 71: {
                return 50;
            }
            case 64: {
                return 50;
            }
            case 65: {
                return 21 + (level - 1) * 8;
            }
            case 66: {
                return 50;
            }
            case 67: {
                return 50;
            }
            default: {
                throw new IllegalArgumentException("Unknown enchantment: " + enchantment);
            }
        }
    }
    
    public static int getWeight(final int enchantment) {
        switch (enchantment) {
            case 0:
            case 16:
            case 32:
            case 48: {
                return 10;
            }
            case 1:
            case 2:
            case 4:
            case 17:
            case 18:
            case 19:
            case 34:
            case 64: {
                return 5;
            }
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 20:
            case 21:
            case 22:
            case 35:
            case 49:
            case 50:
            case 61:
            case 62:
            case 65:
            case 66:
            case 70: {
                return 2;
            }
            case 7:
            case 10:
            case 33:
            case 51:
            case 67:
            case 71: {
                return 1;
            }
            default: {
                throw new IllegalArgumentException("Unknown enchantment: " + enchantment);
            }
        }
    }
    
    public static boolean areCompatible(final int enchA, final int enchB) {
        return enchA != enchB && Enchantments.INCOMPATIBLE_GROUPS.stream().noneMatch(group -> group.contains(enchA) && group.contains(enchB));
    }
    
    public static List<EnchantmentInstance> parseEnchantmentInstance(final String item, final String str, final boolean maxOnly) {
        final String[] parts = str.trim().split("\\s+");
        final String enchantment = parts[0];
        if (!Enchantments.BY_NAME.containsKey(enchantment)) {
            return Collections.emptyList();
        }
        final int enchantmentId = Enchantments.BY_NAME.get(enchantment);
        final int enchantability = Items.getEnchantability(item);
        int maxLevel;
        if (enchantability == 0 || isTreasure(enchantmentId) || !canApply(enchantmentId, item, true)) {
            maxLevel = 0;
        }
        else {
            int level = 31 + enchantability / 4 + enchantability / 4;
            level += Math.round(level * 0.15f);
            for (maxLevel = getMaxLevel(enchantmentId); maxLevel >= 1; --maxLevel) {
                if (level >= getMinEnchantability(enchantmentId, maxLevel)) {
                    break;
                }
            }
        }
        if (parts.length == 1) {
            if (maxLevel == 0) {
                return Collections.emptyList();
            }
            if (maxOnly) {
                return Collections.singletonList(new EnchantmentInstance(enchantmentId, maxLevel));
            }
            final List<EnchantmentInstance> list = new ArrayList<EnchantmentInstance>();
            for (int level2 = 1; level2 <= maxLevel; ++level2) {
                list.add(new EnchantmentInstance(enchantmentId, level2));
            }
            return list;
        }
        else {
            if (parts.length != 2) {
                return Collections.emptyList();
            }
            int level;
            try {
                level = Integer.parseInt(parts[1]);
            }
            catch (NumberFormatException e) {
                return Collections.emptyList();
            }
            if (level < 1 || level > maxLevel) {
                return Collections.emptyList();
            }
            return Collections.singletonList(new EnchantmentInstance(enchantmentId, level));
        }
    }
    
    public static int calcEnchantmentTableLevel(final Random rand, final int slot, final int bookshelves, final String item) {
        if (Items.getEnchantability(item) == 0) {
            return 0;
        }
        final int level = rand.nextInt(8) + 1 + (bookshelves >> 1) + rand.nextInt(bookshelves + 1);
        switch (slot) {
            case 0: {
                return Math.max(level / 3, 1);
            }
            case 1: {
                return level * 2 / 3 + 1;
            }
            case 2: {
                return Math.max(level, bookshelves * 2);
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    public static List<EnchantmentInstance> getEnchantmentsInTable(final Random rand, final int xpSeed, final String item, final int slot, final int levels) {
        rand.setSeed(xpSeed + slot);
        final List<EnchantmentInstance> list = addRandomEnchantments(rand, item, levels, false);
        if ("book".equals(item) && list.size() > 1) {
            list.remove(rand.nextInt(list.size()));
        }
        return list;
    }
    
    public static List<EnchantmentInstance> addRandomEnchantments(final Random rand, final String item, int level, final boolean treasure) {
        final int enchantability = Items.getEnchantability(item);
        final List<EnchantmentInstance> enchantments = new ArrayList<EnchantmentInstance>();
        if (enchantability > 0) {
            level = level + 1 + rand.nextInt(enchantability / 4 + 1) + rand.nextInt(enchantability / 4 + 1);
            final float percentChange = (rand.nextFloat() + rand.nextFloat() - 1.0f) * 0.15f;
            level += Math.round(level * percentChange);
            if (level < 1) {
                level = 1;
            }
            final List<EnchantmentInstance> allowedEnchantments = new ArrayList<EnchantmentInstance>();
            int[] all_ENCHANTMENTS;
            for (int length = (all_ENCHANTMENTS = Enchantments.ALL_ENCHANTMENTS).length, i = 0; i < length; ++i) {
                final int enchantment = all_ENCHANTMENTS[i];
                if ((treasure || !isTreasure(enchantment)) && canApply(enchantment, item, true)) {
                    for (int enchLvl = getMaxLevel(enchantment); enchLvl >= 1; --enchLvl) {
                        if (level >= getMinEnchantability(enchantment, enchLvl) && level <= getMaxEnchantability(enchantment, enchLvl)) {
                            allowedEnchantments.add(new EnchantmentInstance(enchantment, enchLvl));
                            break;
                        }
                    }
                }
            }
            if (!allowedEnchantments.isEmpty()) {
                EnchantmentInstance enchantmentInstance = weightedRandom(rand, allowedEnchantments, it -> getWeight(it.enchantment));
                enchantments.add(enchantmentInstance);
                while (rand.nextInt(50) <= level) {
                    final int enchantment2 = enchantmentInstance.enchantment;
                    allowedEnchantments.removeIf(it -> !areCompatible(it.enchantment, enchantment2));
                    if (allowedEnchantments.isEmpty()) {
                        break;
                    }
                    enchantmentInstance = weightedRandom(rand, allowedEnchantments, it -> getWeight(it.enchantment));
                    enchantments.add(enchantmentInstance);
                    level /= 2;
                }
            }
        }
        return enchantments;
    }
    
    private static <T> T weightedRandom(final Random rand, final List<T> list, final ToIntFunction<T> weightExtractor) {
        int weight = list.stream().mapToInt((ToIntFunction<? super Object>)weightExtractor).sum();
        if (weight <= 0) {
            return null;
        }
        weight = rand.nextInt(weight);
        for (int i = 0, e = list.size(); i < e; ++i) {
            final T t = list.get(i);
            weight -= weightExtractor.applyAsInt(t);
            if (weight < 0) {
                return t;
            }
        }
        return null;
    }
    
    public static class EnchantmentInstance
    {
        public final int enchantment;
        public final int level;
        
        public EnchantmentInstance(final int enchantment, final int level) {
            this.enchantment = enchantment;
            this.level = level;
        }
        
        @Override
        public int hashCode() {
            return this.enchantment + 31 * this.level;
        }
        
        @Override
        public boolean equals(final Object other) {
            return other instanceof EnchantmentInstance && this.equals((EnchantmentInstance)other);
        }
        
        public boolean equals(final EnchantmentInstance other) {
            return this.enchantment == other.enchantment && this.level == other.level;
        }
        
        @Override
        public String toString() {
            final String enchName = Enchantments.getName(this.enchantment);
            if (this.level == 1 && Enchantments.getMaxLevel(this.enchantment) == 1) {
                return enchName;
            }
            String lvlName = null;
            switch (this.level) {
                case 1: {
                    lvlName = "I";
                    break;
                }
                case 2: {
                    lvlName = "II";
                    break;
                }
                case 3: {
                    lvlName = "III";
                    break;
                }
                case 4: {
                    lvlName = "IV";
                    break;
                }
                case 5: {
                    lvlName = "V";
                    break;
                }
                default: {
                    lvlName = String.valueOf(this.level);
                    break;
                }
            }
            return String.valueOf(enchName) + " " + lvlName;
        }
    }
}
