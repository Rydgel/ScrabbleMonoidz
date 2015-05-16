import Scrabble._

import org.scalatest.PropSpec
import org.scalatest.prop.Checkers
import scalaz._, Scalaz._
import org.scalacheck.Arbitrary
import org.scalacheck.Prop._

import scalaz.scalacheck.ScalazProperties._


class ScrabbleTest extends PropSpec with Checkers {

  property("Monoid laws should pass for Score") {
    implicit val arbitraryScore = Arbitrary {
      for {
        x <- Arbitrary.arbitrary[Int]
      } yield Score(x)
    }
    monoid.laws[Score].check
    equal.laws[Score].check
  }

}