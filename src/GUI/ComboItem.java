package GUI;

/**
 * Created by Nico on 01/12/2016.
 */
public class ComboItem {

    /**
     * la clé du comboItem
     */
    private String key;

    /**
     * la valeur du comboItem
     */
    private String value;

    /**
     * constructeur du comboItem
     * @param key
     *      la clé
     * @param value
     *      la valeur
     */
    public ComboItem(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return key;
    }

    /**
     * retourne la clé du comboItem
     * @return
     *      la clé
     */
    public String getKey()
    {
        return key;
    }

    /**
     * retourne la value du comboItem
     * @return
     *      la valeur
     */
    public String getValue()
    {
        return value;
    }
}
