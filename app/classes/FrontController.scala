package classes

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views._
import models._
import java.util.Properties
import play.api.Play.current
import play.api.libs.iteratee.Enumerator
import java.lang.reflect.Method

import models._
import play.api.http.Writeable

class FrontController extends Controller {

  def render(keyword: String): Option[String] = {
    renderDynamic("modules." + keyword)
  }

  def renderDynamic(viewClazz: String): Option[String] = {
    try {
      val clazz: Class[_] = Play.current.classloader.loadClass(viewClazz)
      println(clazz.getMethods())
      val render: Method = clazz.getDeclaredMethod("render", classOf[String])
      val view = render.invoke(clazz, "test").asInstanceOf[String]
      return Some(view)
    } catch {
      case ex: ClassNotFoundException => println("Html.renderDynamic() : could not find view " + viewClazz, ex)
      case ex: Exception => println(ex)
    }

    return None
  }

}