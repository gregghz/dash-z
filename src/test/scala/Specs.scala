package com.gregghz.dashz

import org.scalatest._

class RootSpec extends AsyncFunSpec {

  override val nestedSuites = Vector(
    new NestedSpecA("test"),
    new NestedSpecB("test")
  )

  describe("root") {
    it("root sample") {
      assert(0 === 0)
    }
  }

}

class NestedSpecA(someDep: String) extends AsyncFunSpec {
  describe("nested A") {
    it("sample A test") {
      assert(0 === 0)
    }
  }
}

class NestedSpecB(someDep: String) extends AsyncFunSpec {
  describe("nested B") {
    it("sample B test") {
      assert(0 === 0)
    }
  }
}

class StandaloneSpec extends AsyncFunSpec {
  describe("standalone") {
    it("sample C test") {
      assert(0 === 0)
    }

    it("sample D test") {
      assert(0 === 0)
    }
  }
}
