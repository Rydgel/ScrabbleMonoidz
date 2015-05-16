import java.text.Normalizer

import scalaz._, Scalaz._


object Scrabble {

  val mapCharScores: Map[Char, Int] = Map(
    ('a', 1), ('b', 3), ('c', 3),  ('d', 2), ('e', 1), ('f', 4), ('g', 2),
    ('h', 4), ('i', 1), ('j', 8),  ('k', 5), ('l', 1), ('m', 3), ('n', 1),
    ('o', 1), ('p', 3), ('q', 10), ('r', 1), ('s', 1), ('t', 1), ('u', 1),
    ('v', 4), ('w', 4), ('x', 8),  ('y', 4), ('z', 10)
  )

  case class Score(value: Int)

  implicit object ScoreShow extends Show[Score] {
    override def shows(s: Score) = s"Score: ${s.value}"
  }

  implicit object ScoreMonoid extends Monoid[Score] {
    def append(a: Score, b: => Score): Score = Score(a.value + b.value)
    def zero: Score = Score(0)
  }

  implicit object ScoreEqual extends Equal[Score] {
    def equal(v1: Score, v2: Score): Boolean =
      v1.value == v2.value
  }

  private def normalizeChar(c: Char): Char =
    Normalizer.normalize(s"$c", Normalizer.Form.NFD)
              .replaceAll("[^\\p{ASCII}]", "")
              .charAt(0)

  private def score(c: Char): Score =
    Score(mapCharScores.getOrElse(normalizeChar(c), 0))

  def scoreString(s: String): Score = {
    val m = implicitly[Monoid[Score]]
    // s.foldRight(m.zero)((a, b) => score(a) |+| b)
    s.toCharArray.toList.foldMap(score)(m)
  }

}
