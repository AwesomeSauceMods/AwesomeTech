package com.awesomesauce.minecraft.forge.awesometech.api

import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.{FluidStack, Fluid}

trait TMachine {
  def drainRF(amount: Int, simulate: Boolean) : Int
  def drainFluid(fluid: FluidStack, simulate:Boolean) : Boolean
  def drainItem(item: ItemStack, simulate:Boolean) : Boolean
  def insertRF(amount: Int, simulate: Boolean) : Int
  def insertFluid(fluid:FluidStack, simulate: Boolean) : Boolean
  def insertItem(item: ItemStack, simulate: Boolean) : Boolean
  def getX: Int
  def getY: Int
  def getZ: Int
}
