package searchClasses

import caseClasses.Route

class RouteSearch(routes: List[Route]) {
  private val searchTable: Map[Int, Route] =  routes.map(route => route.route_id -> route).toMap
  def search(routeId: Int): Route = searchTable.getOrElse(routeId, null)
}