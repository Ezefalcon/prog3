La primer solucion valida que se considero, es recorrer todos las familias, ir agregando el primer dia preferido y si ese dia no esta disponible buscar el siguiente

Esto no funciono ya que los dias se quedaban sin espacios para las familias entonces como segunda solucion se agrego a la solucion anterior una busqueda dentro del dia
que traiga la familia que tenga mayor cantidad de miembros y y que este en la lista con su dia preferido. Para asi moverlo al segundo dia mejor.

Se intento hacer un metodo recursivo que busque posibilidades diferentes para las familias que tengan un indice de preferencia alto y mas cantidad de miembros pero no
se logro que funcionara.

Reentrega:

Anteriormente el metodo de transformacion local sacaba una familia directamente e intentaba meterla en otro lugar, y esa familia que sacaba para meterla
en otro lugar recursivamente buscaba otro lugar, lo cual causaba problemas ya que habia veces que no habia otro lugar para la familia.

Se arreglo el metodo para hacer una segunda pasada sobre las familias, en donde se busca un lugar alternativo para una familia en la que poniendola en un lugar diferente
se logre reducir el bono. De esta forma se logro reducir el bono en 3570.

Se agrego un ordenado de las familias por miembros antes de insertarlas, entonces va a agregar la familia que tenga menos miembros primero y hacer
que entren mas familias en un dia.

Se comento el codigo para explicar lo que se esta haciendo.