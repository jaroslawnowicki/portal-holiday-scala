package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import models.dao.ApplicationsDAO
import models.dao.ApplicationsDAO




object HolidayCalendar extends Controller with Authentication with PremiumUsersOnly with BalanceCheck {

   override def getRequiredBalance = 8
  
  def index = Auth{
    user =>
    val message: String = "Test"
    Ok(views.html.calendar())
    
  }
  
  def add(dateFrom: String) = Auth {user =>     
  	var appDAO = new ApplicationsDAO
    val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
    val appFrom  = format.parse("2014-02-02");    
    appDAO.addNewApplication(appFrom)
    Ok(Json.toJson("hello"))
  }
}