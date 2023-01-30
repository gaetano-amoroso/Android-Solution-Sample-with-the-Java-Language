<h1 style="text-align:center; font-size:2.5em; color:#ff6699">`#ff6699`ANDROID SOLUTION SAMPLE WITH THE JAVA LANGUAGE</h1>

<h2 style="text-align:center; color:66ff99">FRAGMENT</h2>

### Fragment Furter Example

<p>
Modificheremo la Activity cosi come fornita dallo IDE Android Studio, blanck Activity, per aggiungere un Fragment staticamente come parte del layout della Activity, il che significa che il Fragment viene mostrato durante l'intero ciclo di vita della Activity.  È un esempio di modularità applicato ai Fragment. Consolideremo un insieme di elementi dell'interfaccia utente (come pulsanti di opzione e testo) e comportamenti di interazione con l'utente che possono essere riutilizzati nei layout di altre attività.
</p>

### Fragment Furter Example 2

<p>
In questa esempio si apprende come aggiungere lo stesso Fragment, dell'esempio appena sopra, ad una Activity in modo dinamico. Il Fragment viene aggiunto solo se l'utente esegue un'interazione nell'attività, in questo caso toccfacendo click sul pulsante denominato OPEN/CLOSE. Modificherai l'app FragmentFurtherExample per gestire il Fragment utilizzando le istruzioni FragmentManager e FragmentTransaction che possono aggiungere, rimuovere e sostituire un Fragment.
</p>

### Fragment To Fragment Comunication Static

<p>
Questa è una dimostrazione di comunicazioni tra Fragment staticiper mezzo della Activity in cui sono contenuti. La Activity agisce da Controller; ovvero viene implementato  il pattern Observer MVC . Si dispone di due Fragment in una Activity e il Fragment 1 vuole parlare con il Fragment 2. In questo esempio, il Fragment 1 ha due pulsanti, il Fragment 2 ha un TextView per visualizzare un contatore. Quando si fa clic su un pulsante in Fragment 1, TextView in Fragment 2 deve aumentare di uno o diminuire di 1. Una soluzione a questo è usare una classe Interface. Crea la Activity per implementare questa interfaccia, il Fragment 1 chiamerà il metodo dell'interfaccia che verrà implementato dalla classe Activity, questo metodo nella classe Activity instraderà i dati dal Fragment 1 al Fragment 2.
</p>

### Fragment To Fragment Comunication Dinamic

<p>
In questo esempio mostriamo come rimpiazzare un Fragment con un altro Dinamicamente e come progettare la comunicazione tra Fragment passando per la Activity che li contiene.
</p>
<p>
Per implementare la comunicazione faremo uso del noto Pattern Comportamentale Mediator. I componenti Fragment, ovvero i componenti mediated, dovrebbere sempre mediati nella comunicazione. In questo caso la Activity che li contiene funge da componente Mediator.
</p>
<p>
Il fragment che vuole inoltrare un messaggio ha il compito di definire una interfaccia i cui metodi dovranno essere implementati dalla Activity. Dal Fragment in questione viene invocato il metodo o i metodi che L'interfaccia dichiara e che La Activity implementa. In tale Implementazione sarà inoltrato
il messaggio/dati alla componente Fragment Appropriata.
</p><br/><br/>

<h2 style="text-align:center; color:66ff99">DATA AND FILE STORAGE</h2>
<P>Android usa un file syste che è molto simile ai files system basati su disco delle altre piattaforme. Il sistema offre svariate soluzioni/opzioni allo sviluppatore per salvare i dati dell'app.</p><br/>

* <strong style = "color: #00ffff">***App Specific storage***</strong>
  <p style="padding-left:15px">Archiviare i file destinati esclusivamente all'uso dell'applicazione in directory dedicate     all'interno di un volume di archiviazione interna o in diverse directory dedicate all'interno dell'archiviazione esterna. È buona norma usare <mark>l'internal storage</mark> ovvero,le directory all'interno della memoria interna` per salvare informazioni sensibili a cui le altre app non dovrebbero accedere.</p>
* <strong style = "color: #00ffff">***Shared Storage***</strong>
  <p style="padding-left:15px"> Memorizza i file che l'applicazione intende condividere con altre applicazioni, tra cui media, documenti e altri file.</p>
* <strong style = "color: #00ffff">***Preferences***</strong>
  <p style="padding-left:15px"> Memorizzare dati primitivi e privati in coppie chiave-valore.</p>
* <strong style = "color: #00ffff">***Databases***</strong>
  <p style="padding-left:15px"> Memorizza i dati strutturati in un database privato utilizzando la libreria di persistenza Room ma non solo. Altre librerie come SqlLight </p>


## App Specific Storage 

