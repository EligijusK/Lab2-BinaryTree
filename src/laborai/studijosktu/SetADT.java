package laborai.studijosktu;

/**
 * Interfeisu aprašomas Aibės ADT.
 *
 * @param <E> Aibės elemento duomenų tipas
 */
public interface SetADT<E> extends Iterable<E> {

    //Patikrinama ar aibė tuščia.
    boolean isEmpty();

    // Grąžinamas aibėje esančių elementų kiekis.
    int size();

    // Išvaloma aibė.
    void clear();

    boolean add(E element);
    
    // Pašalinamas elementas iš aibės.
    void remove(E element);

    // Patikrinama ar elementas egzistuoja aibėje.
    boolean contains(E element);
    
    // return gratest element thats lower then given
    E lower(E element);
    
    // return gratest element thats lower then given
    E higher(E element);
    
    E pollLast();
    // Grąžinamas aibės elementų masyvas.
    Object[] toArray();
}
