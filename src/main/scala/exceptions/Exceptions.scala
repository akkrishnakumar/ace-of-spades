package exceptions

import models.UnAuthenticatedUser

sealed trait Err

case class AuthenticationFailed(reason: String, user: UnAuthenticatedUser) extends Err
case class InValidCredentials(reason: String)                              extends Err
