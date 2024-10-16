# Explicación de algunos Test Smells que apararecen en el código
## AppTest
### Assertion Roulette
La redirección de la salida estándar es una necesidad, ya que los mensajes se capturan por ahí, sin embargo, esto genera el test smell indicado ya que el flujo de salida se ve afectado. Cono necesitamos capturar la salida para hacer las pruebas, este test smell no podemos solucionarlo sin cambiar el código original.

### Lazy Test
Este test smell aparece ya que en todo momento se esta realizando una llamada al `main` de la clase `APP`, indicando redundancia. Sin embargo, la unicá forma de comprobar las diferentes salidas es cambiando los parámetros que se le pasan a `main`, por lo que este test smell no se puede refactorizar sin reestructurar la clase `APP`.

### Sensitive Equality
Este test smell aparece por utilizar `toString()`, que se utiliza para capturar la salida del programa. Si variase su implementación en el código, las pruebas fallarían, aunque la lógica fuese igual. Como no hemos refactorizado el código, este es un test smell que no podemos solucionar.