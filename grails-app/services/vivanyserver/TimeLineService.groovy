package vivanyServer

import grails.transaction.Transactional

@Transactional
class TimeLineService {

    List<Map> createTimeline(entries) {
        def year = 0
        def mapaAnio = [:]
        def mapaEntries = [:]
        def entriesList = []
        def List<Map> timelineList =  []

        def List<Map> sortedEntries = entries.sort{[it.entryDate]}
        println sortedEntries

        if (sortedEntries) {                           // If entries not empty
          year = sortedEntries[0].entryDate.year       //
          mapaAnio.put('año', 1900 + year)       // set the year
          timelineList.add(mapaAnio)             // Add it to list
          mapaAnio = [:]                         // reset map
        }

        entries.eachWithIndex { e, index ->
          if (year == e.entryDate.year) {       // same year
              println "Same Year: " + year + " : " + e.entryDate.year
              println "Entry: " + e
              entriesList.add(e)                // add entrie
              if (index == entries.size()) {     // End of list, no more years
                println "End of entries: " + year + " : " + e.entryDate.year
                println "Entry: " + e
                mapaEntries.put('Entries',entriesList)
                timelineList.add(mapaEntries)
              } else {   // Change of year only if not end of list
                  if (entries[index+1].entryDate.year != entries[index].entryDate.year) {
                      println "Next year different Year: " + year + " : " + e.entryDate.year
                      println "Entry: " + e
                      mapaEntries.put('Entries',entriesList)
                      timelineList.add(mapaEntries)  // cuando cambia el año añandimos los entries
                  }
              }
          } else {
            println "Different Year: " + year + " : " + e.entryDate.year
            println "Entry: " + e
            println  "mapaAnio: " + mapaAnio
            mapaAnio.put('año',1900 + e.entryDate.year)
            timelineList.add(mapaAnio)     // el año

            entriesList = []               // y hacemos reset de la lista de entradas
            mapaEntries = [:]              // y el mapa de entradas
            entriesList.add(e)
            mapaEntries.put('Entries',entriesList)
            timelineList.add(mapaEntries)

            mapaAnio = [:]                  // reset map
          }
          year = e.entryDate.year
        }

//        timelineList.each {
//           for ( entry in it ) {
//              println entry.key
//              println entry.value
//           }
//        }

        return timelineList
    }
}
