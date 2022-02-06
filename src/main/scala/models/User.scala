package models

sealed trait User

case class AuthenticatedUser(name: String, balance: Double) extends User
case class UnAuthenticatedUser(name: String)                extends User
