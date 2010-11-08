package net.alexguev.learningscala.iprovingchangenotifier

import java.net.URL;

import org.cyberneko.html.parsers.SAXParser
import scala.xml.{ SAXParser, XML, NodeSeq }

trait ChangeNodeParserComponent { this: XmlLoaderComponent =>

  class ChangeNodeParser(val liContent: NodeSeq) {

    def author: Option[String] = {
      url match {
        case Some(url) => {
          val page = xmlLoader.loadFromSecuredUrl(url, "guevaraa", "#1engine3")
          val contentInfoNode = page \\ "DIV" find { node => (node \ "@id").text == "ricardo-content-info" }
          contentInfoNode match {
            case Some(div) => 
            	(div \ "A").zipWithIndex find { case (_, index) => index == 1 } match {
            		case Some((node, _)) => Some(node text)
            		case None => None
            	}
            case None => None
          }
        }
        case None => None 
      }
    }

    //is this safe ? maybe index does not exist
    //  def url: String = (liContent \ "A")(0) \ "@href" text
    def url: Option[String] = {
      val url = (liContent \ "A").zipWithIndex.find { case (_, index) => index == 0 }
      url match {
        case Some((node, _)) => Some(node \ "@href" text)
        case None => None
      }
    }

  }

}
