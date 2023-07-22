#SIEĆ GSM

Opis aplikacji:

Wielowątkowa aplikacja okienkowa symulująca przesyłanie wiadomości SMS od nadawców do odbiorców napisana przy użyciu framewora SWING. W warstwie wizualnej aplikacja składa się z 3 interaktywnych paneli. Skrajny lewy panel dedykowany jest wyświetlaniu urządzeń nadawczych (VBD). Skrajny prawy panel dedykowany jest wyświetlaniu urządzeń odbiorczych (VRD). Panel środkowy pozwala na wizualizację stacji bazowych (BTS) w skrajnych warstwach i stacji kontrolerów (BSC) warstw pośrednich. W dolnej części tego panelu znajdują się przyciski dodawania i usuwania warstw kontrolerów (BSC).

Działanie aplikacji:

Użytkownik aplikacji tworzy obiekty VBD przy użyciu przycisku "ADD" w dolnej części lewego panelu, z których każdy jest oddzielnym wątkiem i natychmiast rozpoczyna transmitowanie wprowadzonej podczas tworzenia wiadomości. Jako odbiorca wybierane jest losowe urządzenie odbiorcze. Utworzony SMS przekazywany jest do stacji BTS o najmniejszej liczbie oczekujących SMSów. Aplikacja od razu po uruchomieniu zawiera trzy warstwy. Dokładna ilość warstw pośrednich zależy od akcji użytkowanika aplikacji. Każda nowa warstwa kotrolerów tworzona jest z jednym BSC. Usunięcie warstwy skutkuje zaprzestaniem przyjmowania wiadomości przez tą warstwę i natychmiastowym przekazaniem wiadomości z wszystkich BSC tej warstwy z pominięciem czasu oczekiwania.

Każdy BSC przechowuje SMS przez losowy czas od 5 do 15 sekund, a następnie przekazuje go do kolejnej warstwy.

Każdy BTS przekazuje SMS do kolejnej warstwy lub VRD po upływie 3 sekund.

Jeżeli w danej warstwie ilość SMS w bażdym BSC lub BTS jest większa od 5, automatycznie dodawany jest nowy BTS/BSC.

Jeżeli okaże się, że numer odbiorczy, pod który został wysłany SMS nie istnieje, program podniesie wyjątek a system obsłuży go wyświetlając komunikat w konsoli.
