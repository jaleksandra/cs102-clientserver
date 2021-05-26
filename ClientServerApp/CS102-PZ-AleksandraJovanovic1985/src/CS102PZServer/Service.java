
package CS102PZServer;

/**
 *
 * @author Aleksandra
 */

/**
 * Service obezbedjuje pravila za Service klasu
 */

abstract class Service {

    /**
     * handle metod koji je potrebno implementirati u svaku Service klasu
     */
    protected abstract void handleRequest();
}
