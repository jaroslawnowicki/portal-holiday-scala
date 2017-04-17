package models.dao

import play.db.jpa.JPA
import models.Users
import models.Roles

object UsersDAO {
	
  def getUser(email: String, password: String) : java.util.List[Users] = {
    return JPA.withTransaction(new play.libs.F.Function0[java.util.List[Users]]()  {
      @Override
      def apply() : java.util.List[Users] = {
        return JPA.em().createQuery("from Users WHERE email = :email AND password = :password").
        setParameter("email", email).setParameter("password", password).
        getResultList().asInstanceOf[java.util.List[Users]]
       
      }
    })
  }
  
  def getUserWithRole(email: String, role: String) : java.util.List[Users] = {
    println("role")
    println("ema" + email)
    println("role" + role)
    return JPA.withTransaction(new play.libs.F.Function0[java.util.List[Users]]()  {
      @Override
      def apply() : java.util.List[Users] = {
        return JPA.em().createQuery("from Users u LEFT JOIN u.roles r WHERE u.email = :email AND r.nameRole = :role").
        setParameter("email", email).setParameter("role", role).
        getResultList().asInstanceOf[java.util.List[Users]]
       
      }
    })
  }
    
    def getUsername(email: String) : java.util.List[Users] = { 
      println("email" + email)
    return JPA.withTransaction(new play.libs.F.Function0[java.util.List[Users]]()  {
      @Override
      def apply() : java.util.List[Users] = {
        return JPA.em().createQuery("from Users WHERE email = :email").
        setParameter("email", email).
        getResultList().asInstanceOf[java.util.List[Users]]
       
      }
    })
    }
    
    
    
    def getUserSearchRole(email: String, role: String): java.util.List[Users] = {
      
      println("role")
    println("ema" + email)
    println("role" + role)
    var rol = new RolesDAO
    var user = getUsername(email)
    val searchRole = "admin" //rol.getSearchRole(role, user.get(0).getRoles().get(0).getNameRole()) //Narazie jedna reguła 
    println("SearchRole" + searchRole)
    return JPA.withTransaction(new play.libs.F.Function0[java.util.List[Users]]()  {
      @Override
      def apply() : java.util.List[Users] = {
               
        return JPA.em().createQuery("from Users u LEFT JOIN u.roles r WHERE u.email = :email AND r.nameRole = :role").
        setParameter("email", email).setParameter("role", searchRole).
        getResultList().asInstanceOf[java.util.List[Users]]
       
      }
    })
      
    }
    
 
    
}

case class UsersDAO(email:String, password:String, isPremium:Boolean, balance:Int) {
  def checkPassword(password: String): Boolean = this.password == password

}