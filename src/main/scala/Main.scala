import kafka.{BookConsumer, BookProducer}
import model.Book

import scala.io.Source
import scala.util.matching.Regex

object Main extends App {
  val topic = "books"
  val recordsCountToPrint = 5
  val csvFile = "bestsellers_with_categories.csv"

  val csvReader = Source.fromResource(csvFile).getLines().toList
  val bookRecords = getBooks(csvReader)

  BookProducer.sendMessages(topic, bookRecords)
  BookConsumer.printLastRecords(topic, recordsCountToPrint)

  def getBooks(lines: List[String]): List[Book] = {
    val head :: rows = lines
    rows.map(r => toBook(r, prepareHeaders(head)))
  }

  def prepareHeaders(head: String): Map[String, Int] = {
    head.split(",").zipWithIndex.toMap
  }

  def toBook(line: String, headers: Map[String, Int]): Book = {
    val data = new Regex(""",(?![^"]*"(?:(?:[^"]*"){2})*[^"]*$)""").split(line)
    Book(
      data(headers("Name")),
      data(headers("Author")),
      data(headers("User Rating")),
      data(headers("Reviews")),
      data(headers("Price")),
      data(headers("Year")),
      data(headers("Genre"))
    )
  }
}
