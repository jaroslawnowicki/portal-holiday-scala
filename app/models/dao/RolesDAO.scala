package models.dao

import play.db.jpa.JPA
import models.Roles

class RolesDAO {

  def getRole (role: String ) = {
    JPA.withTransaction(new play.libs.F.Function0[java.util.List[Roles]]()  {
      @Override
      def apply() : java.util.List[Roles]= {        
        return JPA.em().createQuery("from Roles WHERE nameRole = :role").
        setParameter("role", role).setMaxResults(1).
        getResultList().asInstanceOf[java.util.List[Roles]]  
       
      }
    }) 
  }
  
  def getSearchRole(role: String, compareRole: String = "None"): String = {
    try {
      println("role w " + role)
    	val roles : java.util.List[Roles] = getRole(role)
    	val nextRole = getRoleForId(roles.get(0).getParentId())
    	println("nextRole" + nextRole.get(0).getNameRole())
    	println("compate" + compareRole)
    	if (roles.size() > 0) {
    	  	  if (nextRole.get(0).getNameRole().contains(compareRole))
    	  	  {
    	  	    println("tutaj i koniec")
    	  	    return compareRole
    	  	  } 
    	  	  else if (nextRole.get(0).getParentId() == 0)
    	  	  {
    	  	    println("xxx")
    	  	    if (nextRole.get(0).getNameRole().equals(compareRole))
    	  	    {
    	  	      println("iii")
    	  	    	return compareRole
    	  	    } else 
    	  	    {
    	  	      println("tttt")
    	  	    	return "None"
    	  	    }
    	  	  }    	  	  
    	  	  else 
    	  	  {
    	  	    getSearchRole(nextRole.get(0).getNameRole(), compareRole)
    	  	  }
    	  	  
    	  
    	}
    	return "None";
    } catch {
      case ex : Exception => return "None" + ex.getMessage()
    } 
  }
  
  def getRoleForId(roleId: Int) : java.util.List[Roles]= {
    
    return JPA.withTransaction(new play.libs.F.Function0[java.util.List[Roles]]()  {
      @Override
      def apply() : java.util.List[Roles]= {        
        return JPA.em().createQuery("from Roles WHERE roleId = :roleId").
        setParameter("roleId", roleId).setMaxResults(1).
        getResultList().asInstanceOf[java.util.List[Roles]]  
       
      }
    }) 
    
  }
}