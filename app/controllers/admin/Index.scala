package controllers.admin

import play.api.mvc.Controller
import play.api.mvc.Action

object Index extends Controller {
	
  def index = Action {
    Ok(views.html.admin.index())
  }
}