import caseClasses._
import excelSheetWriter.WriteTest
import searchClasses.{CalendarSearch, RouteSearch}

import scala.io.Source

object Main extends App {

  val tripList= Source.fromFile("/home/deshbandhu/dummy_files/gtfs_stm/trips.txt").getLines().drop(1) // Trip List
    .map(line => line.split(","))
    .map(a => Trip(a(0).toInt, a(1), a(2), a(3), a(4), a(5), a(6))).toList


  val routeList = Source.fromFile("/home/deshbandhu/dummy_files/gtfs_stm/routes.txt").getLines().drop(1) // Route List
    .map(line => line.split(","))
    .map(a => Route(a(0).toInt, a(1), a(2), a(3), a(4), a(5), a(6))).toList


  val calenderList=  Source.fromFile("/home/deshbandhu/dummy_files/gtfs_stm/calendar.txt").getLines().drop(1) //// Calender List
    .map(line => line.split(","))
    .map(a => Calender(a(0), a(1), a(2), a(3), a(4), a(5), a(6), a(7), a(8), a(9))).toList


  val routeLookup = new RouteSearch(routeList)
  val calenderLookUp = new CalendarSearch(calenderList)

  val enrichedTripRouteResult = tripList.map(trip => TripRoute(trip,
    routeLookup.search(trip.route_id)))

  val enrichedTripResult = enrichedTripRouteResult.map(tripRoute => EnrichTrip(tripRoute,
    calenderLookUp.search(tripRoute.trips.service_id)))

  val writer = new WriteTest(enrichedTripResult) //Case 2



}
