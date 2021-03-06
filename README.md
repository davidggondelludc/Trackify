<div id="top"></div>

<br />
<div align="center">
  <a href="https://github.com/davidggondelludc/Trackify">
    <img src="images/logo.png" alt="Logo" height="180">
  </a>

  <h3 align="center">APM - Arquitecturas y Plataformas M贸biles</h3>

  <p align="center">
    馃幍 Divertido generador de listas de reproducci贸n para Spotify
    <br />
    <a href="https://github.com/davidggondelludc/Trackify/wiki"><strong>Explora la documentaci贸n 禄</strong></a>
  </p>
</div>
<div align="center">

[![Watch the video](images/video.PNG)](https://youtu.be/k14EpDJbZnQ)

</div>


## 馃攲 Instalaci贸n y uso
* Registrar la aplicaci贸n en Spotify

Deber谩 registrar la aplicaci贸n en el [panel de control del desarrollador de Spotify](https://developer.spotify.com/dashboard) y obtener una identificaci贸n de cliente. Cuando registre la aplicaci贸n, tambi茅n debe incluir en la lista blanca un URI de redirecci贸n para que el servicio de cuentas de Spotify devuelva la llamada a la aplicaci贸n despu茅s de autorizarla. Tambi茅n debe agregar el nombre del paquete y la huella digital de la aplicaci贸n, ya que se usan para verificar la identidad de la aplicaci贸n. Para ello ejecute el siguiente comando en su terminal y agregue una contrase帽a cuando sea necesario, por defecto ser谩 `android`.
```
keytool -alias androiddebugkey -keystore %HOMEPATH%\.android\debug.keystore -list -v
```
Deber铆a recibir una huella digital similar a esta: `SHA1: E7:47:B5:45:71:A9:B4:47:EA:AD:21:D7:7C:A2:8D:B4:89:1C:BF:75`
Copia la huella digital y el nombre del paquete e ingr茅salo en el [panel de control del desarrollador de Spotify](https://developer.spotify.com/dashboard), en la secci贸n `Editar configuraci贸n`.

* Fichero local.properties

A帽ada al `local.properties` la siguiente informaci贸n:
```
MAPS_API_KEY="your_key"
```

<p align="right">(<a href="#top">volver arriba</a>)</p>

## 馃洜锔? Gitflow

Gitflow define un modelo estricto de ramificaci贸n dise帽ado en torno a los lanzamientos de la aplicaci贸n, siendo este ideal para aquellos que lleven una planificaci贸n de entregas iterativas. Este flujo de trabajo permite la paralelizaci贸n del desarrollo mediante ramas independientes, tanto para la preparaci贸n, mantenimiento y publicaci贸n de versiones como para la correcci贸n de errores en cualquier momento. La funcionalidad y uso de cada rama se detalla a continuaci贸n.

-   馃徆 main: es la rama principal que contiene cada una de las versiones estables de la aplicaci贸n que est谩n destinadas para que puedan ser incluidas en producci贸n.
-   馃О develop: esta rama contiene todas las funcionalidades del proyecto y en ella se incluir谩n las nuevas funcionalidades que se desarrollen para la siguiente versi贸n.
-   鈿欙笍 feature: las ramas de caracter铆sticas est谩n destinadas a contener _commits_ que representen una funcionalidad determinada de la aplicaci贸n y, al completarse dicha funcionalidad, est谩 es incluida en la rama de desarrollo.
-   馃Р release: las ramas de lanzamiento contienen todas las versiones finales de un producto destinadas a ser incluidas en producci贸n, siendo este un paso previo y preparatorio. En ella se incluyen todas las funcionalidades de la rama de desarrollo y se arregla cualquier error que contenga antes de entrar en producci贸n.
-   馃Ч hotfixes: estas ramas est谩n destinadas a ser utilizadas para aplicar arreglos directamente sobre la rama principal cuando se encuentren errores graves en una versi贸n de producci贸n.

<p align="right">(<a href="#top">volver arriba</a>)</p>

## 馃懃 Autores

馃懁 David Garc铆a Gondell: david.ggondell@udc.es

馃懁 Diego Ramil L贸pez: diego.ramil.lopez@udc.es

馃懁 Hilda Romero Velo: h.rvelo@udc.es

馃懁 Manuel Ruiz Vale: m.ruiz.vale@udc.es

馃懁 Laura Ben Artiles: l.ben@udc.es

<p align="right">(<a href="#top">volver arriba</a>)</p>
