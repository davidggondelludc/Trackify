<div id="top"></div>

<br />
<div align="center">
  <a href="https://github.com/davidggondelludc/Trackify">
    <img src="images/logo.png" alt="Logo" height="180">
  </a>

  <h3 align="center">APM - Arquitecturas y Plataformas Móbiles</h3>

  <p align="center">
    🎵 Divertido generador de listas de reproducción para Spotify
    <br />
    <a href="https://github.com/davidggondelludc/Trackify/wiki"><strong>Explora la documentación »</strong></a>
  </p>
</div>

## 🔌 Instalación y uso
* Registrar la aplicación en Spotify

Deberá registrar la aplicación en el [panel de control del desarrollador de Spotify](https://developer.spotify.com/dashboard) y obtener una identificación de cliente. Cuando registre la aplicación, también debe incluir en la lista blanca un URI de redirección para que el servicio de cuentas de Spotify devuelva la llamada a la aplicación después de autorizarla. También debe agregar el nombre del paquete y la huella digital de la aplicación, ya que se usan para verificar la identidad de la aplicación. Para ello ejecute el siguiente comando en su terminal y agregue una contraseña cuando sea necesario, por defecto será `android`.
```
keytool -alias androiddebugkey -keystore %HOMEPATH%\.android\debug.keystore -list -v
```
Debería recibir una huella digital similar a esta: `SHA1: E7:47:B5:45:71:A9:B4:47:EA:AD:21:D7:7C:A2:8D:B4:89:1C:BF:75`
Copia la huella digital y el nombre del paquete e ingrésalo en el [panel de control del desarrollador de Spotify](https://developer.spotify.com/dashboard), en la sección `Editar configuración`.

* Fichero local.properties

Añada al `local.properties` la siguiente información:
```
MAPS_API_KEY="your_key"
```

<p align="right">(<a href="#top">volver arriba</a>)</p>

## 🛠️ Gitflow

Gitflow define un modelo estricto de ramificación diseñado en torno a los lanzamientos de la aplicación, siendo este ideal para aquellos que lleven una planificación de entregas iterativas. Este flujo de trabajo permite la paralelización del desarrollo mediante ramas independientes, tanto para la preparación, mantenimiento y publicación de versiones como para la corrección de errores en cualquier momento. La funcionalidad y uso de cada rama se detalla a continuación.

-   🏹 main: es la rama principal que contiene cada una de las versiones estables de la aplicación que están destinadas para que puedan ser incluidas en producción.
-   🧰 develop: esta rama contiene todas las funcionalidades del proyecto y en ella se incluirán las nuevas funcionalidades que se desarrollen para la siguiente versión.
-   ⚙️ feature: las ramas de características están destinadas a contener _commits_ que representen una funcionalidad determinada de la aplicación y, al completarse dicha funcionalidad, está es incluida en la rama de desarrollo.
-   🧲 release: las ramas de lanzamiento contienen todas las versiones finales de un producto destinadas a ser incluidas en producción, siendo este un paso previo y preparatorio. En ella se incluyen todas las funcionalidades de la rama de desarrollo y se arregla cualquier error que contenga antes de entrar en producción.
-   🧹 hotfixes: estas ramas están destinadas a ser utilizadas para aplicar arreglos directamente sobre la rama principal cuando se encuentren errores graves en una versión de producción.

<p align="right">(<a href="#top">volver arriba</a>)</p>

## 👥 Autores

👤 David García Gondell: david.ggondell@udc.es

👤 Diego Ramil López: diego.ramil.lopez@udc.es

👤 Hilda Romero Velo: h.rvelo@udc.es

👤 Manuel Ruiz Vale: m.ruiz.vale@udc.es

👤 Laura Ben Artiles: l.ben@udc.es

<p align="right">(<a href="#top">volver arriba</a>)</p>
