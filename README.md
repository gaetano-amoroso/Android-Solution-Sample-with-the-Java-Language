# Android Solution Sample with the Java Language

## Fragment Furter Example

<p>
Modificheremo la Activity cosi come fornita dallo IDE Android Studio, blanck Activity, per aggiungere un Fragment staticamente come parte del layout della Activity, il che significa che il Fragment viene mostrato durante l'intero ciclo di vita della Activity.  È un esempio di modularità applicato ai Fragment. Consolideremo un insieme di elementi dell'interfaccia utente (come pulsanti di opzione e testo) e comportamenti di interazione con l'utente che possono essere riutilizzati nei layout di altre attività.
</p>


## Fragment Furter Example 2

<p>
In questa esempio si apprende come aggiungere lo stesso Fragment, dell'esempio appena sopra, ad una Activity in modo dinamico. Il Fragment viene aggiunto solo se l'utente esegue un'interazione nell'attività, in questo caso toccfacendo click sul pulsante denominato OPEN/CLOSE. Modificherai l'app FragmentFurtherExample per gestire il Fragment utilizzando le istruzioni FragmentManager e FragmentTransaction che possono aggiungere, rimuovere e sostituire un Fragment.
</p>


## Fragment To Fragment Comunication Static

<p>
Questa è una dimostrazione di comunicazioni tra Fragment staticiper mezzo della Activity in cui sono contenuti. La Activity agisce da Controller; ovvero viene implementato  il pattern Observer MVC . Si dispone di due Fragment in una Activity e il Fragment 1 vuole parlare con il Fragment 2. In questo esempio, il Fragment 1 ha due pulsanti, il Fragment 2 ha un TextView per visualizzare un contatore. Quando si fa clic su un pulsante in Fragment 1, TextView in Fragment 2 deve aumentare di uno o diminuire di 1. Una soluzione a questo è usare una classe Interface. Crea la Activity per implementare questa interfaccia, il Fragment 1 chiamerà il metodo dell'interfaccia che verrà implementato dalla classe Activity, questo metodo nella classe Activity instraderà i dati dal Fragment 1 al Fragment 2.
</p>
