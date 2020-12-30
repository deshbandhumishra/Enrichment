package searchClasses

import caseClasses.Calender

class CalendarSearch(calendars: List[Calender]) {
  private val searchTable: Map[String, Calender] = calendars.map(calendar => calendar.service_id -> calendar).toMap
  def search(serviceId: String): Calender = searchTable.getOrElse(serviceId, null)
}
