package org.simplemodeling.SimpleModeler.sbt

import sbt._
import Keys._
import org.simplemodeling.SimpleModeler.SimpleModeler

/**
 * @since   Jan. 27, 2012
 * @version Feb.  3, 2012
 * @auther  ASAMI, Tomoharu
 */
object SimpleModelerPlugin extends Plugin {
  override lazy val settings = Seq(
    commands ++= Seq(
      simplemodeler
    )
  )

  lazy val simplemodeler = Command.command("sm") { state =>
    val argssm = Array.empty[String]
    val argscmd = Array("-build")                                               
    val sm = new SimpleModeler(argssm)
    sm.executeShellCommand(argscmd)
    state
  }
}
