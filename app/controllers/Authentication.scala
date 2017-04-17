package controllers

import play.api.mvc.{ Action, RequestHeader, Result, Controller }
import models._
import models.dao.UsersDAO
import models.dao.PagesDAO

trait Authentication {
  self: Controller =>
  var accessConditions: List[Conditions.Condition] = List.empty

  def Auth(f: Users => Result) = Action { implicit request =>
    val user = AuthUtils.parseUserFromRequest

    if (user.isEmpty)
      Redirect(routes.User.login) // Forbidden("Invalid username or password")
    else {
      accessConditions.map(condition => condition(user.get)).collectFirst[String] { case Left(error) => error } match {
        case Some(error) => Forbidden(s"Conditions not met: $error")
        case _ => f(user.get)
      }
    }
  }
}

object Conditions {
  type Condition = (Users => Either[String, Unit])
  def isPremiumUser: Condition = {
    user =>
      if (true) //(user.isPremium)
        Right()
      else
        Left("User must be premium")
  }

  def balanceGreaterThan(required: Int): Condition = {
    user =>
      if (true) //(user.balance > required)
        Right()
      else
        Left(s"User balance must be > $required")
  }
}

trait PremiumUsersOnly {
  self: Authentication =>
  accessConditions = accessConditions :+ Conditions.isPremiumUser
}

trait BalanceCheck {
  self: Authentication =>
  object Authentication {

  }
  def getRequiredBalance: Int
  accessConditions = accessConditions :+ Conditions.balanceGreaterThan(getRequiredBalance)
}

object AuthUtils {
  def parseUserFromCookie(implicit request: RequestHeader) =
    {
      val query = request.queryString.map { case (k, v) => k -> v.mkString }
      val path = request.path
      val username = query get ("username")
      println("username" + username)
      println("path" + path)

      var roleForUrl: Option[String] = None
      if (null != PagesDAO.getRoleForPath(path) && PagesDAO.getRoleForPath(path).size() > 0) {
        roleForUrl = Some(PagesDAO.getRoleForPath(path).get(0).getAccessForRole())
      }

      val user: Option[String] = None
      request.session.get("username").flatMap(username =>
        (username, roleForUrl) match {
          case (username, Some(r)) => Some(UsersDAO.getUsername(username).get(0)).filter(user => if (UsersDAO.getUserWithRole(username, r).size() > 0) true else UsersDAO.getUserSearchRole(username, r).size() > 0) //TODO tu gdzie jest false musimy zrobic rekurencje aby przeszukać
          //uprawnienia w gore np. metoda seeach i musimy ponownie wywolac getUserWithRole. Jeśli 0 i parent wiekszy od 0 sprawdzamy dalej jeśli nie to false
          case _ => None
        })

      //println("uuuxx" + Some(username))

      //Some(UsersDAO.getUsername(username).asInstanceOf[Users])

    }

  def parseUserFromQueryString(implicit request: RequestHeader) = {
    println("parse")
    val query = request.queryString.map { case (k, v) => k -> v.mkString }
    val path = request.path
    val username = query get ("username")

    println("username" + username)
    println("path" + path)

    val user: Option[String] = None
    request.session.get("username").map(username => user)
    //println("uuuxx" + Some(username))
    println("uuuuu" + user)

    var roleForUrl: Option[String] = None
    if (null != PagesDAO.getRoleForPath(path) && PagesDAO.getRoleForPath(path).size() > 0) {
      roleForUrl = Some(PagesDAO.getRoleForPath(path).get(0).getAccessForRole())
    }
    println("roleFOR" + roleForUrl)
    val accessForRole: Option[String] = Some("user") // roleForUrl. getAccessForRole().asInstanceOf[String]

    val password = query get ("password")
    println("pass" + password)

    //().asInstanceOf[String]

    (username, roleForUrl) match {
      case (Some(u), Some(r)) => Some(UsersDAO.getUsername(u).get(0)).filter(user => if (UsersDAO.getUserWithRole(u, r).size() > 0) true else false)
      case _ => None
    }
  }

  def parseUserFromRequest(implicit request: RequestHeader): Option[Users] = {
    parseUserFromQueryString orElse parseUserFromCookie
  }

}