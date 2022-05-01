package classes.views;

import classes.databaseclasses.entity.Jegy;
import classes.databaseclasses.entity.Repulojaratok;
import classes.databaseclasses.entity.Utasok;
import classes.databaseclasses.repository.JegyRepository;
import classes.databaseclasses.repository.RepulojaratokRepository;
import classes.databaseclasses.repository.UtasokRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@PageTitle("jegyfoglalas Atszallassal")
@Route(value = "jegyfoglalasAtszallassal")
public class JegyFoglalasAtszallassal extends VerticalLayout {
    @Autowired
    RepulojaratokRepository repulojaratokRepository;
    @Autowired
    UtasokRepository utasokRepository;
    @Autowired
    JegyRepository jegyRepository;

    TextField ar;
    ComboBox<String> honnan;
    ComboBox<String> hova;
    ComboBox<String> utasNev;
    Grid<Jegy> jegyGrid;
    Button foglal;

    List<Repulojaratok> repulojaratokList;

    public JegyFoglalasAtszallassal(){
        add(ar = new TextField("ar"));
        add(honnan = new ComboBox<>("honnan"));
        add(hova = new ComboBox<>("hova"));
        add(foglal = new Button("foglal"));

        honnan.addValueChangeListener(event -> findJaratok());
        hova.addValueChangeListener(event -> findJaratok());
        foglal.addClickListener(click -> reserve());

        setUpGrid();
    }

    @PostConstruct
    void init(){
        repulojaratokList = (List<Repulojaratok>) repulojaratokRepository.findAll();
        List<String> allHonnan = new ArrayList<>();
        List<String> allHova = new ArrayList<>();
        for (Repulojaratok rep: repulojaratokList){
            if (!allHonnan.contains(rep.getHonnan())){
                allHonnan.add(rep.getHonnan());
            }
            if (!allHova.contains(rep.getHova())){
                allHova.add(rep.getHova());
            }
        }
        honnan.setItems(allHonnan);
        hova.setItems(allHova);
        utasNev.setItems(utasokRepository.findAllNev());
    }

    private void findJaratok(){
        for (Repulojaratok repulojaratok : repulojaratokList){
            if (repulojaratok.getHonnan().equals(honnan.getValue()) &&
                repulojaratok.getHova().equals(hova.getValue()) &&
                !jegyRepository.findAllByRepulojaratokAndFoglalt(repulojaratok, Boolean.FALSE).isEmpty()){
                    setUpGrid();
                    jegyGrid.setItems(jegyRepository.findAllByRepulojaratokAndFoglalt(repulojaratok, Boolean.FALSE));
            }else if (repulojaratok.getHonnan().equals(honnan.getValue()) &&
                    !jegyRepository.findAllByRepulojaratokAndFoglalt(repulojaratok, Boolean.FALSE).isEmpty()){
                List<Repulojaratok> honnan = repulojaratokRepository.findAllByHonnan(repulojaratok.getHova());
                for (Repulojaratok innerRepulok : honnan){
                    if (innerRepulok.getHova().equals(hova.getValue())){
                        setUpGrid();
                        List<Jegy> jegyek = jegyRepository.findAllByRepulojaratokAndFoglalt(innerRepulok, Boolean.FALSE);
                        if (!jegyek.isEmpty()){
                            jegyek.addAll(jegyRepository.findAllByRepulojaratokAndFoglalt(repulojaratok, Boolean.FALSE));
                        }
                        if (!jegyek.isEmpty()){
                            jegyGrid.setItems(jegyek);
                        }
                    }
                }
            }
        }
    }

    private void setUpGrid(){
        jegyGrid.addColumn(Jegy::repulojarat).setHeader("repulojarat");
        jegyGrid.addColumn(Jegy::getSor).setHeader("sor");
        jegyGrid.addColumn(Jegy::getSzek).setHeader("szek");
        jegyGrid.addItemClickListener(click -> ar());
        add(jegyGrid = new Grid<>());
    }

    private void reserve(){
        Jegy jegy = (Jegy) jegyGrid.getSelectedItems().toArray()[0];
        jegy.setFoglalt(true);
        jegy.setUtasok(utasokRepository.findByNev(utasNev.getValue()));
        jegyRepository.save(jegy);
    }

    private void ar(){
        Jegy jegy = (Jegy) jegyGrid.getSelectedItems().toArray()[0];
        Utasok utas = utasokRepository.findByNev(utasNev.getValue());
        if (utas.getGyerek()){
            ar.setValue(String.valueOf(jegy.getAr()*0.7));
        }else {
            ar.setValue(String.valueOf(jegy.getAr()));
        }
    }
}
