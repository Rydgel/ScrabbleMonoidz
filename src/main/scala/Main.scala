import scalaz._, Scalaz._, effect._, IO._


object Main {

  def displayScoreFromInput: IO[Unit] =
    for {
      _    <- putStrLn("Enter your word:")
      word <- readLn
      _    <- putStrLn(Scrabble.scoreString(word).shows)
      _    <- displayScoreFromInput
    } yield ()

  def main(args: Array[String]) =
    displayScoreFromInput.unsafePerformIO()

}
