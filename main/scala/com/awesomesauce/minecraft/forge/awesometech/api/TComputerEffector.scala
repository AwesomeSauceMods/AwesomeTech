package com.awesomesauce.minecraft.forge.awesometech.api


trait TComputerEffector extends TComputerBindable {
  def activate(): Boolean
}
