package classes.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.awt.*;

public class Menu extends HorizontalLayout {

    MenuBar menu = new MenuBar();

    public Menu(){
        menu.setOpenOnHover(true);

        MenuItem fooldal = menu.addItem("Főoldal", event -> UI.getCurrent().navigate(""));
        MenuItem felsorolasok = menu.addItem("felsorolasok");
        MenuItem menetrend = menu.addItem("menetrend", event -> UI.getCurrent().navigate("menetrend"));
        MenuItem jegyfoglalas = menu.addItem("jegyfoglalas", event -> UI.getCurrent().navigate("jegyfoglalas"));
        MenuItem repulojegyCsre = menu.addItem("repjegy csere", event -> UI.getCurrent().navigate("repulojegyCsere"));
        MenuItem keresok = menu.addItem("keresok");
        MenuItem kimutats = menu.addItem("kimutats", event -> UI.getCurrent().navigate("kimutatas"));
        MenuItem biztositasok = menu.addItem("biztositasok szerkesztese", event -> UI.getCurrent().navigate("editBiztositasok"));

        SubMenu felsorolasokSubMenu = felsorolasok.getSubMenu();
        MenuItem repulojaratok = felsorolasokSubMenu.addItem("repulojaratok", event -> UI.getCurrent().navigate("repulojaratok"));
        MenuItem legitarsasagok = felsorolasokSubMenu.addItem("legitarsasagok", event -> UI.getCurrent().navigate("legitarsasagokList"));
        MenuItem legnepszerubbJaratok = felsorolasokSubMenu.addItem("legnepszerubb", event -> UI.getCurrent().navigate("legnepszerubb"));
        MenuItem repuloTipusok = felsorolasokSubMenu.addItem("repulo tipusok", event -> UI.getCurrent().navigate("repuloTipusok"));
        MenuItem szallodak = felsorolasokSubMenu.addItem("szallodak", event -> UI.getCurrent().navigate("szallodak"));

        SubMenu keresokSubMenu = keresok.getSubMenu();
        MenuItem planesBetweenDate = keresokSubMenu.addItem("repulojaratok datum kozott", event -> UI.getCurrent().navigate("kereso"));

        add(menu);
    }
}
