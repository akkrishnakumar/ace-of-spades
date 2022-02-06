package auth

import utils.BaseSpec
import models.AuthenticatedUser
import play.api.test.FakeRequest
import utils.RequestHelper
import models.UnAuthenticatedUser
import exceptions.Err
import exceptions.AuthenticationFailed
import exceptions.InValidCredentials

class UserAuthenticaterSpec extends BaseSpec {

  val userAuthenticater = new DefaultUserAuthenticater

  "User Authenticater" should "return AuthenticatedUser when credentails are valid" in {
    // GIVEN
    val expectedUser = Right(AuthenticatedUser("Akhil", 1000.00))
    val request      = RequestHelper.sampleRequest

    // WHEN
    val actualUser = userAuthenticater.authenticate(request)

    // THEN
    actualUser shouldEqual expectedUser
  }

  it should "return Authentication Failed when user is not authorized" in {
    // GIVEN
    val expectedValidationError =
      Left(AuthenticationFailed("Not Authorized", UnAuthenticatedUser("Elon")))
    val request = RequestHelper.fakeRequest("""{ "uname": "Elon", "pass": "DogeCoin" }""")

    // WHEN
    val actualValidationError = userAuthenticater.authenticate(request)

    // THEN
    actualValidationError shouldEqual expectedValidationError

  }

  it should "return Invalid Credentials when credentials are invalid/not found" in {
    // GIVEN
    val expectedValidationError =
      Left(InValidCredentials("Invalid Credentials"))
    val request = RequestHelper.fakeRequest("""{}""")

    // WHEN
    val actualValidationError = userAuthenticater.authenticate(request)

    // THEN
    actualValidationError shouldEqual expectedValidationError
  }

}
