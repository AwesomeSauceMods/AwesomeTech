package com.awesomesauce.minecraft.forge.awesometech.tile.computer

import com.awesomesauce.minecraft.forge.awesometech.api.data.{DataItem, DataPosition}
import com.awesomesauce.minecraft.forge.awesometech.api.prefab.TileEntityComputerEffector
import net.minecraft.item.ItemBlock

class TileEntityStoneBlockPlacer extends TileEntityComputerEffector {
  var itemDataName: String = ""
  var positionDataName: String = ""
  var name = "stoneBlockPlacer"

  def activate(): Boolean = {
    val itemData = cpu.getData(itemDataName)
    if (itemData.isInstanceOf[DataItem]) {
      val item = itemData.asInstanceOf[DataItem].stack.getItem
      if (item.isInstanceOf[ItemBlock]) {
        val block = item.asInstanceOf[ItemBlock].field_150939_a
        val positionData = cpu.getData(positionDataName)
        if (positionData.isInstanceOf[DataPosition]) {
          val pData = positionData.asInstanceOf[DataPosition]
          var x: Int = 0
          var y: Int = 0
          var z: Int = 0
          if (pData.relative) {
            if (onlyNextTo && (pData.x <= 1 && pData.x >= -1) && (pData.y <= 1 && pData.y >= -1))
              x = getX + pData.x
            y = getY + pData.y
            z = getZ + pData.z
          }
          else if (supportsAbsolute) {
            x = pData.x
            y = pData.y
            z = pData.z
          }
          if (block.isReplaceable(worldObj, x, y, z)) {
            worldObj.setBlock(x, y, z, block)
            true
          }
          else false
        }
        else false
      }
      else false
    }
    else false
  }

  def supportsAbsolute = false

  def onlyNextTo = true
}
