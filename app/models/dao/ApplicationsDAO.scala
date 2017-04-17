package models.dao

import models.Applications
import java.util.Date
import play.db.jpa.JPA

class ApplicationsDAO {
	
  def addNewApplication(from: Date) : Unit = {
    var app = new Applications
    app.setFrom(from)
    JPA.withTransaction(new play.libs.F.Function0[Unit]()  {
      def apply() = {
        JPA.em().persist(app)
      }
    })
  }
}