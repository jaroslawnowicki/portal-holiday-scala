package classes

import models.dao.UsersDAO

class Auth {

  def getUser(email: String, password: String) : Boolean= {
    if (UsersDAO.getUser(email, password).size > 0)
      return true    
    return false
  }
  
}