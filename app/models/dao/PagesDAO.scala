package models.dao

import play.db.jpa.JPA
import models.Pages


object PagesDAO {

  def getRoleForPath(path: String): java.util.List[Pages] =     
    JPA.withTransaction(new play.libs.F.Function0[java.util.List[Pages]]()  {
      @Override
      def apply() : java.util.List[Pages]= {        
        return JPA.em().createQuery("from Pages WHERE url = :path").
        setParameter("path", path).setMaxResults(1).
        getResultList().asInstanceOf[java.util.List[Pages]]
       
       
      }
    }) 
  
}

case class PagesDAO(path:String) {
  
}