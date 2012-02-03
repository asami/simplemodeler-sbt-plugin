package org.simplemodeling.SimpleModeler.sbt

import sbt._
import Keys._

/**
 * @since   Jan. 27, 2012
 * @version Jan. 27, 2012
 * @auther  ASAMI, Tomoharu
 */
object SimpleModelerPlugin extends Plugin {
  override lazy val settings = Seq(
    commands ++= Seq(
      simplemodeler
    )
  )

  lazy val simplemodeler = Command.command("sm") { state =>
    state
  }
}
