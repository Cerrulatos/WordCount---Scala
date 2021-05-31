
    import java.nio.charset.CodingErrorAction
    import scala.collection.immutable.ListMap
    import scala.io.Codec


    object Start {

      //COLOCANDO NO PADRÃO UTF-8
      implicit val codec = Codec("UTF-8")
      codec.onMalformedInput(CodingErrorAction.REPLACE)
      codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

      def main(args: Array[String]): Unit = {

        //INFORMAR O CAMINHO DO ARQUIVO
        //Coloque o endereço do caminho na variável Path
        val path = if (args.isEmpty) "src/resources/sonetos.txt" else args(0)
        val readFile = scala.io.Source.fromFile(path).getLines.flatMap(_.split(" ")).toList

        // DATAQUALITY DO ARQUIVO (REMOVENDO ACENTUAÇÃO)
        val cleanFile = readFile.map(i => i.replace(":","").
          replace("?","").
          replace(".","").
          replace(",","").
          replace(";","").
          replace("ã","a").
          replace("é","e").
          replace("í","i").
          replace("ó","o").
          replace("ç","c").
          replace("á","a").
          replace("ô","o").
          replace("õ","o").
          replace("ê","e").
          replace("â","a").
          replace("*",""))


        //COUNT DAS PALAVRAS
        //Lógica para contagem das palavras
        val keyData = cleanFile.map(i => (i, 1))
        val groupData = keyData.groupBy(_._1)
        val result: Map[String, Int] = groupData.mapValues(list =>{list.map(_._2).sum})

        //ORDENANDO RESULTADO
        val x = ListMap(result.toSeq.sortWith(_._2 > _._2):_*)

        //IMPRESSÃO
        x.foreach(println)

      }

}
