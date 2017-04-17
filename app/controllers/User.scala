package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import classes.Auth

case class UserLogin(email: String, password: String)



object User extends Controller {
  
   val userLoginForm = Form(
  mapping(
    "email" -> text,
    "password" -> text
  )(UserLogin.apply)(UserLogin.unapply)
)
  
	
  def login = Action {
    
    
   
    Ok(views.html.login(userLoginForm))
  }
   
  def auth = Action { 
    
    implicit request =>
    val email = request.body.asFormUrlEncoded.get("email")(0)
    val password = request.body.asFormUrlEncoded.get("password")(0)
    val auth = new Auth
    var role :String = "user"
     request.session.get("role").map { user =>
    println("hello" + user)
  }.getOrElse {
    Unauthorized("Oops, you are not connected")
  }
      auth.getUser(email, password) match {
      case true => 
      	role = "admin"
    	Redirect(routes.Application.index()).withSession("username" ->email)
      case false =>
        Ok(views.html.login(userLoginForm)).withSession("username" -> email)
    }

//    
//    POBIERRAMY SESJE
   
    
  }
}