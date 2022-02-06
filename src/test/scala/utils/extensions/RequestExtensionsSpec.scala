package utils.extensions

import utils.BaseSpec
import play.api.test.FakeRequest
import models.Credentials
import play.api.mvc.AnyContent
import play.api.mvc.Request
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import utils.RequestHelper
import RequestExtensions._

class RequestExtensionsSpec extends BaseSpec {

  "Request Ops" should "extract Credentials from request" in {
    val request = RequestHelper.sampleRequest
    request.credentials shouldEqual Some(Credentials("Akhil", "Pass@123"))
  }

  it should "return None if the required properties are not found" in {
    val request = RequestHelper.fakeRequest("""{}""")
    request.credentials shouldEqual None
  }

}
