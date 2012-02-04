package org.simplemodeling.SimpleModeler.sbt

import sbt._
import Keys._
import Project.Initialize
import org.simplemodeling.SimpleModeler.SimpleModeler

/**
 * @since   Jan. 27, 2012
 * @version Feb.  4, 2012
 * @auther  ASAMI, Tomoharu
 */
object SimpleModelerPlugin extends Plugin {
  override lazy val settings = Seq(
    commands += simplemodeler,
    smModelDirectories in Compile <<= smModelDirectoriesValue,
    sourceGenerators in Compile <+= smSourceGeneratorTask
  )

  lazy val smModelDirectories = SettingKey[Seq[File]]("sm-model-directories",
                                                      "Locations of SimpleModel files.")
 
  def smModelDirectoriesValue: Initialize[Seq[File]] =
    (resourceDirectory in Compile) { (d) => Seq(d) }

  lazy val simplemodeler = Command.args("sm" ,"<args>") { (state, args) =>
    val argssm = Array.empty[String]
    val argscmd = Array("-build")                                               
    val sm = new SimpleModeler(argssm)
    sm.executeShellCommand(argscmd)
    state
  }

  lazy val smSourceGeneratorTask: Initialize[Task[Seq[File]]] = (streams,
                                                                 sourceManaged in Compile,
                                                                 smModelDirectories in Compile) map {
    (out, outputDir, inputDir) => {
      val outs = outputDir / "java/model"
      println("SimpleModeler Model Generation.")
      println(outs.listFiles.toList)
      outs.listFiles.toList
    }
  }

/*
  val scalateTemplateDirectories = SettingKey[Seq[File]]("scalate-template-directories",
                                                         "Locations of template files.")
  def scalateTemplateDirectoriesValue: Initialize[Seq[File]] =
    (resourceDirectory in Compile) { (d) => Seq(d) }

  def scalateSourceGeneratorTask: Initialize[Task[Seq[File]]] = (streams,
                                                                 sourceManaged in Compile,
                                                                 scalateTemplateDirectories in Compile
                                                                 ) map {
    (out, outputDir, inputDir) => {
      System.setProperty("logback.configurationFile", logConfig.toString)

      val engine = new org.fusesource.scalate.TemplateEngine()
      engine.packagePrefix = ""

      inputDir.foreach { dir =>
        dir.listFiles.filter(needsCompiling(_, outputDir)).foreach { template =>
          generate(engine, template, outputDir, out.log)
        }
      }
      outputDir.listFiles.toList
    }
  }

  // Overriding settings means these expressions will be automatically interpolated
  // into the project's build.sbt

  override def settings = Seq (
    scalateTemplateDirectories in Compile <<= scalateTemplateDirectoriesValue,
    sourceGenerators in Compile <+= scalateSourceGeneratorTask
  )
*/
}
