package excelSheetWriter

import caseClasses.EnrichTrip
import org.apache.poi.xssf.usermodel.{XSSFRow, XSSFSheet, XSSFWorkbook}

import java.io.{File, FileOutputStream}

class WriteTest(enrichedList: List[EnrichTrip]){
  val workbook = new XSSFWorkbook()
  val sheet:XSSFSheet = workbook.createSheet("Enrich Trip Details")

  //====================For Column Name======================
  val csvSchema = Array("Route Id", "Service Id", "Trip Id", "Trip Head Sign", "Direction Id",
    "Shape Id", "Wheelchair accessible", "Note_FR", "Note En", "Agency Id",
    "Route Short Name", "Route Long Name", "Route Type", "Route Url", "Route Colour",
    "Monday", "Tuesday", "Wednesday", "Thrusday", "Friday", "Saturday", "Sunday",
    "Start Date", "End Date")


  var rowCount:Int = 0

  val row:XSSFRow = sheet.createRow(rowCount)
  for(count<- 0 until  csvSchema.length-1){
    val c = row.createCell(count)
    c.setCellValue(csvSchema(count))
  }

  //====================For filling Row Data====================
  enrichedList.foreach{element =>
  {
    rowCount = rowCount+1
    val row = sheet.createRow(rowCount)

    row.createCell(0).setCellValue(element.tripRoute.routes.route_id.toString)
    row.createCell(1).setCellValue(element.calender.service_id.toString)
    row.createCell(2).setCellValue(element.tripRoute.trips.trip_id.toString)
    row.createCell(3).setCellValue(element.tripRoute.trips.trip_headsign.toString)
    row.createCell(4).setCellValue(element.tripRoute.trips.direction_id.toString)
    row.createCell(5).setCellValue(element.tripRoute.trips.shape_id.toString)
    row.createCell(6).setCellValue(element.tripRoute.trips.wheelchair_accessible.toString)
    row.createCell(7).setCellValue(element.tripRoute.trips.note_fr.toString)
    /* row.createCell(8).setCellValue(element.tripRoute.trips.note_en.toString)
     row.createCell(9).setCellValue(element.tripRoute.routes.agency_id.toString)
     row.createCell(10).setCellValue(element.tripRoute.routes.route_short_name.toString)
     row.createCell(11).setCellValue(element.tripRoute.routes.route_long_name.toString)
     row.createCell(12).setCellValue(element.tripRoute.routes.route_type.toString)
     row.createCell(13).setCellValue(element.tripRoute.routes.route_color.toString)
     row.createCell(14).setCellValue(element.calender.monday.toString)
     row.createCell(15).setCellValue(element.calender.tuesday.toString)
     row.createCell(16).setCellValue(element.calender.wednesday.toString)
     row.createCell(17).setCellValue(element.calender.thursday.toString)
     row.createCell(18).setCellValue(element.calender.friday.toString)
     row.createCell(19).setCellValue(element.calender.saturday.toString)
     row.createCell(20).setCellValue(element.calender.sunday.toString)
     row.createCell(21).setCellValue(element.calender.start_date.toString)
     row.createCell(22).setCellValue(element.calender.end_date.toString)
    */
  }

  }
  //==================For writing File==========================
  val out:FileOutputStream = new FileOutputStream(new File("/home/deshbandhu/dummy_files/gtfs_stm/testOutput.xlsx"))
  workbook.write(out)
  out.close()
}// Class End

