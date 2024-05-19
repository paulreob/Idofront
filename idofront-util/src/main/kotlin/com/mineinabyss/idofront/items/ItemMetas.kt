package com.mineinabyss.idofront.items

import org.bukkit.Color
import org.bukkit.inventory.meta.FireworkEffectMeta
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.MapMeta
import org.bukkit.inventory.meta.PotionMeta

interface Colorable {
    var color: Color?
}

fun ItemMeta.asColorable(): Colorable? {
    return when (val meta = this) {
        is LeatherArmorMeta -> object : Colorable {
            override var color: Color?
                get() = meta.color
                set(value) {
                    meta.setColor(value) // LeatherArmorMeta has setColor(Color)
                }
        }

        is PotionMeta -> object : Colorable {
            override var color: Color?
                get() = meta.color
                set(value) {
                    meta.color = value // PotionMeta has color property
                }
        }

        is MapMeta -> object : Colorable {
            override var color: Color?
                get() = meta.color
                set(value) {
                    meta.color = value // MapMeta has color property
                }
        }

        is FireworkEffectMeta -> object : Colorable {
            override var color: Color?
                get() = null // FireworkEffectMeta does not have color property directly
                set(value) {
                    // FireworkEffectMeta does not have a direct setColor method, needs workaround if needed
                }
        }

        else -> null
    }
}
