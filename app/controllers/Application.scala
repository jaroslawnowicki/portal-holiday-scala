package controllers

import classes._
import play.api._
import play.api.mvc._
import javax.persistence._
import javax.persistence.Query
import javax.persistence.Persistence

import play.db.jpa.JPA
import org.hibernate.Transaction


object Application extends FrontController with Authentication with PremiumUsersOnly with BalanceCheck  {

  override def getRequiredBalance = 8
  
  def index = Auth{ 
    user =>
      
//    var bar: Bar = new Bar()
//    bar.name = "dsads"
//   
//    
//    JPA.withTransaction(new play.libs.F.Function0[Unit]()  {
//      def apply() = {
//        JPA.em().persist(bar)
//      }
//    })
//    
//    val result = JPA.withTransaction(new play.libs.F.Function0[java.util.List[Bar]]()  {
//      @Override
//      def apply() : java.util.List[Bar] = {
//        return JPA.em().createQuery("from Bar").getResultList().asInstanceOf[java.util.List[Bar]]
//       
//      }
//    })
//   
//    println(result.size())
   
    var result = Nil
        		 
       
    
    
    val message: String = "Test"
    val liczba: Int = 3

//    em.persist(bar)
//    JPA.em().getTransaction().begin()
//    //JPA.em.persist(bar)
//    JPA.em().persist(bar)
//    JPA.em().getTransaction().commit()
   
   val leftColumn = render("Test")
//   render("Test") match {
// case Some(i) => leftColumn
// case None => NotFoundrequire
// }
    println(leftColumn)
    //leftColumn
   Ok(views.html.index(message, liczba, leftColumn ))
  }

}